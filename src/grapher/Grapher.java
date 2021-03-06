package grapher;

import java.awt.Color;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JPanel;

import common.AbstractGraphTool;
import common.Convertible;
import grapher.exceptions.SizeOutOfRangeException;
import grapher.ui.GraphPanel;
import grapher.util.Buffer;
import grapher.util.Expression;
import grapher.util.Point;
import util.ExceptionHandler;

/**
 * The Grapher is a tool that shows function on the screen.
 * 
 * @author danielxu
 *
 */
public class Grapher extends AbstractGraphTool implements Convertible {

    private List<Buffer> points_buffer;
    private List<Expression> expressions;
    private JPanel graphPanel;

    /** Configuration **/
    private Config config;

    /**
     * Construct grapher with default configuration
     */
    public Grapher() {
	this(new Config());
    }

    /**
     * Construct grapher with custom configuration
     * 
     * @param config The Config object
     */
    public Grapher(Config config) {
	super(config);
	if (ExceptionHandler.isnull(config)) {
	    throw new NullPointerException("Configuration cannot be null");
	}
	this.config = config;
	points_buffer = new ArrayList<Buffer>();
	expressions = new ArrayList<Expression>();
    }

    /**
     * Add one or more expressions to the graph. Each expression <b>can only be put
     * in one graph</b>. Adding one expression to multiple graphs may result
     * rendering error.
     * 
     * @param exp Expression array
     */
    public void add_exp(Expression... exp) {
	int check_result = ExceptionHandler.isnull(exp);
	if (check_result == ExceptionHandler.NULL_ARRAY) {
	    throw new NullPointerException("Expressions array cannot be null");
	} else if (check_result != ExceptionHandler.VALID_ARRAY) {
	    throw new NullPointerException("Expression at index " + check_result + " cannot be null");
	}
	expressions.addAll(Arrays.asList(exp));
    }

    /**
     * Add one or more points on the graph. The points will be discrete and will not
     * be connected together. However, regression is available with proper
     * configuration.{@link grapher.Config}
     * 
     * @param points The array that contains points on the graph
     *               {@link grapher.util.Point}
     */
    public void add_pts(Point... points) {
	add_pts(config.point_color, config.point_size, points);
    }

    /**
     * Add one or more points on the graph with custom size. The points will be
     * discrete and will not be connected together. However, regression is available
     * with proper configuration.{@link grapher.Config}
     * 
     * @param size   The size of the point, minimum value should be 1
     * @param points The array that contains points on the graph
     *               {@link grapher.util.Point}
     */
    public void add_pts(int size, Point... points) {
	add_pts(config.point_color, config.point_size, points);
    }

    /**
     * Add one or more points on the graph with custom color. The points will be
     * discrete and will not be connected together. However, regression is available
     * with proper configuration.{@link grapher.Config}
     * 
     * @param points The array that contains points on the graph
     *               {@link grapher.util.Point}
     * @param c      Color of the points
     */
    public void add_pts(Color c, Point... points) {
	add_pts(c, config.point_size, points);
    }

    /**
     * Add one or more points on the graph with custom size and color. The points
     * will be discrete and will not be connected together. However, regression is
     * available with proper configuration.{@link grapher.Config}
     * 
     * @param c      The color of the points
     * @param size   The size of the points
     * @param points The array that contains points on the graph
     *               {@link grapher.util.Point}
     */
    public void add_pts(Color c, int size, Point... points) {
	if (ExceptionHandler.isnull(c)) {
	    throw new NullPointerException("Color cannot be null");
	}
	if (ExceptionHandler.outrange(size, 1, Integer.MAX_VALUE)) {
	    throw new SizeOutOfRangeException("Size of the point must be greater than 0");
	}
	int check_result = ExceptionHandler.isnull(points);
	if (check_result == ExceptionHandler.NULL_ARRAY) {
	    throw new NullPointerException("Points array cannot be null");
	} else if (check_result != ExceptionHandler.VALID_ARRAY) {
	    throw new NullPointerException("Point at index " + check_result + " cannot be null");
	}
	points_buffer.add(new Buffer(points, c, size));
    }

    @Override
    protected JPanel getGraphPanel() {
	if (graphPanel == null) {
	    graphPanel = new GraphPanel(points_buffer, expressions, config);
	}
	return graphPanel;
    }

    /**
     * Read the formatted file, the format should be: <center>y=... if it is a
     * function</center> <center>(x,y) if it is a point</center> A valie example
     * will be:<br>
     * y=2x+1<br>
     * (5,10)<br>
     * And the Grapher will then show the function 2x+1 and point (5,10). User can
     * add more than one functions and points in arbitrary order, but remember that
     * the file should not contain blank lines and whitespaces, each function or
     * point should be in separate line.
     */
    @Override
    public void read(String file) {
	try {
	    List<String> lines = Files.readAllLines(Paths.get(file));
	    for (String line : lines) {
		if (line.startsWith("y=")) {
		    add_exp(new Expression(line.replace("y=", "")));
		} else if (line.startsWith("(")) {
		    String[] loc = line.replaceAll("[()]", "").split(",");
		    Point p = new Point();
		    p.x = Double.parseDouble(loc[0]);
		    p.y = Double.parseDouble(loc[1]);
		    add_pts(p);
		}
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    /**
     * Write the functions and points on current graph to a .txt file, the file
     * format is explained in {@link #read(String)}.<br>
     * If the given flile does not exist, a new file with the given path and name
     * will be created.
     */
    @Override
    public void out(String location) {
	StringBuilder sb = new StringBuilder();
	for (int i = 0; i < expressions.size(); i++) {
	    sb.append("y=" + expressions.get(i).toString() + "\n");
	}
	for (int i = 0; i < points_buffer.size(); i++) {
	    Buffer b = points_buffer.get(i);
	    Point[] p = b.getPoints();
	    for (int j = 0; j < p.length; j++) {
		Point sp = p[j];
		sb.append("(" + sp.x + "," + sp.y + ")" + "\n");
	    }
	}
	try {
	    Files.write(Paths.get(location), sb.toString().getBytes());
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}

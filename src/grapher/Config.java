package grapher;

import java.awt.Color;
import java.text.DecimalFormat;

import common.BaseConfig;

/**
 * The Configutaion of the grapher. Each grapher has one unique config object,
 * and they donot affect each other. That means, modifying one grapher's
 * configuration will not affect the others. There are tons of values that can
 * be reset to let user customize their graphs. Most of the variables can be
 * modified but some critical variables cannot.
 * 
 * @author danielxu
 *
 */
public class Config extends BaseConfig {

    /** Max value on x axis **/
    public int x_max = 10;

    /** Min value on y axis **/
    public int x_min = -10;

    /** Max value on y axis **/
    public int y_max = 10;

    /** Min value on y axis **/
    public int y_min = -10;

    /**
     * The density of the points represents that how many points will be evaluated
     * between one unit, the more points being evaluated, t he smoother the graph
     * will be, but it will cause longer loading time. The recommend value is 10 and
     * it can be modified
     **/
    public int density = 10;

    /** Default size of points on graph **/
    public int point_size = 4;

    /**
     * The default function color on the graph, if no specific color is given in
     * Expression, grapher will use this color
     **/
    public Color func_color = Color.BLUE;

    /** The grid color of the grapher by default, can be modified **/
    public Color grid_color = Color.DARK_GRAY;

    /** The axis color of the grapher by default, can be modified **/
    public Color axis_color = Color.BLACK;

    /** The unit color on axis by default, can be modified **/
    public Color unit_color = Color.RED;

    /** The point color by default, can be modified **/
    public Color point_color = Color.YELLOW;

    /** The color of the numbers on axises, can be modified **/
    public Color number_color = Color.BLACK;

    /** The color of the cursor location font, can be modified **/
    public Color cursorlocation_color = Color.PINK;

    /** Showing grid on the panel by default, can be modified **/
    public boolean show_grid = true;

    /** Showing axis on the panel by default, can be modified **/
    public boolean show_axis = true;

    /** Showing units on the panel by default, can be modified **/
    public boolean show_unit = true;

    /**
     * Use round points to represent the points, if this is false, use rectangle
     * instead
     **/
    public boolean point_cicrle = false;

    /**
     * Not showing numbers on axises by default, can be modified Numbers on the
     * axies might not be properly rendered in some cases, especially when the there
     * are two many units in a small window and the numbers will overlap each other
     **/
    public boolean show_numbers = false;

    /** Not linking points on the graph by default, can be modified **/
    public boolean link_points = false;

    /**
     * Not showing cursor location by default, enable this will show cursor location
     * on the top left corner on the jframe and also a small rectangle will be
     * rendered at cursor location
     **/
    public boolean show_cursorxy = false;

    /** Show expressions on top left corner **/
    public boolean show_expressions = true;

    /**
     * The DecimalFormat that will be apply to cursorlocation x,y values. However,
     * show_cursorxy need to be enabled first in order to apply this configuration
     **/
    public DecimalFormat cursorlocation_decimal = new DecimalFormat("#0.00");
}

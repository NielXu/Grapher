package grapher;

import java.awt.Color;

/**
 * The Configutaion of the grapher. Each grapher has one unique
 * config object, and they donot affect each other. That means, modifying
 * one grapher's configuration will not affect the others. There are tons
 * of values that can be reset to let user customize their graphs. Most of
 * the variables can be modified but some critical variables cannot.
 * @author danielxu
 *
 */
public class Config {
	
	/**The title of the window, cannot be modified**/
	public final String TITLE = "Grapher v0.1 test";
	
	/**The size of the Panel(not window) by default, can be modified.
	 * The size is width and height, the Panel must be a square**/
	public int size = 600;
	
	/**The max unit in the axises, for both x and y, can be modified**/
	public int max_unit = 10;
	
	/**The min unit in the axises, for both x and y, can be modified**/
	public int min_unit = -10;
	
	/**The density of the points represents that how many points will be
	 * evaluated between one unit, the more points being evaluated, t
	 * he smoother the graph will be, but it will cause longer loading time.
	 * The recommend value is 10 and it can be modified**/
	public int density = 10;
	
	/**The color of the function by default, can be modified**/
	public Color func_color = Color.BLUE;
	
	/**The background color of the grapher by default, can be modified**/
	public Color background_color = Color.BLACK;
	
	/**The grid color of the grapher by default, can be modified**/
	public Color grid_color = Color.DARK_GRAY;
	
	/**The axis color of the grapher by default, can be modified**/
	public Color axis_color = Color.WHITE;
	
	/**The unit color on axis by default, can be modified**/
	public Color unit_color = Color.RED;
	
	/**The point color by default, can be modified**/
	public Color point_color = Color.YELLOW;
	
	/**Showing grid on the panel by default, can be modified**/
	public boolean show_grid = true;
	
	/**Showing axis on the panel by default, can be modified**/
	public boolean show_axis = true;
	
	/**Showing units on the panel by default, can be modified**/
	public boolean show_unit = true;

}
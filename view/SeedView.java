package view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
/**
	 * This class is used in the design of a Seed on the Board. 
	 * Comments by Jaydene Green-Stevens
	 * 
	 * @author Ajeya Jog
	 */
public class SeedView extends Circle {
	

	/**
	 * Method to creates a circle with position, size, and colour to represent the Seed object
	 * 
	 * @param x - x coordinate of the seeds position in the house
	 * @param y	- y coordinate of the seeds position in the house
	 * @param r - the radius of the circle
	 */
	public SeedView(int x, int y, int r) {
		super(x,y,r, Color.web("#ffffff"));
		setStroke(Color.web("#2a282d"));
		//defines the line width
		setStrokeWidth(2);				
		//applies rendering hints to the circle
		setSmooth(true);					
	}
}

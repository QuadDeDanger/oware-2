package view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class SeedView extends Circle {

	/**
	 * Creates a circle with position, size, and colour to represent the Seed object
	 * 
	 * @param x - x coordinate of the seeds position in the house
	 * @param y	- y coordinate of the seeds position in the house
	 * @param r - the radius of the circle
	 */
	public SeedView(int x, int y, int r) {
		super(x,y,r, Color.web("#ffffff"));
		setStroke(Color.web("#2a282d"));
		setStrokeWidth(2);					//defines the line width
		setSmooth(true);					//applies rendering hints to the circle
	}
}

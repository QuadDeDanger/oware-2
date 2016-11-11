package view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class SeedView extends Circle {

	/*Creates a circle could possibly be replaced with just circle in the future,
	 but it might need to store house in the future*/
	public SeedView(int x, int y, int r) {
		super(x,y,r, Color.web("#ffffff"));
		setStroke(Color.web("#2a282d"));
		setStrokeWidth(2);
		setSmooth(true);
	}
}

package view;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SeedView extends Label {
	
	public SeedView() {
		super();
		Image seedImage = new Image("Seed.gif");
		this.setGraphic(new ImageView(seedImage));
	}
}

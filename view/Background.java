package view;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Background extends Label {

	public Background() {
		super();
		Image backgroundImage = new Image("background.jpg");
		//this.setGraphic(new ImageView(backgroundImage));
	}

}
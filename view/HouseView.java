package view;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class HouseView extends GridPane {
	private int counter;
	private Label label;
	
	
	public HouseView() {
		super();
		setAlignment(Pos.CENTER);
		setVgap(20);
		setHgap(20);
		label = new Label(Integer.toString(counter));
		this.add(label, 0, 0);
		
	}
	
	public void setSeeds(int i) {
		if(i != counter) {
			this.getChildren().clear();
			counter = i;
			label.setText(Integer.toString(counter));
			this.add(label, 0, 0);
			
		}
	}

}

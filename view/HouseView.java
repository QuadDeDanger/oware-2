package view;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

public class HouseView extends GridPane {
	private ArrayList<SeedView> seeds = new ArrayList<SeedView>();
	
	public HouseView() {
		super();
		setAlignment(Pos.CENTER);
		setVgap(20);
		setHgap(20);
	}

}

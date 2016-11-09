package view;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class BoardView extends BorderPane{
	private GridPane housesGrid;
	public BoardView() {
		super();
		
		housesGrid = new GridPane();
		housesGrid.setAlignment(Pos.CENTER);
//		housesGrid.setVgap(20);
//		housesGrid.setHgap(20);
		housesGrid.setStyle("-fx-border: 2px solid; -fx-border-color: black;");
	}
	
	private void makeGrid() {
		for(int i=0; i<2; ++i) {
			for(int j=0; j<6; ++j) {
				//housesGrid.add(child, columnIndex, rowIndex);
				
				
			}
		}
	}
	
	

}

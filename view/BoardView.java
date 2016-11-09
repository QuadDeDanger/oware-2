package view;

import functionality.Board;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class BoardView extends BorderPane{
	
	private GridPane housesGrid;
	private Board board;
	private HouseView[][] houses; 
	
	public BoardView(Board board) {
		super();
		
		this.board = board;
		
		housesGrid = new GridPane();
		housesGrid.setAlignment(Pos.CENTER);
//		housesGrid.setVgap(20);
//		housesGrid.setHgap(20);
//		housesGrid.setStyle("-fx-border: 2px solid; -fx-border-color: black;");
		
		makeGrid();
		this.setCenter(housesGrid);
		
	}
	
	private void makeGrid() {
		houses = new HouseView[2][6];
		
		for(int i=0; i<2; ++i) {
			for(int j=0; j<6; ++j) {
				houses[i][j] = new HouseView();
				houses[i][j].setSeeds(board.getHouseOnBoard(i, j).getCount());	//change to one getter
				int x = i;
				int y = j;
				houses[i][j].setOnMouseClicked(new EventHandler<MouseEvent>(){

					@Override
					public void handle(MouseEvent arg0) {
						
						board.sow(x,y);
						updateBoard();
					}
					
				});
				housesGrid.add(houses[i][j], j, i);
			}
		}
	}
	
	private void updateBoard() {
		for(int i=0; i<2; ++i) {
			for(int j=0; j<6; ++j) {
				houses[i][j].setSeeds(board.getHouseOnBoard(i, j).getCount());
			}
		}
	}
	
	

}

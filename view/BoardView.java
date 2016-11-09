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
	private PlayerView playerView1;
	private PlayerView playerView2;
	
	public BoardView(Board board) {
		super();
		
		//
		this.board = board;
		housesGrid = new GridPane();
		housesGrid.setAlignment(Pos.CENTER);
		makeGrid();
		this.setCenter(housesGrid);
		//
		playerView1 = new PlayerView(0, board.getPlayer1Name(), board.getPlayerTurn());
		playerView2 = new PlayerView(1, board.getPlayer2Name(), board.getPlayerTurn());
		this.setLeft(playerView1);
		this.setRight(playerView2);
		
	}
	
	private void makeGrid() {
		houses = new HouseView[2][6];
		System.out.println(board.getPlayerTurn());
		for(int i=0; i<2; ++i) {
			for(int j=0; j<6; ++j) {
				houses[i][j] = new HouseView();
				houses[i][j].setSeeds(board.getHouseOnBoard(i, j).getCount());	//change to one getter
				int x = i;
				int y = j;
				houses[i][j].setOnMouseClicked(new EventHandler<MouseEvent>(){

					@Override
					public void handle(MouseEvent arg0) {
						if(board.getPlayerTurn() == x) {
							board.sow(x,y);
							updateBoard();
						}
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
		playerView1.update(board.getPlayer1Score(), board.getPlayerTurn());
		playerView2.update(board.getPlayer2Score(), board.getPlayerTurn());
	}
	
	

}

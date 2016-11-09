package view;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class PlayerView extends BorderPane {
	
	private Label nameLabel;
	private Label scoreLabel;
	private Label playerTurn;
	
	private int playerNumber;
	
	private HouseView scoreHouse;
	
	public PlayerView(int playerNumber, String playerName, int turnPlayer) {
		super();
		this.playerNumber = playerNumber;
		
		makeLabels(turnPlayer, playerName);
		
		makeScoreHouse();
	}
	
	private void makeLabels(int turnPlayer, String playerName) {
		HBox labels = new HBox();
		nameLabel = new Label(playerName);
		scoreLabel = new Label(" = 0");
		playerTurn = new Label();
		labels.getChildren().addAll(nameLabel, scoreLabel);
		
		if(playerNumber == 0) {
			setTop(labels);
		} else {
			setBottom(labels);
		}
		updatePlayerTurn(turnPlayer);
	}
	
	private void makeScoreHouse() {
		scoreHouse = new HouseView();
		setCenter(scoreHouse);
	}
	
	private void updateScore(int newScore) {
		scoreLabel.setText("= "+newScore);
		scoreHouse.setSeeds(newScore);
	}
	
	private void updatePlayerTurn(int turnPlayer) {
		/*
		if(turnPlayer == playerNumber && playerNumber == 0) {
			playerTurn.setText("Your Turn");
			setBottom(playerTurn);
			
		} else if(turnPlayer == playerNumber && playerNumber == 1) {
			playerTurn.setText("Your Turn");
			setTop(playerTurn);
		} else {
			playerTurn.setText("");
			setBottom(playerTurn);
		}
		*/
	}
	
	public void update(int newScore, int turnPlayer) {
		updateScore(newScore);
		updatePlayerTurn(turnPlayer);
	}
	
	public void updatePlayerName(String name){
		nameLabel.setText(name);
	}

}

package view;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class PlayerView extends BorderPane {
	
	private Label nameLabel;
	private Label scoreLabel;
	private Label playerTurn;
	
	private int playerNumber;
	
	public PlayerView(int playerNumber, String playerName, int turnPlayer) {
		super();
		this.playerNumber = playerNumber;
		
				// border pane
		//left: name		//right: score
		
		
		
		makeLabels(turnPlayer, playerName);
	}
	
	private void makeLabels(int turnPlayer, String playerName) {
		nameLabel = new Label(playerName);
		scoreLabel = new Label("Captured seeds: 0");
		playerTurn = new Label();
		
		setLeft(nameLabel);
		setRight(scoreLabel);
		
		//updatePlayerTurn(turnPlayer);
	}
	
	private void updateScore(int newScore) {
		scoreLabel.setText("Captured seeds: "+newScore);
	}
	
//	private void updatePlayerTurn(int turnPlayer) {
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
	//}
	
	public void update(int newScore, int turnPlayer) {
		updateScore(newScore);
		//updatePlayerTurn(turnPlayer);
	}
	
	public void updatePlayerName(String name){
		nameLabel.setText(name);
	}

}
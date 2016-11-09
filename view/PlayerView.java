package view;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class PlayerView extends BorderPane {
	
	private Label nameLabel;
	private Label scoreLabel;
	
	private int playerNumber;
	private String playerName;
	private int playerScore;
	
	private HouseView scoreHouse;
	
	public PlayerView(int playerNumber, String playerName) {
		super();
		this.playerNumber = playerNumber;
		this.playerName = playerName;
		
		makeLabels();
		
		makeScoreHouse();
	}
	
	private void makeLabels() {
		HBox labels = new HBox();
		nameLabel = new Label(playerName);
		scoreLabel = new Label(" = 0");
		labels.getChildren().addAll(nameLabel, scoreLabel);
		
		if(playerNumber == 1) {
			setTop(labels);
			setBottom(new Label(""));
		} else {
			setBottom(labels);
			setTop(new Label(""));
		}
	}
	
	private void makeScoreHouse() {
		scoreHouse = new HouseView();
		setCenter(scoreHouse);
	}
	
	public void updateScore(int newScore) {
		playerScore = newScore;
		scoreLabel.setText("= "+playerScore);
		scoreHouse.setSeeds(playerScore);
	}

}

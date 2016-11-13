package view;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


public class PlayerView extends BorderPane {

	/**
	 * This class groups the labels on the board containing information
	 * regarding human and computer players, their score and their turn.
	 *
	 * @author Ajeya Jog
	 * @author Federico Midolo
	 */

	private Label nameLabel;
	private Label scoreLabel;
	private Rectangle playerTurn;

	private int playerNumber;

	public PlayerView(int playerNumber, String playerName, int turnPlayer) {
		super();
		this.playerNumber = playerNumber;

		makeLabels(turnPlayer, playerName);
	}

	//Will be turned on once the view can control turns
	private void toggleTurnIndicator(){
        FillTransition f = new FillTransition();
        f.setShape(playerTurn);
        f.setDuration(Duration.millis(1000));
        f.setCycleCount(Timeline.INDEFINITE);
        f.setToValue(Color.web("#eb505d"));
        f.setAutoReverse(true);
        f.play();
    }

    private StackPane nameRectangle(){
        StackPane stack = new StackPane();
        Rectangle temp = new Rectangle(60,20,Color.web("#1b1b1c"));
        stack.getChildren().add(temp);
        stack.getChildren().add(nameLabel);
        temp.setStroke(Color.web("#2a282d"));
        temp.setStrokeWidth(3);
        return stack;
    }

    private StackPane scoreRectangle(){
        StackPane stack = new StackPane();
        Rectangle temp = new Rectangle(110,20,Color.web("#1b1b1c"));
        stack.getChildren().add(temp);
        stack.getChildren().add(scoreLabel);
        temp.setStroke(Color.web("#2a282d"));
        temp.setStrokeWidth(3);
        return stack;
    }


	private void makeLabels(int turnPlayer, String playerName) {
		nameLabel = new Label(playerName);
		scoreLabel = new Label("Captured seeds: 0");
		playerTurn = new Rectangle(50, 15, Color.web("#1b1b1c"));

		setLeft(nameRectangle());
		setRight(scoreRectangle());
		setCenter(playerTurn);
		nameLabel.setTextFill(Color.WHITE);
		scoreLabel.setTextFill(Color.WHITE);
		playerTurn.setStroke(Color.web("#2a282d"));
        playerTurn.setStrokeWidth(3);
		playerTurn.setTranslateX(50/2.0);
        //toggleTurnIndicator();
		// updatePlayerTurn(turnPlayer);
	}

	private void updateScore(int newScore) {
		scoreLabel.setText("Captured seeds: " + newScore);
	}

	// private void updatePlayerTurn(int turnPlayer) {
	/*
	 * if(turnPlayer == playerNumber && playerNumber == 0) { playerTurn.setText(
	 * "Your Turn"); setBottom(playerTurn);
	 *
	 * } else if(turnPlayer == playerNumber && playerNumber == 1) {
	 * playerTurn.setText("Your Turn"); setTop(playerTurn); } else {
	 * playerTurn.setText(""); setBottom(playerTurn); }
	 */
	// }

	public void update(int newScore, int turnPlayer) {
		updateScore(newScore);
		// updatePlayerTurn(turnPlayer);
	}

	public void updatePlayerName(String name) {
		nameLabel.setText(name);
	}

}
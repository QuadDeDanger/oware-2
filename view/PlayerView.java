package view;

import javafx.animation.FillTransition;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class PlayerView extends BorderPane {

	/**
	 * This class groups the components on the board containing information
	 * regarding human and computer players, their score and their turn.
	 *
	 * @author Ajeya Jog
	 * @author Federico Midolo
	 */

	private Label nameLabel;
	private Label scoreLabel;
	private Rectangle playerTurn;
	private FillTransition f;

	private int playerNumber;

	/**
	 * Calls parent constructor and calls makeLabels() method to create and add
	 * components
	 * 
	 * @param playerNumber
	 *            indicates to which player this pane refers to
	 * @param playerName
	 *            used to assign the right String to nameLabel
	 * @param turnPlayer
	 *            indicates the playerNumber of who needs to make the next move
	 */
	public PlayerView(int playerNumber, String playerName, int turnPlayer) {
		super();
		this.playerNumber = playerNumber;

		makeLabels(turnPlayer, playerName);
	}

	/*
	 * Initialises the labels and the turn indicator(playerTurn) and adds them
	 * to their intended position on the pane. Adjusts looks of these to fit the
	 * program style, initialises animation for playerTurn calling method
	 * toggleTurnIndicator() and makes call to updatePlayerTurn() to enable
	 * program to display who needs to make the first move.
	 * 
	 */
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
		playerTurn.setTranslateX(50 / 2.0);
		toggleTurnIndicator(); // initialises FillTransition object before
								// calling updatePlayerTurn
		updatePlayerTurn(turnPlayer);
	}

	/*
	 * Initialises FillTransition object and adjusts looks to fit program style.
	 * Sets the animation regarding the the turn indicator(playerTurn) to repeat
	 * indefinitely. At this point, animation is ready to start.
	 */
	private void toggleTurnIndicator() {
		f = new FillTransition();
		f.setShape(playerTurn);
		f.setDuration(Duration.millis(1000));
		f.setCycleCount(Timeline.INDEFINITE);
		f.setToValue(Color.web("#eb505d"));
		f.setAutoReverse(true);
	}

	/*
	 * Improves nameLabel look by assigning it a Rectangle object and adjusting
	 * this to make it suit the program style.
	 */
	private StackPane nameRectangle() {
		StackPane stack = new StackPane();
		Rectangle temp = new Rectangle(60, 20, Color.web("#1b1b1c"));
		stack.getChildren().add(temp);
		stack.getChildren().add(nameLabel);
		temp.setStroke(Color.web("#2a282d"));
		temp.setStrokeWidth(3);
		return stack;
	}

	/*
	 * Improves scoreLabel look by assigning it a Rectangle object and adjusting
	 * this to make it suit the program style.
	 */
	private StackPane scoreRectangle() {
		StackPane stack = new StackPane();
		Rectangle temp = new Rectangle(110, 20, Color.web("#1b1b1c"));
		stack.getChildren().add(temp);
		stack.getChildren().add(scoreLabel);
		temp.setStroke(Color.web("#2a282d"));
		temp.setStrokeWidth(3);
		return stack;
	}

	/*
	 * Updates the score displayed by the scoreLabel.
	 */
	private void updateScore(int newScore) {
		scoreLabel.setText("Captured seeds: " + newScore);
	}

	/*
	 * Calls play() method to start animation inside turnPlayer, when it is the
	 * turn of the player that this pane refers to. When it is the opponent's
	 * player, animation is restarted and stopped, ready to be started again in
	 * the next turn.
	 */
	private void updatePlayerTurn(int turnPlayer) {

		if (turnPlayer == playerNumber) {	// if this player needs to make next move
			f.play();
			System.out.println("Turn of player" + playerNumber);

		} else if (turnPlayer != playerNumber) {	// if opponent needs to make next move
			f.jumpTo(Duration.ZERO); // restarts animation before stopping it
			f.stop();
		}
	}

	/**
	 * Makes calls to updateScore and updatePlayerTurn to update the scoreLabel
	 * and the turn indicator(playerTurn).
	 * 
	 * @param newScore
	 *            indicates new score value to assign to scoreLabel
	 * @param turnPlayer
	 *            indicates playerNumber of player who needs to make the next
	 *            move, needed to update the turn indicator
	 */
	public void update(int newScore, int turnPlayer) {
		updateScore(newScore);
		updatePlayerTurn(turnPlayer);
	}

	/**
	 * Updates the name String displayed by the nameLabel
	 * 
	 * @param name
	 *            of the player this pane refers to
	 */
	public void updatePlayerName(String name) {
		nameLabel.setText(name);
	}

}
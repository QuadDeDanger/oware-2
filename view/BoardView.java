package view;

import model.Board;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;

import java.util.Optional;

/**
 * This class represents the main pane grouping all components part of the
 * interface the user will interact with during a game of Oware.
 *
 * @author Ajeya Jog
 * @author Federico Midolo
 * @author Aqib Rashid
 */

public class BoardView extends BorderPane {

	private VBox centerPane;
	private GridPane housesGrid;
	private Board board;
	private HouseView[][] houses;
	private PlayerView playerView1;
	private PlayerView playerView2;
	private Button newGame;
	private Button forceEnd;
	private Label gameStatus;

	/**
	 * Constructs the boardView by adding and initialising components either
	 * directly or indirectly, by calling private methods such as setUpTop
	 * makeGrid and nameDialogue.
	 * 
	 * @param board
	 *            a reference to the Board class belonging to the model
	 */
	public BoardView(Board board) {
		super();

		setPadding(new Insets(0, 20, 0, 20));
		setStyle("-fx-background-color: #363338");

		setUpTop();
		gameStatus = new Label();
		centerPane = new VBox(20);
		centerPane.setPadding(new Insets(20, 0, 0, 0));

		this.board = board;
		housesGrid = new GridPane();

		housesGrid.setHgap(5);
		housesGrid.setVgap(8);
		housesGrid.setAlignment(Pos.CENTER);

		makeGrid();

		playerView1 = new PlayerView(0, board.getPlayer1Name(), board.getPlayerTurn());
		playerView2 = new PlayerView(1, board.getPlayer2Name(), board.getPlayerTurn());

		nameDialogue();

		if (board.isPlayingComputer()) {
			updateBoard();
		}

		centerPane.getChildren().addAll(playerView1, housesGrid, playerView2);

		this.setCenter(centerPane);
	}

	/*
	 * Handles the instantiation and adding of components relative to the upper
	 * part of the main pane.
	 */
	private void setUpTop() {
		BorderPane borderTop = new BorderPane();

		borderTop.setPadding(new Insets(20, 0, 0, 0));

		newGame = new Button("New Game");
		newGame.setFocusTraversable(false);

		// provides the to the newGame button the functionality to start a new
		// game with the current players

		newGame.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				fadeHide(gameStatus);
				setCenter(centerPane);
				fadeShow(centerPane);
				forceEnd.setDisable(false);
				board.resetBoard();
				updateBoard();

			}
		});
		newGame.setDisable(true);
		formatButton(newGame);

		Button mainScreen = new Button("Main screen");
		mainScreen.setFocusTraversable(false);

		// provides the mainScreen button with the functionality to leave the
		// current game session and go back to the game mode selection

		mainScreen.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				Stage stage = new Stage();
				stage.setTitle("Oware");

				Scene scene = new Scene(new WelcomeView(), 500, 300);
				stage.setMinWidth(500);
				stage.setMinHeight(350);
				stage.setScene(scene);
				stage.show();

				((Node) event.getSource()).getScene().getWindow().hide();

			}
		});
		formatButton(mainScreen);

		forceEnd = new Button("Force end");
		forceEnd.setFocusTraversable(false);

		// provides the forceEnd button with the functionality to end the
		// current game session, by adding each player's score, with the amount
		// of seeds present in their side of the board

		forceEnd.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Force end game");
				alert.setHeaderText("Game stuck in a loop?");
				alert.setContentText(
						"If both players agree that the game is an endless cycle, each player will capture the seeds on their side of the board.");

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {
					board.captureOwnSeeds();
					updateBoard();
				}

			}
		});
		formatButton(forceEnd);

		Button endGame = new Button("Exit game");
		endGame.setFocusTraversable(false);

		// provides the endGame button with the functionality to close the
		// program altogether

		endGame.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Stage stage = (Stage) endGame.getScene().getWindow();
				// do what you have to do
				stage.close();
			}
		});
		formatButton(endGame);

		// groups button into HBoxes and adds them to the panes

		HBox leftHBox = new HBox(10);
		leftHBox.getChildren().addAll(newGame, mainScreen);

		HBox rightHBox = new HBox(10);
		rightHBox.getChildren().addAll(forceEnd, endGame);

		borderTop.setLeft(leftHBox);
		borderTop.setRight(rightHBox);

		this.setTop(borderTop);
	}

	/*
	 * Provides a method for changing the look of a given button to suit the
	 * style adopted for the rest of the program.
	 */
	private void formatButton(Button b) {
		b.setTextFill(Color.web("#ffffff"));
		b.setStyle(
				"-fx-background-color: #eb505d; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0)");
	}

	/*
	 * Instantiates and adds the components relative to the centre of the main
	 * pane.
	 */
	private void makeGrid() {
		houses = new HouseView[2][6];
		for (int i = 0; i < 2; ++i) {
			for (int j = 0; j < 6; ++j) {
				houses[i][j] = new HouseView(55 * (j + (6 * i)) + 5, 55 + (105 * i), 50);
				if (i == 1)
					houses[i][j].setBottom();
				houses[i][j].addSeeds(board.getHouseCount(i, j));
				int x = i;
				int y = j;

				// Enables the clicking of HouseViews and assigns the
				// functionality provided by the board class from the model

				houses[i][j].setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent arg0) {
						if (!board.gameOverNoMovesPossible() && board.getPlayerTurn() == x) {
							board.sow(x, y);
							updateBoard();
						}
					}

				});
				housesGrid.add(houses[i][j], j, i);
			}
		}
	}

	/*
	 * Provides a method for updating the view after each turn. This includes
	 * changing the number of seeds in each house, the score of each player and
	 * checking whether the game can be considered over.
	 */
	private void updateBoard() {
		// enables the clicking of the newGame button, only if at least a move
		// has been made
		if (board.isGameStarted()) {
			newGame.setDisable(false);
		} else {
			newGame.setDisable(true);
		}

		// updates the amount of seeds displayed in each house, using the data
		// received from the model

		for (int i = 0; i < 2; ++i) {
			for (int j = 0; j < 6; ++j) {
				if ((houses[i][j].getSize() + 1) == board.getHouseOnBoard(i, j).getCount()) {
					houses[i][j].addOneSeed();
				} else if (houses[i][j].getSize() != board.getHouseOnBoard(i, j).getCount()) {
					houses[i][j].clear();
					houses[i][j].addSeeds(board.getHouseOnBoard(i, j).getCount());
				}
			}
		}

		// calls methods contained in PlayerView, which will update the
		// players'scores, if necessary, and update the turn indicator

		playerView1.update(board.getPlayer1Score(), board.getPlayerTurn());
		playerView2.update(board.getPlayer2Score(), board.getPlayerTurn());

		// checks whether or not the game can be considered over

		checkGameFinished();
		if (board.gameOverNoMovesPossible()) {
			board.captureOwnSeeds();
			for (int i = 0; i < 2; ++i) {
				for (int j = 0; j < 6; ++j) {
					houses[i][j].addSeeds(board.getHouseOnBoard(i, j).getCount());
				}
			}
			checkGameFinished();
		}
	}

	/*
	 * Provides a method for terminating the current game window.
	 */
	private void killWindow() {
		this.getScene().getWindow().hide();
	}

	/*
	 * Provides a method for the instantiation of a dialog window where users
	 * are given the option to give their player a customised name.
	 */
	private void nameDialogue() {
		// create the custom dialog
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("Oware - Set player names (optional)");

		// set the button types.
		ButtonType okButton = new ButtonType("Ok", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(okButton, ButtonType.CANCEL);

		// set the style to fit the rest of the program
		dialog.getDialogPane().setStyle("-fx-background-color: #363338");
		dialog.getDialogPane().lookupButton(ButtonType.CANCEL).setStyle("-fx-background-color: #eb505d;"
				+ " -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);-fx-text-fill: #fff");
		dialog.getDialogPane().lookupButton(okButton).setStyle("-fx-background-color: #eb505d;"
				+ " -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);-fx-text-fill: #fff");

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		// instantiates the text field for inserting customised player names and
		// disables the option to change the name of player 1, in the case where
		// it is a computer player

		TextField player1 = new TextField();
		TextField player2 = new TextField();
		if (board.isPlayingComputer()) {
			player1.setDisable(true);
			player1.setText(board.getPlayer1Name());
			player2.setPromptText("Player");
		} else {
			player1.setPromptText("Player 1");
			player2.setPromptText("Player 2");
		}

		grid.add(new Label("Player 1"), 0, 0);
		grid.add(player1, 1, 0);
		grid.add(new Label("Player 2"), 0, 1);
		grid.add(player2, 1, 1);

		dialog.getDialogPane().setContent(grid);
		if (board.isPlayingComputer()) {
			Platform.runLater(() -> player2.requestFocus());
		} else {
			Platform.runLater(() -> player1.requestFocus());
		}

		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == okButton) {
				return new Pair<>(player1.getText(), player2.getText());
			}
			return null;
		});

		Optional<Pair<String, String>> result = dialog.showAndWait();

		// deals with the scenario where users have chosen to assign names to players 
		if (result.isPresent()) {

			if (result.get().getKey().length() > 0) {
				board.setPlayer1Name(result.get().getKey());
			}
			if (result.get().getValue().length() > 0) {
				board.setPlayer2Name(result.get().getValue());
			}
			updatePlayerNames();
		} else {
			killWindow();
		}
	}

	/*
	 * Provides a method for updating players' names.
	 */
	private void updatePlayerNames() {
		playerView1.updatePlayerName(board.getPlayer1Name());
		playerView2.updatePlayerName(board.getPlayer2Name());
	}

	/*
	 * Provides a method for hiding a Node object using a transition animation.
	 */
	private void fadeHide(Node node) {
		FadeTransition fadeNode = new FadeTransition(Duration.millis(500), node);
		fadeNode.setFromValue(1);
		fadeNode.setToValue(0);
		fadeNode.play();
	}

	/*
	 * Provides a method for displaying a Node object using a transition
	 * animation.
	 */
	private void fadeShow(Node node) {
		FadeTransition fadeNode = new FadeTransition(Duration.millis(500), node);
		fadeNode.setFromValue(0);
		fadeNode.setToValue(1);
		fadeNode.play();
	}

	/*
	 * Provides a method for checking whether or not the game session can be
	 * considered over.
	 */
	private void checkGameFinished() {

		// deals with the scenario in which the game ends due to one of the
		// players (or a computer) winning and displays the final scores
		if (board.gameWonCheck()) {
			fadeHide(centerPane);
			// System.out.println("game over, disable UI");
			if (board.getPlayer1Score() > board.getPlayer2Score()) {
				this.setCenter(gameStatus);
				setAlignment(gameStatus, Pos.CENTER);
				gameStatus.setStyle(
						"-fx-text-fill: #fff; -fx-effect: dropshadow(three-pass-box, rgba(255,255,255,0.5), 10, 0, 0, 0);"
								+ "-fx-font-size: 25pt; -fx-opacity: 0;-fx-font-smoothing-type: gray;");
				fadeShow(gameStatus);
				gameStatus.setText("Game won by " + board.getPlayer1Name() + "\n(" + board.getPlayer1Name()
						+ "'s score: " + board.getPlayer1Score() + ", " + board.getPlayer2Name() + "'s score: "
						+ board.getPlayer2Score() + ")");
				// System.out.println("Game won by " + board.getPlayer1Name());
				// this.setBottom(gameStatus);
			} else {
				this.setCenter(gameStatus);
				setAlignment(gameStatus, Pos.CENTER);
				gameStatus.setStyle(
						"-fx-text-fill: #fff; -fx-effect: dropshadow(three-pass-box, rgba(255,255,255,0.5), 10, 0, 0, 0);"
								+ "-fx-font-size: 25pt; -fx-opacity: 0;-fx-font-smoothing-type: gray;");
				fadeShow(gameStatus);
				gameStatus.setText("Game won by " + board.getPlayer2Name() + "\n(" + board.getPlayer1Name()
						+ "'s score: " + board.getPlayer1Score() + ", " + board.getPlayer2Name() + "'s score: "
						+ board.getPlayer2Score() + ")");
				// System.out.println("Game won by " + board.getPlayer2Name());
			}
			newGame.setDisable(false);
			forceEnd.setDisable(true);

			// deals with the scenario in which the game ends as a result of a
			// draw and displays the final scores.
		} else if (board.gameDrawCheck()) {
			// System.out.println("game drawn, disable UI");

			this.setCenter(gameStatus);
			setAlignment(gameStatus, Pos.CENTER);
			gameStatus.setStyle(
					"-fx-text-fill: #fff; -fx-effect: dropshadow(three-pass-box, rgba(255,255,255,0.5), 10, 0, 0, 0);"
							+ "-fx-font-size: 25pt; -fx-opacity: 0;-fx-font-smoothing-type: gray;");
			fadeShow(gameStatus);
			gameStatus.setText("Game drawn\n(" + board.getPlayer1Name() + "'s score: " + board.getPlayer1Score() + ", "
					+ board.getPlayer2Name() + "'s score: " + board.getPlayer2Score() + ")");
			// this.setBottom(gameStatus);

			newGame.setDisable(false);
			forceEnd.setDisable(true);
		}
	}

}

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

	public BoardView(Board board) {
		super();

		setPadding(new Insets(0, 20, 0, 20));
		setStyle("-fx-background-color: #363338");

		setUpTop();
		gameStatus = new Label();
		centerPane = new VBox(20);
		centerPane.setPadding(new Insets(20, 0, 0, 0));
		//
		this.board = board;
		housesGrid = new GridPane();

		housesGrid.setHgap(5);
		housesGrid.setVgap(8);
		housesGrid.setAlignment(Pos.CENTER);

		makeGrid();
		//
		playerView1 = new PlayerView(0, board.getPlayer1Name(), board.getPlayerTurn());
		playerView2 = new PlayerView(1, board.getPlayer2Name(), board.getPlayerTurn());

		nameDialogue();

		if (board.isPlayingComputer()) {
			updateBoard();
		}
		//
		centerPane.getChildren().addAll(playerView1, housesGrid, playerView2);

		this.setCenter(centerPane);
	}

	private void formatButton(Button b) {
		b.setTextFill(Color.web("#ffffff"));
		b.setStyle(
				"-fx-background-color: #eb505d; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0)");
	}

	private void setUpTop() {
		BorderPane borderTop = new BorderPane();

		borderTop.setPadding(new Insets(20, 0, 0, 0));

		newGame = new Button("New Game");
		newGame.setFocusTraversable(false);
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

		endGame.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Stage stage = (Stage) endGame.getScene().getWindow();
				// do what you have to do
				stage.close();
			}
		});
		formatButton(endGame);

		HBox leftHBox = new HBox(10);
		leftHBox.getChildren().addAll(newGame, mainScreen);

		HBox rightHBox = new HBox(10);
		rightHBox.getChildren().addAll(forceEnd, endGame);

		borderTop.setLeft(leftHBox);
		borderTop.setRight(rightHBox);

		this.setTop(borderTop);
	}

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

	private void updateBoard() {
		if (board.isGameStarted()) {
			newGame.setDisable(false);
		} else {
			newGame.setDisable(true);
		}
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
		playerView1.update(board.getPlayer1Score(), board.getPlayerTurn());
		playerView2.update(board.getPlayer2Score(), board.getPlayerTurn());
		checkGameFinished();
		if (board.gameOverNoMovesPossible()) { // required for Jay's part
			board.captureOwnSeeds();
			for (int i = 0; i < 2; ++i) {
				for (int j = 0; j < 6; ++j) {
					houses[i][j].addSeeds(board.getHouseOnBoard(i, j).getCount());
				}
			}
			checkGameFinished();
		}
	}

	private void killWindow() {
		this.getScene().getWindow().hide();
	}

	private void nameDialogue() {
		// Create the custom dialog.
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("Oware - Set player names (optional)");

		// Set the icon (must be included in the project).

		// Set the button types.
		ButtonType okButton = new ButtonType("Ok", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(okButton, ButtonType.CANCEL);

		// Set Style
		dialog.getDialogPane().setStyle("-fx-background-color: #363338");
		dialog.getDialogPane().lookupButton(ButtonType.CANCEL).setStyle("-fx-background-color: #eb505d;"
				+ " -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);-fx-text-fill: #fff");
		dialog.getDialogPane().lookupButton(okButton).setStyle("-fx-background-color: #eb505d;"
				+ " -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);-fx-text-fill: #fff");

		// Create the username and password labels and fields.
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

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

	private void updatePlayerNames() {
		playerView1.updatePlayerName(board.getPlayer1Name());
		playerView2.updatePlayerName(board.getPlayer2Name());
	}

	private void fadeHide(Node node) {
		FadeTransition fadeNode = new FadeTransition(Duration.millis(500), node);
		fadeNode.setFromValue(1);
		fadeNode.setToValue(0);
		fadeNode.play();
	}

	private void fadeShow(Node node) {
		FadeTransition fadeNode = new FadeTransition(Duration.millis(500), node);
		fadeNode.setFromValue(0);
		fadeNode.setToValue(1);
		fadeNode.play();
	}

	private void checkGameFinished() {

		if (board.gameWonCheck()) {
			fadeHide(centerPane);
			System.out.println("game over, disable UI");
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

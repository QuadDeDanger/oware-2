package view;

import java.util.Optional;

import functionality.Board;
import functionality.ComputerPlayer;
import functionality.Player;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Pair;

public class BoardView extends BorderPane {

	private VBox centerPane;
	private GridPane housesGrid;
	private Board board;
	private HouseView[][] houses;
	private PlayerView playerView1;
	private PlayerView playerView2;

	public BoardView(Board board) {
		super();

		setPadding(new Insets(20, 20, 20, 20));
		centerPane = new VBox(20);
		centerPane.setPadding(new Insets(20, 0, 0, 0));
		//
		this.board = board;
		housesGrid = new GridPane();

		housesGrid.setPadding(new Insets(10, 10, 10, 10));
		housesGrid.setHgap(2);
		housesGrid.setVgap(100);

		for (int row = 0; row < 2; row++) {
			RowConstraints rowConstraints = new RowConstraints();
			rowConstraints.setPercentHeight(100.0 / 2);
			housesGrid.getRowConstraints().add(rowConstraints);
		}
		for (int col = 0; col < 6; col++) {
			ColumnConstraints columnConstraints = new ColumnConstraints();
			columnConstraints.setPercentWidth(100.0 / 6);
			housesGrid.getColumnConstraints().add(columnConstraints);
		}

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

		Label welcomeLabel = new Label("Oware");
		welcomeLabel.setFont(new Font("Arial", 30));
		welcomeLabel.setMaxWidth(Double.MAX_VALUE);
		welcomeLabel.setAlignment(Pos.CENTER);
		welcomeLabel.setPadding(new Insets(20, 0, 20, 20));

		HBox buttons = new HBox(10);
		buttons.setMaxWidth(Double.MAX_VALUE);
		buttons.setAlignment(Pos.CENTER);
		Button newGame = new Button("New Game");

		newGame.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				Stage stage = new Stage();
				stage.setTitle("Oware");

				Scene scene = new Scene(new WelcomeView(), 500, 300);
				stage.setScene(scene);
				stage.show();

				((Node) event.getSource()).getScene().getWindow().hide();

			}
		});

		Button forceEnd = new Button("Force end");
		forceEnd.setDisable(true);
		Button quitGame = new Button("Exit Game");

		quitGame.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Stage stage = (Stage) quitGame.getScene().getWindow();
				// do what you have to do
				stage.close();
			}
		});

		buttons.getChildren().addAll(newGame, forceEnd, quitGame);

		VBox topBox = new VBox();
		topBox.getChildren().addAll(welcomeLabel, buttons);

		this.setTop(topBox);

	}

	private void makeGrid() {
		houses = new HouseView[2][6];
		System.out.println(board.getPlayerTurn());
		for (int i = 0; i < 2; ++i) {
			for (int j = 0; j < 6; ++j) {
				houses[i][j] = new HouseView();
				houses[i][j].setSeeds(board.getHouseCount(i, j));
				int x = i;
				int y = j;
				houses[i][j].setOnMouseClicked(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent arg0) {
						if (board.getPlayerTurn() == x) {
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
		for (int i = 0; i < 2; ++i) {
			for (int j = 0; j < 6; ++j) {
				houses[i][j].setSeeds(board.getHouseOnBoard(i, j).getCount());
			}
		}
		playerView1.update(board.getPlayer1Score(), board.getPlayerTurn());
		playerView2.update(board.getPlayer2Score(), board.getPlayerTurn());
		checkGameFinished();
	}

	private void nameDialogue() {
		// Create the custom dialog.
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("Oware - Set player names (optional)");

		// Set the icon (must be included in the project).

		// Set the button types.
		ButtonType okButton = new ButtonType("Ok", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(okButton, ButtonType.CANCEL);

		// Create the username and password labels and fields.
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		TextField player1 = new TextField();
		if (board.isPlayingComputer()) {
			player1.setDisable(true);
			player1.setText("Computer");
		} else {

			player1.setPromptText("Player 1");
		}

		TextField player2 = new TextField();
		player2.setPromptText("Player 2");

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
		}
	}

	private void updatePlayerNames() {
		playerView1.updatePlayerName(board.getPlayer1Name());
		playerView2.updatePlayerName(board.getPlayer2Name());
	}

	private void checkGameFinished() {
		if (board.gameWonCheck()) {
			if (board.getPlayer1Score() > board.getPlayer2Score()) {
				this.setBottom(new Label("Game won by " + board.getPlayer1Name()));
			} else {
				this.setBottom(new Label("Game won by " + board.getPlayer2Name()));
			}
		} else if (board.gameDrawCheck()) {
			this.setBottom(new Label("Game drawn"));
		}
	}

}
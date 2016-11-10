package view;

import java.util.Optional;

import functionality.Board;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
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

	   	setPadding(new Insets(20,20,20,20));
		centerPane = new VBox(20);
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

}
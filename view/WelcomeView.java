package view;

import functionality.AIComputerPlayer;
import functionality.BasicComputerPlayer;
import functionality.Board;
import functionality.Player;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class WelcomeView extends BorderPane {

	public WelcomeView() {
		setPadding(new Insets(20, 0, 20, 0));
		setStyle("-fx-background-color: #363338");
		setUp();
	}

	private void formatButton(Button b){
        b.setTextFill(Color.web("#ffffff"));
        b.setStyle("-fx-background-color: #eb505d; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0)");
    }

	private void setUp() {
		VBox vbButtons = new VBox();
		vbButtons.setSpacing(10);
		vbButtons.setPadding(new Insets(0, 20, 10, 20));

		Button singlePlayerButton = new Button("Play single player");
		singlePlayerButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				Stage stage = new Stage();
				stage.setTitle("Oware");

				BasicComputerPlayer player1 = new BasicComputerPlayer();
				Player player2 = new Player("Player");
				Board board = new Board(player1, player2); // isComputer

				try {
					StackPane stack = new StackPane();
					stack.getChildren().add(new BoardView(board));

					Scene scene = new Scene(stack, 800, 420);
					stage.setScene(scene);
					stage.show();

					((Node) event.getSource()).getScene().getWindow().hide();
				}catch (NullPointerException e){
					//Continue as user cancelled opening window so nothing needs to be done
				}
			}
		});

		Button singePlayerAdvancedButton = new Button("Play single player (advanced)");
		singePlayerAdvancedButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				Stage stage = new Stage();
				stage.setTitle("Oware");

				AIComputerPlayer player1 = new AIComputerPlayer();
				Player player2 = new Player("Player");
				Board board = new Board(player1, player2); // isComputer

				try {
					StackPane stack = new StackPane();
					stack.getChildren().add(new view.BoardView(board));

					Scene scene = new Scene(stack, 800, 420);
					stage.setScene(scene);
					stage.show();

					((Node) event.getSource()).getScene().getWindow().hide();
				}catch (NullPointerException e){
					//Continue as user cancelled opening window so nothing needs to be done
				}
			}
		});

		Button twoPlayerButton = new Button("Play two-player");
		twoPlayerButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Stage stage = new Stage();
				stage.setTitle("Oware");

				Player player1 = new Player("Player 1");
				Player player2 = new Player("Player 2");
				Board board = new Board(player1, player2);

				try {
					StackPane stack = new StackPane();
					stack.getChildren().add(new view.BoardView(board));

					Scene scene = new Scene(stack, 800, 420);
					stage.setScene(scene);
					stage.show();

					((Node) event.getSource()).getScene().getWindow().hide();
				}catch (NullPointerException e){
					//Continue as user cancelled opening window so nothing needs to be done
				}
			}
		});

		singlePlayerButton.setMaxWidth(Double.MAX_VALUE);
		singlePlayerButton.setPadding(new Insets(15, 0, 15, 0));

		singePlayerAdvancedButton.setMaxWidth(Double.MAX_VALUE);
		singePlayerAdvancedButton.setPadding(new Insets(15, 0, 15, 0));
		
		twoPlayerButton.setMaxWidth(Double.MAX_VALUE);
		twoPlayerButton.setPadding(new Insets(15, 0, 15, 0));

        formatButton(singlePlayerButton);
        formatButton(singePlayerAdvancedButton);
        formatButton(twoPlayerButton);

		vbButtons.getChildren().addAll(singlePlayerButton, singePlayerAdvancedButton, twoPlayerButton);

		Label welcomeLabel = new Label("Oware");
		welcomeLabel.setFont(new Font("Arial", 30));
        welcomeLabel.setTextFill(Color.web("#ffffff"));
		welcomeLabel.setPadding(new Insets(20, 0, 20, 0));

		setTop(welcomeLabel);
		setCenter(vbButtons);
        setAlignment(welcomeLabel,Pos.CENTER);
	}
}

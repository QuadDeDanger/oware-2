package view;

import controller.PlayerModeEventHandler;
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
		singlePlayerButton.setOnAction(new PlayerModeEventHandler("basic"));

		Button singePlayerAdvancedButton = new Button("Play single player (advanced)");
		singePlayerAdvancedButton.setOnAction(new PlayerModeEventHandler("advanced"));

		Button twoPlayerButton = new Button("Play two-player");
		twoPlayerButton.setOnAction(new PlayerModeEventHandler("player"));

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

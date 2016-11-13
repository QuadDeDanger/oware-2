package view;

import controller.PlayerModeEventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * This class represents the welcome view which creates the board view according to the game mode selected by user.
 *
 * @author Aqib Rashid
 * @author Haaris Memon
 */
public class WelcomeView extends BorderPane {

	/**
	 * Constructs the welcome view with the game modes
	 */
	public WelcomeView() {
		setPadding(new Insets(20, 0, 20, 0));
		setStyle("-fx-background-color: #363338");
		setUp();
	}

	//sets the theme of the buttons
	private void formatButton(Button b){
        b.setTextFill(Color.web("#ffffff"));
        b.setStyle("-fx-background-color: #eb505d; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0)");
    }

    //sets up the view, adding buttons and their event handlers
	private void setUp() {
		VBox vbButtons = new VBox();
		vbButtons.setSpacing(10);
		vbButtons.setPadding(new Insets(0, 20, 10, 20));

		//button for basic single player game
		Button singlePlayerButton = new Button("Play single player");
		singlePlayerButton.setOnAction(new PlayerModeEventHandler("basic"));

		//button for the advanced single player
		Button singePlayerAdvancedButton = new Button("Play single player (advanced)");
		singePlayerAdvancedButton.setOnAction(new PlayerModeEventHandler("advanced"));

		//button for the two player game
		Button twoPlayerButton = new Button("Play two-player");
		twoPlayerButton.setOnAction(new PlayerModeEventHandler("player"));

		//sets the size and padding for all the buttons
		singlePlayerButton.setMaxWidth(Double.MAX_VALUE);
		singlePlayerButton.setPadding(new Insets(15, 0, 15, 0));

		singePlayerAdvancedButton.setMaxWidth(Double.MAX_VALUE);
		singePlayerAdvancedButton.setPadding(new Insets(15, 0, 15, 0));
		
		twoPlayerButton.setMaxWidth(Double.MAX_VALUE);
		twoPlayerButton.setPadding(new Insets(15, 0, 15, 0));

		//sets the theme for the buttons
        formatButton(singlePlayerButton);
        formatButton(singePlayerAdvancedButton);
        formatButton(twoPlayerButton);

		//add the buttons to the layout
		vbButtons.getChildren().addAll(singlePlayerButton, singePlayerAdvancedButton, twoPlayerButton);

		//sets the welcome title on the view
		Label welcomeLabel = new Label("Oware");
		welcomeLabel.setFont(new Font("Arial", 30));
        welcomeLabel.setTextFill(Color.web("#ffffff"));
		welcomeLabel.setPadding(new Insets(20, 0, 20, 0));
		
		setTop(welcomeLabel);
		setCenter(vbButtons);
        setAlignment(welcomeLabel,Pos.CENTER);
	}
}

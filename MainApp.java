import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.WelcomeView;

/**
 * This is the main class for the game.
 * @author Team Dragonfly
 *
 */

public class MainApp extends Application {

	/**
	 * Start the Javafx application
	 */
	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Oware"); // Title is Oware

		// A new WelcomeView scene which is the Main Menu
		Scene scene = new Scene(new WelcomeView(), 500, 300);
		stage.setMinWidth(500);
		stage.setMinHeight(350);
		stage.setScene(scene);
		stage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}

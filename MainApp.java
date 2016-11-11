import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.WelcomeView;

public class MainApp extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Oware");

		Scene scene = new Scene(new WelcomeView(), 500, 300);
		stage.setScene(scene);
		stage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}

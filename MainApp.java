import functionality.Board;
import functionality.Player;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.BoardView;

/**
 * Main class
 */

public class MainApp extends Application {

	@Override
	public void start(Stage primaryStage) {
		
		Player player1 = new Player("Player 1");
		Player player2 = new Player("Player 2");
		Board board = new Board(player1, player2);
		
		Scene scene = new Scene(new BoardView(board), 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
	}

}

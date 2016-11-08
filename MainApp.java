import functionality.Board;
import functionality.Player;

/**
 * Main class
 *
 */

public class MainApp {

	public static void main(String[] args) {

		Player player1 = new Player("Player 1");
		Player player2 = new Player("Player 2");
		Board board = new Board(player1, player2);
		board.print();
		System.out.println("after");
		board.sow(1, 5);
		board.print();

	}

}

import functionality.Board;
import functionality.Player;

/**
 * Main class
 */

public class MainApp {

	public static void main(String[] args) {

		Player player1 = new Player("Player 1");
		Player player2 = new Player("Player 2");
		Board board = new Board(player1, player2);
		System.out.println("Initial");
		board.print();
		System.out.println(player1.getName() + " score: " + player1.getScore() + ", " + player2.getName() + " score: "
				+ player2.getScore());

		board.strictlyTestMakeMove(player1, 0, 0);
		board.strictlyTestMakeMove(player2, 1, 5);
		board.strictlyTestMakeMove(player1, 0, 4);
		board.strictlyTestMakeMove(player2, 1, 4);
		board.strictlyTestMakeMove(player1, 0, 0);
		board.strictlyTestMakeMove(player2, 1, 2);
		System.out.println("Player 2 should have score 2 and house (0,4) should be empty");

	}

}

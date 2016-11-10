package functionality;

import java.util.Random;

/**
 * This class represents a (simple) Single player.
 * @author Aqib Rashid
 *
 */

public class ComputerPlayer extends Player {

	private Board board;

	/**
	 * Creates a new Player with name Computer
	 */
	public ComputerPlayer(Board board) {
		super("Computer");
		this.board = board;
	}

	/**
	 * Makes move to sow at random place
	 * @return the random house clicked on the first row
	 */
	public void makeMove() {
		// Assuming the computer will always be row 0
		board.sow(0, new Random().nextInt(6));
	}

}

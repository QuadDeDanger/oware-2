package functionality;

import java.util.Random;

/**
 * This class represents a (simple) Single player.
 * @author Aqib Rashid
 *
 */

public class ComputerPlayer extends Player {

	/**
	 * Creates a new Player with name Computer
	 */
	public ComputerPlayer() {
		super("Computer");
		// TODO Auto-generated constructor stub
	}

	/**
	 * Makes the random move
	 * @return the random house clicked on the first row
	 */
	// Assuming the computer will always be row 0
	public int makeMove() {

		return new Random().nextInt(6);

	}

}

package functionality;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

/**
 * This class represents a basic computer player in the single player mode.
 *
 * @author Aqib Rashid
 * @author Haaris Memon
 */

public class BasicComputerPlayer extends Player {

	private Board board;

	/**
	 * Creates a new Player with name Computer
	 */
	public BasicComputerPlayer() {
		super("Computer");
	}

	/**
	 * Set the board that the computer player is playing on
	 *
	 * @param board
	 *            that player is playing on
	 */
	public void setBoard(Board board) {
		this.board = board;
	}

	/**
	 * Returns the board that the computer player is playing on
	 *
	 * @return board computer player playing on
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * Generate and store the random integer position
	 * 
	 * @return
	 */
	private int generateRandomPosition() {
		return new Random().nextInt(6);
	}

	private boolean isSuitableMove(int position) {
		if ((getBoard().getHouseCount(0, position) == 0 || !board.willGiveOpponentSeeds(0, position))) {
			return false;
		}
		return true;
	}

	/**
	 * Makes move to sow at random position generated previously
	 * 
	 * @return the random house clicked on the first row
	 */
	public void makeMove() {
		int computerMove = generateRandomPosition();
		Set<Integer> indicesTried = new TreeSet<>();
		indicesTried.add(computerMove);
		
		boolean moveFound = isSuitableMove(computerMove);
		
		while (!moveFound) {
			computerMove = generateRandomPosition();
			indicesTried.add(computerMove);
			moveFound = isSuitableMove(computerMove);
			if (indicesTried.size() == 6) {
				break;
			}
		}


		System.out.println(
				"Computer chose " + computerMove + " which is size  " + getBoard().getHouseCount(0, computerMove));

		if (!moveFound) {
			board.setGameIsOver(true);
		} else {
			board.sow(0, computerMove);
		}

	}

}

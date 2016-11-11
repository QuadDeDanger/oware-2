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
	private int randomPosition;

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
	public int generateAndStoreRandomPosition() {
		randomPosition = new Random().nextInt(6);
		return randomPosition;
	}

	/**
	 * Makes move to sow at random position generated previously
	 * 
	 * @return the random house clicked on the first row
	 */
	public void makeMove() {
		generateAndStoreRandomPosition();
		Set<Integer> indicesTried = new TreeSet<>();
		while (getBoard().getHouseOnBoard(0, randomPosition).getCount() == 0 || !board.willGiveOpponentSeeds(0, randomPosition)) {
			generateAndStoreRandomPosition();
			if(!indicesTried.contains(randomPosition)){
				indicesTried.add(randomPosition);
			}
			
		}

		// Assuming the computer will always be row 0
		
		if(indicesTried.size() == 6){
			board.setGameIsOver(true);
		}
		else {
			board.sow(0, randomPosition);
		}
		
	}

}

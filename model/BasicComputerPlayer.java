package model;

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
	 * Creates a new Player with name Computer by calling parent constructor
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
	 * Generate a random integer position between 0 and 5 inclusive
	 * 
	 * @return a random number for an x position on a row
	 */
	private int generateRandomPosition() {
		return new Random().nextInt(6);
	}

	/*
	 * Checks whether a move is suitable. If clicking a zero-seed house or the
	 * move will leave the opponent seed-less then return false
	 */
	private boolean isSuitableMove(int position) {
		if ((getBoard().getHouseCount(0, position) == 0 || !board.willGiveOpponentSeeds(0, position))) {
			return false;
		}
		return true;
	}

	/**
	 * Makes move to sow at random position generated using above method. Finds
	 * a suitable move using isSuitableMove. Once one is found the move is made.
	 */
	public void makeMove() {
		int computerMove = generateRandomPosition(); // generate a random number

		// use this to hold indices tried
		Set<Integer> indicesTried = new TreeSet<>();
		indicesTried.add(computerMove);

		// check whether move is suitable
		boolean moveFound = isSuitableMove(computerMove);

		// if move wasn't suitable, try to find another
		while (!moveFound) {
			computerMove = generateRandomPosition();
			indicesTried.add(computerMove);
			moveFound = isSuitableMove(computerMove);
			if (indicesTried.size() == 6) {
				// since we've now tried 0-5, we know there is no more move
				break;
			}
		}

		// System.out.println( "Computer chose " + computerMove + " which is
		// size " + getBoard().getHouseCount(0, computerMove));

		if (!moveFound) { // we couldn't find a move, so end the game
			board.setGameIsOver(true);
		} else { // otherwise sow
			board.sow(0, computerMove);
		}

	}

}

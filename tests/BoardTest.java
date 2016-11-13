package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import model.*;

/**
 * This class contains unit tests for the Board class.
 * 
 * @author Aqib Rashid
 *
 */

public class BoardTest {

	@Test
	public void creatingBoardCreates2By6ArrayOfHousesWith4SeedsInEach() {
		Board board = new Board(new Player("Player 1"), new Player("Player 2"));

		boolean isCorrect = true;

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 6; j++) {
				if (board.getHouseCount(i, j) != 4) {
					isCorrect = false;
				}
			}
		}

		assertEquals("Should be 2x6 array with 4 seeds each", true, isCorrect);
	}

	@Test
	public void creatingBoardAddsTwoPlayersWithZeroScore() {
		Player alice = new Player("Alice");
		Player bob = new Player("Bob");
		Board board = new Board(alice, bob);

		assertEquals("Should be 2 players", true, board.getPlayer1Name() != null && board.getPlayer2Name() != null);
		assertEquals("Player 1 should be Alice", "Alice", board.getPlayer1Name());
		assertEquals("Player 2 should be Bob", "Bob", board.getPlayer2Name());
		assertEquals("Player 1 and 2 have zero secore", true,
				board.getPlayer1Score() == 0 && board.getPlayer2Score() == 0);
	}

	/*
	 * NB: this is quite a difficult test to perform and even then it isn't
	 * entirely exhaustive
	 */
	@Test
	public void firstTurnIsRandomised() {

		Board board1 = new Board(new Player("Player 1"), new Player("Player 2"));
		List<Integer> turns1 = new ArrayList<>();
		turns1.add(0);
		turns1.add(1);
		assertEquals("First turn is in the array", true, turns1.contains(board1.getPlayerTurn()));

		Board board2 = new Board(new Player("Player 1"), new Player("Player 2"));
		List<Integer> turns2 = new ArrayList<>();
		turns2.add(0);
		turns2.add(1);
		assertEquals("First turn is in the array", true, turns2.contains(board2.getPlayerTurn()));

	}

	@Test
	public void gameIsSetAsOverWhenSet() {
		Board board = new Board(new Player("Player 1"), new Player("Player 2"));
		board.setGameIsOver(true);
		assertEquals("Game over when set", true, board.gameOverNoMovesPossible());

	}

	@Test
	public void gameNotOverWhenNotSet() {
		Board board = new Board(new Player("Player 1"), new Player("Player 2"));
		assertEquals("Game not over when not set", false, board.gameOverNoMovesPossible());
	}

	@Test
	public void sowingIsCorrect() {
		Board board = new Board(new Player("Player 1"), new Player("Player 2"));
		board.sow(0, 0);
		boolean isCorrect = true;
		for (int j = 0; j < 4; j++) {
			if (board.getHouseCount(1, j) != 5) {
				isCorrect = false;
			}
		}

		assertEquals("After sowing (0, 0), (1, 0-3) contains 5", true, isCorrect);

		board.sow(1, 1);
		boolean isCorrect2 = true;
		if (board.getHouseCount(1, 2) != 6 || board.getHouseCount(1, 3) != 6 || board.getHouseCount(1, 4) != 5
				|| board.getHouseCount(1, 5) != 5) {
			isCorrect2 = false;
		}

		assertEquals("After sowing (0, 0), (1, 0-3) contains 5", true, isCorrect2);

		Board board2 = new Board(new Player("Player 1"), new Player("Player 2"));
		board2.sow(1, 5);
		boolean isCorrect3 = true;
		for (int j = 2; j < 6; j++) {
			if (board2.getHouseCount(0, j) != 5) {
				isCorrect3 = false;
			}
		}

		assertEquals("After sowing (1, 5), (0, 2-5) contains 5", true, isCorrect3);
		assertEquals("After sowing (1,5), row 1 has 28 and row 2 has 20", true,
				board2.getNumSeedsOnRow(0) == 28 && board2.getNumSeedsOnRow(1) == 20);
	}

	@Test
	public void getNextgetPreviousHousesAreCorrect() {
		Board board = new Board(new Player("Player 1"), new Player("Player 2"));

		House start = board.getHouseOnBoard(0, 0);
		House target = board.getHouseOnBoard(1, 0);

		assertEquals("Next house of (0,0) is (1,0)", true, board.getNextHouse(start) == target);

		House start2 = board.getHouseOnBoard(1, 4);
		House target2 = board.getHouseOnBoard(1, 5);

		assertEquals("Next house of (1,4) is (1,5)", true, board.getNextHouse(start2) == target2);

		House start3 = board.getHouseOnBoard(1, 0);
		House target3 = board.getHouseOnBoard(0, 0);

		assertEquals("Previous house of (1,0) is (0,0)", true, board.getPreviousHouse(start3) == target3);

		House start4 = board.getHouseOnBoard(1, 5);
		House target4 = board.getHouseOnBoard(1, 4);

		assertEquals("Previous house of (1,5) is (1,4)", true, board.getPreviousHouse(start4) == target4);

	}

	@Test
	public void captureOwnSeedsIsCorrect() {
		Board board = new Board(new Player("Player 1"), new Player("Player 2"));
		board.captureOwnSeeds();
		assertEquals("Each player has 24", true, board.getPlayer1Score() == 24 && board.getPlayer2Score() == 24);
	}

	@Test
	public void resetBoardIsCorrect() {
		Board board = new Board(new Player("Player 1"), new Player("Player 2"));
		board.resetBoard();

		boolean isCorrect = true;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 6; j++) {
				if (board.getHouseCount(i, j) != 4) {
					isCorrect = false;
				}
			}
		}

		assertEquals("Each house has 4 seeds", true, isCorrect);
		assertEquals("Each player has 0 score", true, board.getPlayer1Score() == 0 && board.getPlayer2Score() == 0);
	}

}

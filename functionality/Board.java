package functionality;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class represents a board. Created by Haaris on 08/11/2016.
 * 
 * @author Haaris Memon
 * @author Aqib Rashid
 * @author Jaydene Green-Stevens
 */
public class Board {

	private House[][] board;
	private Player player1;
	private Player player2;

	/**
	 * Sets up a board and initialises it
	 * 
	 * @param player1
	 *            first player
	 * @param player2
	 *            second player
	 */
	public Board(Player player1, Player player2) {
		board = new House[2][6];
		this.player1 = player1;
		this.player2 = player2;
		setFirstTurn();

		initialiseBoard();
	}

	private void setFirstTurn() {
		Random rand = new Random();
		int whichPlayer = rand.nextInt(2);

		if(whichPlayer == 0) {
			player1.setIsPlayersTurn(true);
			player2.setIsPlayersTurn(false);
		} else if (whichPlayer == 1) {
			player1.setIsPlayersTurn(false);
			player2.setIsPlayersTurn(true);
		}
	}

	private void initialiseBoard() {
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 6; j++) {
				board[i][j] = new House(i, j);
			}
		}
	}

	/**
	 * Performs the sowing action (a move)
	 * 
	 * @param i
	 *            the x coordinate of the seed clicked on
	 * @param j
	 *            the y coordinate of the seed clicked on
	 */
	public void sow(int i, int j) {
		List<Seed> toSow = board[i][j].getSeedsAndEmptyHouse(); // get the list
																// of seeds and
																// clear that
																// house

		House currentHouse = board[i][j]; // get the current house
		for (int index = 0; index < toSow.size(); ++index) {
			currentHouse = getNextHouse(currentHouse); // get the next one
			/*
			 * 12-seed rule: if are sowing more than 12 seeds, we don't want to
			 * replant in the starting house
			 */
			if (currentHouse.equals(board[i][j])) {
				currentHouse = getNextHouse(currentHouse); // so we skip
			}
			currentHouse.addSeedInPot(toSow.get(index)); // sow a seed
		}

		// start capture from the last house
		capture(currentHouse.getXPos(), currentHouse.getYPos());

	}

	// Get next house by checking which row. If first, we go backwards, if
	// second we go forwards
	private House getNextHouse(House house) {
		int currentX = house.getXPos();
		int currentY = house.getYPos();

		if (currentX == 0) {
			if (currentY == 0) {
				return board[currentX + 1][currentY];
			}
			return board[currentX][currentY - 1];

		} else {
			if (currentY == 5) {
				return board[currentX - 1][currentY];
			}
			return board[currentX][currentY + 1];

		}

	}

	// Start from the last house and work backwards/forwards depending on row
	private void capture(int x, int y) {

		House currentHouse = board[x][y];

		if (x == 0) { // player 2 made the last move
			captureHelper(player2, currentHouse);
		} else { // player 1 made the last move
			captureHelper(player1, currentHouse);

		}

	}

	// Get next house by checking which row. If first, we go backwards, if
	// second we go forwards
	private House getPreviousHouse(House house) {
		int currentX = house.getXPos();
		int currentY = house.getYPos();

		if (currentX == 0) {
			if (currentY == 5) {
				return board[currentX + 1][currentY];
			}
			return board[currentX][currentY + 1];

		} else {
			if (currentY == 0) {
				return board[currentX - 1][currentY];
			}
			return board[currentX][currentY - 1];

		}

	}

	private void captureHelper(Player player, House lastHouse) {
		List<House> toCapture = new ArrayList<>();
		// capture only if 2 or 3
		if (lastHouse.getCount() == 2 || lastHouse.getCount() == 3) {

			toCapture.add(lastHouse); // add house to list of houses for now

			House previousHouse = getPreviousHouse(lastHouse); // get previous
																// house
			for (int j = 0; j < 6; j++) {
				// if on same row and has size 2 or 3
				if (previousHouse.getXPos() == lastHouse.getXPos()
						&& (previousHouse.getCount() == 2 || previousHouse.getCount() == 3)) {
					toCapture.add(previousHouse); // add to capture
					previousHouse = getPreviousHouse(previousHouse);
				} else { // quit the loop
					break;
				}
			}

		}

		if (toCapture.size() > 0) { // Only go through if we have something to
									// capture
			int capturedSeedTotal = 0;
			for (House capturedHouse : toCapture) {
				capturedSeedTotal += capturedHouse.getCount();
			}

			int totalOnRow = 0;

			for (int j = 0; j < 6; j++) {
				totalOnRow += board[lastHouse.getXPos()][j].getCount();
			}

			if (capturedSeedTotal != totalOnRow) { // if the opponent now has no
													// more seeds, then forfeit
													// capture

				for (House house : toCapture) {
					List<Seed> toAddToScoreHouse = house.getSeedsAndEmptyHouse();
					for (Seed seed : toAddToScoreHouse) { // add each to the
															// score
															// house
						player.addSeedToHouse(seed);
					}
				}

			}
		}
	}
	
	public boolean gameWonCheck() {

		if (player1.getScore() >= 25 || player2.getScore() >= 25) {
			return true;
		}
		return false;
	}

	public boolean gameDrawCheck() {
		if (player1.getScore() == 24 && player2.getScore() == 24) {
			return true;
		}
		return false;
	}

	// Strictly for debugging. This mustn't be used in the game. Remove soon!
	public void strictlyTestMakeMove(Player player, int i, int j) {
		System.out.println(" ");
		sow(i, j);
		System.out.println(player.getName() + ":  After sowing (" + i + "," + j + ")");
		print();
		System.out.println(player1.getName() + " score: " + player1.getScore() + ", " + player2.getName() + " score: "
				+ player2.getScore());
	}

	// For debugging only
	public void print() {
		for (int i = 0; i < 2; ++i) {
			for (int j = 0; j < 6; ++j) {
				System.out.print(board[i][j].getCount() + " ");
			}
			System.out.println(" ");
		}
	}

}

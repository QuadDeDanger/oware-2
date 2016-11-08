package functionality;

import java.util.List;

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
	 * @param player1 first player
	 * @param player2 second player
	 */
	public Board(Player player1, Player player2) {
		board = new House[2][6];
		this.player1 = player1;
		this.player2 = player2;
		initialiseBoard();
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
	 * @param i the x coordinate of the seed clicked on
	 * @param j the y coordinate of the seed clicked on
	 */
	public void sow(int i, int j) {
		List<Seed> toSow = board[i][j].getSeedsAndEmptyHouse(); // get the list of seeds and clear that house

		House currentHouse = board[i][j]; // get the current house
		for (int index = 0; index < toSow.size(); ++index) {
			currentHouse = getNextHouse(currentHouse); // get the next one
			/*
			 * 12-seed rule: if are sowing more than 12 seeds, we don't want to replant in the starting house
			 */
			if (currentHouse == board[i][j]) { 
				currentHouse = getNextHouse(currentHouse); // so we skip
			}
			currentHouse.addSeedInPot(toSow.get(index)); // sow a seed
		}

	}

	// Get next house by checking which row. If first, we go backwards, if second we go forwards
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

	public void capture() {

	}

	public void letOpponentPlay() {

	}

	public void gameWonCheck() {

	}

	// For debugging only
	public void print() {
		for (int i = 0; i < 2; ++i) {
			for (int j = 0; j < 6; ++j) {
				System.out.print(board[i][j].getCount() + " ");
			}
			System.out.println("\n");
		}
	}

}

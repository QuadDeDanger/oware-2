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
	private Player player1; // will be the computer
	private Player player2;
	private boolean isPlayingComputer;
	private ComputerPlayer computerPlayer;
	private boolean gameStarted;

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

	public Board(Player player1, Player player2, boolean isComputer) {
		board = new House[2][6];
		this.player1 = player1;
		this.player2 = player2;
		this.isPlayingComputer = isComputer;
		setFirstTurn();
		initialiseBoard();
		playingComputer();

	}

	private void playingComputer(){
		if (isPlayingComputer) {
			computerPlayer = (ComputerPlayer) player1;
			if (getPlayerTurn() == 0) {
				sow(0, computerPlayer.makeMove());
			}
		}
	}
	
	public boolean isPlayingComputer() {
		return isPlayingComputer;
	}

	private void setFirstTurn() {
		Random rand = new Random();
		int whichPlayer = rand.nextInt(2);

		if (whichPlayer == 0) {
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
	 * Method to ensure, that if the opponent has no seeds, only moves which
	 * would increase... ...the number of opponents seeds are allowed.
	 * 
	 * @param x
	 *            - x coordinate of the house
	 * @param y
	 *            - y coordinate of the house
	 * @return boolean - Shows whether or not this house's seeds can be
	 *         collected and sewn
	 * @author Jay
	 */
	public boolean canSow(int x, int y) {
		int opponentRow;
		if (x == 0) {
			// top row is players row
			opponentRow = 1;
		} else {
			// bottom row is players row (x == 1)
			opponentRow = 0;
		}

		// checks the number of seeds on the opponents row
		int totalSeeds = 0;
		for (int col = 0; col < 6; col++) {
			totalSeeds += board[opponentRow][col].getCount();
		}

		if (totalSeeds > 0) {
			return true;
		} else {
			// boolean canGiveSeeSds = true; // checks all player moves to see
			// if
			int numHousesCanGiveSeeds = 0;
			// any give the opponent seeds
			int numHousesAway = 6; // keeps a count of the number of seeds the
									// house must have
			for (int col = 0; col < 6; col++) {
				if ((board[x][col].getCount() >= numHousesAway)) {
					// canGiveSeeds = false;
					numHousesCanGiveSeeds++;
				}
				numHousesAway--;
			}

			if (numHousesCanGiveSeeds > 1) { // if the seeds can be given - you
												// need to check
				// this specific move

				int numSeedsNeeded;
				if (opponentRow == 1) {
					// number of seeds needs to be +1 of column index
					numSeedsNeeded = (y + 1);
				} else {
					// opponent row is bottom
					numSeedsNeeded = (6 - y);
				}
				if (board[x][y].getCount() >= numSeedsNeeded) {
					//System.out.println("valid move");
					return true;
				} else {
					//System.out.println("choose another seed");
				}

			} else {
				//System.out.println("No moves possible - END GAME");
				// end game
			}
		}
		return false;

	}

	/**
	 * Gets the House at the coordinate (i,j)
	 *
	 * @param i
	 *            coordinate
	 * @param j
	 *            coordinate
	 * @return House at the coordinate
	 */
	public House getHouseOnBoard(int i, int j) {
		return board[i][j];
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
		// System.out.println(i + " " + j);
		if (board[i][j].getCount() != 0) {
			if (canSow(i, j)) {
				gameStarted = true;

				// Get list of seeds and clear house
				List<Seed> toSow = board[i][j].getSeedsAndEmptyHouse();

				House currentHouse = board[i][j]; // get the current house
				for (int index = 0; index < toSow.size(); ++index) {
					currentHouse = getNextHouse(currentHouse); // get the next
																// one
					/*
					 * 12-seed rule: if are sowing more than 12 seeds, we don't
					 * want to replant in the starting house
					 */
					if (currentHouse.equals(board[i][j])) {
						currentHouse = getNextHouse(currentHouse); // so we skip
					}
					currentHouse.addSeedInPot(toSow.get(index)); // sow a seed
				}

				// start capture from the last house
				capture(currentHouse.getXPos(), currentHouse.getYPos(), getPlayerTurn());

				// switches the players turns

				player1.setIsPlayersTurn(!player1.getIsPlayersTurn());
				player2.setIsPlayersTurn(!player2.getIsPlayersTurn());

				if (isPlayingComputer && getPlayerTurn() == 0) {
					int computerMove = computerPlayer.makeMove();
					if (board[0][computerMove].getCount() == 0 && canSow(0, computerMove)) {
						computerMove = computerPlayer.makeMove();
					}
					sow(0, computerMove);
				}
			}
		}

	}

	public boolean isGameStarted(){
		return gameStarted;
	}
	
	// Start from the last house and work backwards/forwards depending on row
	private void capture(int x, int y, int playerTurn) {

		House currentHouse = board[x][y];

		if (playerTurn == 1) { // player 2 made the last move
			captureHelper(player2, currentHouse, 1);

		} else { // player 1 made the last move
			captureHelper(player1, currentHouse, 0);

		}

	}

	private void captureHelper(Player lastPlayer, House lastHouse, int playerNumber) {
		List<House> toCapture = new ArrayList<>();
		// capture only if 2 or 3

		// System.out.println("lastHouse " + lastHouse.getXPos() + " lastPlayer " + playerNumber);

		if (lastHouse.getXPos() != playerNumber && (lastHouse.getCount() == 2 || lastHouse.getCount() == 3)) {

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
						seed.setIsCaptured(true);
						lastPlayer.addSeedToHouse(seed);
					}
				}

			}
		}
	}

	public int getPlayerTurn() {
		if (player1.getIsPlayersTurn()) {
			return 0;
		}
		return 1;
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

	/**
	 * Check to see if game has won
	 *
	 * @return true if game has won
	 */
	public boolean gameWonCheck() {

		if (player1.getScore() > 24 || player2.getScore() > 24) {
			return true;
		}
		return false;
	}

	/**
	 * Check to see if the game was a draw
	 *
	 * @return true if the game was a draw
	 */
	public boolean gameDrawCheck() {
		if (player1.getScore() == 24 && player2.getScore() == 24) {
			return true;
		}
		return false;
	}

	public int getHouseCount(int i, int j) {
		return board[i][j].getCount();
	}

	public String getPlayer1Name() {
		return player1.getName();
	}

	public String getPlayer2Name() {
		return player2.getName();
	}

	public void setPlayer1Name(String name) {
		player1.setName(name);
	}

	public void setPlayer2Name(String name) {
		player2.setName(name);
	}

	public int getPlayer1Score() {
		return player1.getScore();
	}

	public int getPlayer2Score() {
		return player2.getScore();
	}

	public void captureOwnSeeds() {

		for (int j = 0; j < 6; j++) {

			List<Seed> toAddToPlayer1 = new ArrayList<>(board[0][j].getSeedsAndEmptyHouse());
			for (Seed seed : toAddToPlayer1) {
				seed.setIsCaptured(true);
				player1.addSeedToHouse(seed);
			}

			List<Seed> toAddToPlayer2 = new ArrayList<>(board[1][j].getSeedsAndEmptyHouse());
			for (Seed seed : toAddToPlayer2) {
				seed.setIsCaptured(true);
				player2.addSeedToHouse(seed);
			}

		}

	}

	public void resetBoard() {
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 6; j++) {
				board[i][j].resetHouse();
			}
		}
		player1.clearScoreHouse();
		player2.clearScoreHouse();
		gameStarted = false;
		setFirstTurn();
		playingComputer();
	}

}

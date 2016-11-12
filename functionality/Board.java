package functionality;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class represents a board which has a grid of houses, and 2 players.
 * 
 * @author Haaris Memon
 * @author Aqib Rashid
 * @author Jaydene Green-Stevens
 * @author Federico Midolo
 */
public class Board {

	private House[][] board;
	private Player player1; // will be the computer
	private Player player2;
	private boolean isPlayingComputer;
	private boolean gameStarted;
	private boolean gameOverNoMovesPossible;

	/**
	 * Sets up a board and initialises it
	 * 
	 * @param player1
	 *            first player
	 * @param player2
	 *            second player
	 */
	public Board(Player player1, Player player2) {
		//initialise the grid of houses
		board = new House[2][6];
		this.player1 = player1;
		this.player2 = player2;
		//randomly chooses who plays in the first turn
		setFirstTurn();

		//initialises the houses on the board
		initialiseBoard();

		if (player1 instanceof BasicComputerPlayer) {
			playingComputer();
		}
	}

	private void playingComputer() {
		if (player1 instanceof BasicComputerPlayer) {
			isPlayingComputer = true;
			((BasicComputerPlayer) player1).setBoard(this);
			if (getPlayerTurn() == 0) {
				((BasicComputerPlayer) player1).makeMove();
			}
		}
	}

	/**
	 * Returns if the computer is playing in the game
	 *
	 * @return boolean value if computer is playing (true if computer is playing)
	 */
	public boolean isPlayingComputer() {
		return isPlayingComputer;
	}

	//randomly chooses who plays in the first turn
	private void setFirstTurn() {
		Random rand = new Random();
		//random number generated either 0 or 1
		int whichPlayer = rand.nextInt(2);

		//player 1 starts
		if (whichPlayer == 0) {
			player1.setIsPlayersTurn(true);
			player2.setIsPlayersTurn(false);
		} else if (whichPlayer == 1) {
			player1.setIsPlayersTurn(false);
			player2.setIsPlayersTurn(true);
		}
	}

	//initialises all the houses on the board and sets the coordinates
	private void initialiseBoard() {
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 6; j++) {
				board[i][j] = new House(i, j);
			}
		}
	}

	/**
	 * Checks that the opponent will have some seeds at end
	 * 
	 * @author Aqib
	 * @author Jay
	 * @param i coorindate
	 * @param j coordinate
	 * @return true if opponent has seeds on their side
	 */
	public boolean willGiveOpponentSeeds(int i, int j) {
		int opponentRow = 0;
		if(i == 0){
			opponentRow = 1;
		}

		//if there are seeds on the opponents row then return true
		if (getNumSeedsOnRow(opponentRow) > 0) {
			return true;
		} else {
			//System.out.println(i + " must choose another move ");
			int numberToDistribute = board[i][j].getCount();
			House targetHouse = board[i][j];
			//moves the house to the target house
			for (int index = 0; index < numberToDistribute; index++) {
				targetHouse = getNextHouse(targetHouse);
			}
			//if the target house is on the opponents side then return true
			if (targetHouse.getXPos() != i) {
				return true;
			}
			checkValidMoves(i);
			return false;
		}
	}

	//checks if the move is valid
	private boolean checkValidMoves(int i) {
		for(int j=0; j<6; ++j) {
			//stores the number of seeds in the house
			int numberToDistribute = board[i][j].getCount();
			House targetHouse = board[i][j];
			//keep moving to next house as there are seeds
			for (int index = 0; index < numberToDistribute; index++) {
				targetHouse = getNextHouse(targetHouse);
			}
			if (targetHouse.getXPos() != i) {
				return true;
			}
		}
		captureOwnSeeds();
		setGameIsOver(true);
		return false;
	}

	/**
	 * Performs the sowing action (a move by a player)
	 * 
	 * @param i the x coordinate of the seed clicked on
	 * @param j the y coordinate of the seed clicked on
	 */
	public void sow(int i, int j) {
		//System.out.println("Last move by " + getPlayerTurn() + " was " + i + " " + j);
		// System.out.println(i + " " + j);
		if (board[i][j].getCount() != 0 && willGiveOpponentSeeds(i, j)) {
			gameStarted = true;

			// get list of seeds and empty house
			List<Seed> toSow = board[i][j].getSeedsAndEmptyHouse();

			House currentHouse = board[i][j]; // get the current house
			for (int index = 0; index < toSow.size(); ++index) {
				currentHouse = getNextHouse(currentHouse); // get the next one
				/* 12-seed rule: if sowing more than 12 seeds, we don't want
				 * to replant in the starting house
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

			if (player1 instanceof BasicComputerPlayer && getPlayerTurn() == 0) {
				//if player 1 is an AI Computer Player call its make move method
				if (player1 instanceof AIComputerPlayer) {
					((AIComputerPlayer) player1).makeMove();
				} else { //else it is basic computer player, calls its make move method
					((BasicComputerPlayer) player1).makeMove();
				}
			}
		}
	}

	/**
	 * Return the number of seeds currently on a particular row.
	 * 
	 * @param row - takes the row as input - should be either 1 or 0
	 * @return the number of seeds on that row
	 */
	public int getNumSeedsOnRow(int row) {
		int totalSeeds = 0;
		for (int col = 0; col < 6; col++) {
			totalSeeds += board[row][col].getCount();
		}
		return totalSeeds;
	}

	/**
	 * Get the House at the coordinate (i,j)
	 *
	 * @param i coordinate
	 * @param j coordinate
	 * @return House at the coordinate
	 */
	public House getHouseOnBoard(int i, int j) {
		return board[i][j];
	}

	/**
	 * Returns if the game has been started
	 *
	 * @return true if the game has started
	 */
	public boolean isGameStarted() {
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

		// System.out.println("lastHouse " + lastHouse.getXPos() + " lastPlayer
		// " + playerNumber);

		//if the house on the opponents side and it has 2 or 3 seeds
		if (lastHouse.getXPos() != playerNumber && (lastHouse.getCount() == 2 || lastHouse.getCount() == 3)) {
			// add house to list of houses for now
			toCapture.add(lastHouse);

			// get previous house
			House previousHouse = getPreviousHouse(lastHouse);
			for (int j = 0; j < 6; j++) {
				// if still on the opponents row and has size 2 or 3
				if (previousHouse.getXPos() == lastHouse.getXPos() && (previousHouse.getCount() == 2 || previousHouse.getCount() == 3)) {
					// add house to list to capture
					toCapture.add(previousHouse);
					//moves to the next previous house
					previousHouse = getPreviousHouse(previousHouse);
				} else { // quit the loop
					break;
				}
			}

		}

		// Only go through if we have something to capture (list not empty)
		if (toCapture.size() > 0) {
			//keeps track of how many seeds captured
			int capturedSeedTotal = 0;
			//for each house that is captured, add the seed count to the counter
			for (House capturedHouse : toCapture) {
				capturedSeedTotal += capturedHouse.getCount();
			}

			//keeps track of how many seeds on the row
			int totalOnRow = 0;
			//for each house on the row and the seed count to the counter (to check if opponent still has seeds)
			for (int j = 0; j < 6; j++) {
				totalOnRow += board[lastHouse.getXPos()][j].getCount();
			}

			// if the opponent now has no more seeds, then forfeit capture
			if (capturedSeedTotal != totalOnRow) {
				for (House house : toCapture) {
					//for each house get and empty the seeds
					List<Seed> toAddToScoreHouse = house.getSeedsAndEmptyHouse();
					// add each seed to the players score house
					for (Seed seed : toAddToScoreHouse) {
						seed.setIsCaptured(true);
						lastPlayer.addSeedToHouse(seed);
					}
				}
			}
		}
	}

	/**
	 * Checks whose turn it currently is
	 *
	 * @return int value for which player's turn it is
	 */
	public int getPlayerTurn() {
		if (player1.getIsPlayersTurn()) {
			return 0;
		}
		return 1;
	}

	/**
	 * Get next house in anticlockwise rotation by checking which row.
	 * If on first row, we go backwards, if on second row we go forwards.
	 *
	 * @param house to find next house of
	 * @return next house in the board
	 */
	public House getNextHouse(House house) {
		int currentX = house.getXPos();
		int currentY = house.getYPos();

		//on the first row
		if (currentX == 0) {
			//on the first column
			if (currentY == 0) {
				//move up
				return board[currentX + 1][currentY];
			} else {
				//else move to the left
				return board[currentX][currentY - 1];
			}

		} else { // on second row
			//on last column
			if (currentY == 5) {
				return board[currentX - 1][currentY];
			} else {
				//else move to the right
				return board[currentX][currentY + 1];
			}

		}

	}


	/**
	 * Get previous house in clockwise rotation by checking which row.
	 * If on first row, we go forwards, if on second row we go backwards.
	 *
	 * @param house to find previous house of
	 * @return previous house in the board
	 */
	public House getPreviousHouse(House house) {
		int currentX = house.getXPos();
		int currentY = house.getYPos();

		//on the first row
		if (currentX == 0) {
			//on the last column
			if (currentY == 5) {
				//move up
				return board[currentX + 1][currentY];
			} else {
				//else move to the right
				return board[currentX][currentY + 1];
			}

		} else { //on second row
			//first column
			if (currentY == 0) {
				//move down
				return board[currentX - 1][currentY];
			} else {
				//move left
				return board[currentX][currentY - 1];
			}

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

	/**
	 * Each player captures all the seeds on their own side
	 *
	 */
	public void captureOwnSeeds() {

		for (int j = 0; j < 6; j++) {

			//list of seeds for the house (that is emptied) on player 1 side
			List<Seed> toAddToPlayer1 = new ArrayList<>(board[0][j].getSeedsAndEmptyHouse());
			for (Seed seed : toAddToPlayer1) {
				//each seed is captured
				seed.setIsCaptured(true);
				//each seed is added to the the player 1 score house
				player1.addSeedToHouse(seed);
			}

			//list of seeds for the house (that is emptied) on player 2 side
			List<Seed> toAddToPlayer2 = new ArrayList<>(board[1][j].getSeedsAndEmptyHouse());
			for (Seed seed : toAddToPlayer2) {
				//each seed is captured
				seed.setIsCaptured(true);
				//each seed is added to the the player 2 score house
				player2.addSeedToHouse(seed);
			}
		}
	}

	/**
	 * Gets the number of seeds that are stored in a particular house
	 *
	 * @param i coordinate
	 * @param j coordinate
	 * @return number of seeds in the house
	 */
	public int getHouseCount(int i, int j) {
		return board[i][j].getCount();
	}

	/**
	 * Gets the player 1's  name
	 *
	 * @return name of player 1
	 */
	public String getPlayer1Name() {
		return player1.getName();
	}

	/**
	 * Gets the player 2's  name
	 *
	 * @return name of player 2
	 */
	public String getPlayer2Name() {
		return player2.getName();
	}

	/**
	 * Sets the player 1's name
	 *
	 * @param name of player
	 */
	public void setPlayer1Name(String name) {
		player1.setName(name);
	}

	/**
	 * Sets the player 2's name
	 *
	 * @param name of player
	 */
	public void setPlayer2Name(String name) {
		player2.setName(name);
	}

	/**
	 * Gets the player 1's  score
	 *
	 * @return score of player 1
	 */
	public int getPlayer1Score() {
		return player1.getScore();
	}

	/**
	 * Gets the player 2's  score
	 *
	 * @return score of player 2
	 */
	public int getPlayer2Score() {
		return player2.getScore();
	}

	/**
	 * Checks if the game is over
	 *
	 * @return true if game is over
	 */
	public boolean gameOverNoMovesPossible() {
		return gameOverNoMovesPossible;
	}

	/**
	 * Sets the game over boolean
	 * @param gameOver boolean to make the game over variable
	 */
	public void setGameIsOver(boolean gameOver) {
		gameOverNoMovesPossible = gameOver;
	}

	/**
	 * Resets the game board for a new game
	 */
	public void resetBoard() {
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 6; j++) {
				board[i][j].resetHouse();
			}
		}
		player1.clearScoreHouse();
		player2.clearScoreHouse();
		gameStarted = false;
		gameOverNoMovesPossible = false;
		setFirstTurn();
		playingComputer();
	}

}

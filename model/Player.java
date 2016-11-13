package model;

/**
 * This class represents a Player in the game. Minor changes by Aqib Rashid
 * 
 * @author Haaris Memon
 */
public class Player {

	private String name; // player's name
	private ScoreHouse scoreHouse; // player's scorehouse
	private boolean isPlayersTurn; // is true when it is player's turn

	/**
	 * Create Player with a name and initialised (empty) score house
	 *
	 * @param name
	 *            of the Player
	 */
	public Player(String name) {
		this.name = name;
		scoreHouse = new ScoreHouse();
	}

	/**
	 * Sets the name of the player
	 *
	 * @param name
	 *            of the Player
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the name of the Player
	 *
	 * @return name of the Player
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the Score House object that the Player has
	 *
	 * @return Score House that player has
	 */
	public ScoreHouse getScoreHouse() {
		return scoreHouse;
	}

	/**
	 * Gets the score of how many seeds the player has (which is the number of
	 * seeds in the score house)
	 *
	 * @return the player's score
	 */
	public int getScore() {
		return scoreHouse.getCount();
	}

	/**
	 * Adds a seed to the Player's Score House
	 *
	 * @param seed
	 *            to be inserted into player's score house
	 */
	public void addSeedToScoreHouse(Seed seed) {
		scoreHouse.addSeed(seed);
	}

	/**
	 * Returns if it is Player's turn
	 *
	 * @return true if it is Player's turn
	 */
	public boolean getIsPlayersTurn() {
		return isPlayersTurn;
	}

	/**
	 * Set if it is Player's turn
	 *
	 * @param isTurn
	 *            of the Player
	 */
	public void setIsPlayersTurn(boolean isTurn) {
		isPlayersTurn = isTurn;
	}

	/**
	 * Resets the player's scorehouse
	 */
	public void clearScoreHouse() {
		scoreHouse.reset();
	}

	/*
	 * Method used in debugging (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name;
	}

}

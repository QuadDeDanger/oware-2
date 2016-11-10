package functionality;

/**
 * This class represents a Player that has a Score House
 *
 * @author Haaris Memon
 */
public class Player {

    private String name;
    private ScoreHouse scoreHouse;
    private boolean isPlayersTurn;

    /**
     * Create Player with a name and initialised score house
     *
     * @param name of the Player
     */
    public Player(String name) {
        this.name = name;
        scoreHouse = new ScoreHouse();
    }

    /**
     * Sets the name of the player
     *
     * @param name of the Player
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
    public ScoreHouse getHouse() {
        return scoreHouse;
    }

    /**
     * Gets the score of how many seeds the player has
     *
     * @return the player's score
     */
    public int getScore() {
        return scoreHouse.getCount();
    }

    /**
     * Adds a seed to the Player's Score House
     *
     * @param seed to be inserted into player's score house
     */
    public void addSeedToHouse(Seed seed) {
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
     * @param isTurn of the Player
     */
    public void setIsPlayersTurn(boolean isTurn) {
        isPlayersTurn = isTurn;
    }

    public void clearScoreHouse(){
    	scoreHouse.reset();
    }
    
    @Override
    public String toString(){
    	return name;
    }

}

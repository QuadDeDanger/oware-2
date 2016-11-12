package functionality;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a ScoreHouse which stores a player's seeds (and thus
 * the score).
 * 
 * @author Aqib Rashid
 *
 */

public class ScoreHouse {

	private List<Seed> seedsInHouse;

	/**
	 * Creates a ScoreHouse with an empty list of seeds
	 */
	public ScoreHouse() {
		seedsInHouse = new ArrayList<>();
	}

	/**
	 * Get the number of seeds in the house which is the score
	 * 
	 * @return the number of seeds in the house
	 */
	public int getCount() {
		return seedsInHouse.size();
	}

	/**
	 * Add a seed to the house
	 *
	 * @param seed
	 *            seed to add to the scorehouse
	 */
	public void addSeed(Seed seed) {
		seedsInHouse.add(seed);
	}

	/**
	 * Resets the scorehouse to be empty
	 */
	public void reset() {
		seedsInHouse.clear();
	}

}

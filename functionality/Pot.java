package functionality;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a pot that contains a particular number of seeds
 *
 * @author Haaris Memon
 *
 */
public class Pot {

	// list of seed objects that the pot contains
	private List<Seed> seedsInPot;

	/**
	 * Create pot containing empty list of seeds
	 */
	public Pot() {
		seedsInPot = new ArrayList<Seed>();
		for (int i = 0; i < 4; i++) {
			seedsInPot.add(new Seed());
		}
	}

	/**
	 * Adds the Seed to the List of Seeds
	 *
	 * @param seed
	 *            to add to the pot
	 */
	public void addSeedInPot(Seed seed) {
		seedsInPot.add(seed);
	}

	/**
	 * Checks the size of the list of Seeds of the pot
	 *
	 * @return size of the list of Seeds
	 */
	public int howManySeeds() {
		return seedsInPot.size();
	}

	/**
	 * Stores and returns the list of Seeds, and empties the list that the Pot
	 * contains
	 *
	 * @return list of seeds that the Pot originally contained
	 */
	public List<Seed> emptyPot() {
		// stores the seeds in the pot
		List<Seed> temporaryListOfSeeds = new ArrayList(seedsInPot);
		// empties the pot
		seedsInPot.clear();
		return temporaryListOfSeeds;
	}

}

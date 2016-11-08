package functionality;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a House that contains a particular number of seeds on
 * the board.
 *
 * @author Haaris Memon
 *
 */
public class House {

	// list of seed objects that the pot contains
	private List<Seed> seedsInHouse;
	private int xPos;
	private int yPos;

	/**
	 * Create house containing empty list of seeds
	 */
	public House(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
		seedsInHouse = new ArrayList<Seed>();
		initialiseHouse();
	}

	private void initialiseHouse() {
		for (int i = 0; i < 4; i++) {
			seedsInHouse.add(new Seed());
		}
	}

	/**
	 * Adds the Seed to the List of Seeds
	 *
	 * @param seed
	 *            to add to the house
	 */
	public void addSeedInPot(Seed seed) {
		seedsInHouse.add(seed);
	}

	/**
	 * Checks the size of the list of Seeds of the pot
	 *
	 * @return size of the list of Seeds
	 */
	public int getCount() {
		return seedsInHouse.size();
	}

	/**
	 * Stores and returns the list of Seeds, and empties the list that the Pot
	 * contains
	 *
	 * @return list of seeds that the Pot originally contained
	 */
	public List<Seed> getSeedsAndEmptyHouse() {
		// stores the seeds in the pot
		List<Seed> temporaryListOfSeeds = new ArrayList<>(seedsInHouse);
		// empties the pot
		seedsInHouse.clear();
		return temporaryListOfSeeds;
	}

	public int getXPos() {
		return xPos;
	}

	public int getYPos() {
		return yPos;
	}

	@Override
	public String toString() {
		return xPos + ", " + yPos;
	}

	@Override
	public boolean equals(Object obj) {
		House otherHouse = (House) obj;
		return (otherHouse.getXPos() == xPos && otherHouse.getYPos() == yPos);
	}

}

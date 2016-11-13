package model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a House that contains a particular number of seeds on
 * the board.
 * Minor changes by Aqib Rashid and Jaydene Green-Stevens
 * @author Haaris Memon
 *
 */
public class House {

	// list of seed objects that the pot contains
	private List<Seed> seedsInHouse;
	private int xPos; // x position of house
	private int yPos; // y position of house

	/**
	 * Create house containing 4 seeds which is initialised by a helper method.
	 * Also sets the xPos and yPos of the house
	 */
	public House(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
		seedsInHouse = new ArrayList<Seed>();
		initialiseHouse();
	}

	/*
	 * Place 4 seeds in a house
	 */
	private void initialiseHouse() {
		for (int i = 0; i < 4; i++) {
			seedsInHouse.add(new Seed());
		}
	}

	/**
	 * Adds the Seed to the List of Seeds (house)
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
	 * Reset the house back to its initial state of 4 seeds
	 */
	public void resetHouse() {
		seedsInHouse.clear();
		initialiseHouse();
	}

	/**
	 * Stores and returns the list of Seeds, and empties the list that the house
	 * contains
	 *
	 * @return list of seeds that the house originally contained
	 */
	public List<Seed> getSeedsAndEmptyHouse() {
		// stores the seeds in the house in a temp arraylist
		List<Seed> temporaryListOfSeeds = new ArrayList<>(seedsInHouse);
		// empties the house
		seedsInHouse.clear();
		return temporaryListOfSeeds; // returns the temp arraylist
	}

	/**
	 * Method to return the list of Seeds stored in the house, without emptying
	 * the list
	 * 
	 * @return the list of seeds that the pot contains
	 */
	public List<Seed> getSeeds() {
		return seedsInHouse;
	}

	/**
	 * Return the x position of this house
	 * 
	 * @return x position of house
	 */
	public int getXPos() {
		return xPos;
	}

	/**
	 * Return the y position of this house
	 * 
	 * @return y position of house
	 */
	public int getYPos() {
		return yPos;
	}

	/*
	 * . Method used in debugging (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return xPos + ", " + yPos;
	}

	/**
	 * Check whether two houses are equal to each other
	 */
	@Override
	public boolean equals(Object obj) {
		House otherHouse = (House) obj;
		// x and y positions must match
		return (otherHouse.getXPos() == xPos && otherHouse.getYPos() == yPos);
	}

}

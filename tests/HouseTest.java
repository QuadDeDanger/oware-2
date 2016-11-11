package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import functionality.*;

/**
 * This class contains unit tests for the House class.
 * 
 * @author Aqib Rashid
 *
 */

public class HouseTest {

	@Test
	public void whenCreatingHouseSeedCountIs4() {
		House house = new House(0, 0);
		assertEquals("New house has 4 seeds", 4, house.getCount());
	}

	@Test
	public void whenCreatingHouseXAndYAreCorrect() {
		House house = new House(1, 2);
		assertEquals("New house has x position of 1", 1, house.getXPos());
		assertEquals("New house has y position of 2", 2, house.getYPos());

		House house2 = new House(0, 5);
		assertEquals("New house has x position of 0", 0, house2.getXPos());
		assertEquals("New house has y position of 5", 5, house2.getYPos());
	}

	@Test
	public void whenAddingSeedToPotCountIsCorrect() {
		House house = new House(0, 0);
		for (int i = 0; i < 16; i++) { // It already contains 4 seeds
			house.addSeedInPot(new Seed());
		}
		assertEquals("House has count 20", 20, house.getCount());
		house.addSeedInPot(new Seed());
		assertEquals("House has count 21", 21, house.getCount());
	}

	@Test
	public void resettingHouseSizeIsZero() {
		House house = new House(0, 0);
		for (int i = 0; i < 16; i++) {
			house.addSeedInPot(new Seed());
		}
		house.resetHouse(); // It is reset to 4 seeds
		assertEquals("House has count 4", 4, house.getCount());
	}

	@Test
	public void gettingSeedsAndEmptyingActuallyEmptiesAndReturnsAList() {
		House house = new House(0, 0);
		List<Seed> removed = new ArrayList<>(house.getSeedsAndEmptyHouse());
		assertEquals("House has size of 0 after emptying", 0, house.getCount());
		assertEquals("Removed list has size 4", 4, removed.size());
	}

}

package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import functionality.*;

/**
 * This class contains unit tests for the ScoreHouse class.
 * @author Aqib Rashid
 *
 */

public class ScoreHouseTest {

	@Test
	public void emptyScoreHouseShouldReturn0Size() {
		ScoreHouse scoreHouse = new ScoreHouse();
		assertEquals("Empty score house is size 0", 0, scoreHouse.getCount());
	}

	@Test
	public void addOneToScoreHouseIsSize1() {
		ScoreHouse scoreHouse = new ScoreHouse();
		scoreHouse.addSeed(new Seed());
		assertEquals("Score house is size 1 when adding", 1, scoreHouse.getCount());
	}

	@Test
	public void addTwentyToScoreHouseIsSize20() {
		ScoreHouse scoreHouse = new ScoreHouse();
		for (int i = 0; i < 20; i++) {
			scoreHouse.addSeed(new Seed());
		}
		assertEquals("Score house is size 20 when adding 20", 20, scoreHouse.getCount());
	}

	@Test
	public void resetScoreHouseIsSize0() {
		ScoreHouse scoreHouse = new ScoreHouse();
		for (int i = 0; i < 3; i++) {
			scoreHouse.addSeed(new Seed());
		}
		scoreHouse.reset();
		assertEquals("Score house is empty when resetting", 0, scoreHouse.getCount());
	}

}

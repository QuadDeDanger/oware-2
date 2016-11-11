package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import functionality.*;

/**
 * This class contains unit tests for the Seed class.
 * @author Aqib Rashid
 *
 */ 

public class SeedTest {

	@Test
	public void isNotCapturedShouldReturnFalseForNotCaptured() {
		Seed seed = new Seed();
		assertEquals("Seed is not captured", false, seed.isCaptured());
	}

	@Test
	public void isCapturedShouldReturnTrueForCaptured() {
		Seed seed = new Seed();
		seed.setIsCaptured(true);
		assertEquals("Seed is captured", true, seed.isCaptured());
	}

}

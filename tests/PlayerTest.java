package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.*;

/**
 * This class contains unit tests for the Player class.
 * 
 * @author Aqib Rashid
 *
 */

public class PlayerTest {

	@Test
	public void playerNameIsCorrectWhenSet() {
		Player player = new Player("Aqib");
		assertEquals("Player name is Aqib", "Aqib", player.getName());
		Player player2 = new Player("");
		assertEquals("Player2 name is blank", "", player2.getName());
	}

	@Test
	public void playerTurnIsSetWhenSet() {
		Player player = new Player("Aqib");
		player.setIsPlayersTurn(true);
		assertEquals("Is player's turn", true, player.getIsPlayersTurn());
	}

	@Test
	public void playerTurnIsNotSetWhenNotSet() {
		Player player = new Player("Aqib");
		assertEquals("Is not player's turn", false, player.getIsPlayersTurn());
	}

	@Test
	public void playerScoreIsZeroWhenSet() {
		Player player = new Player("Aqib");
		assertEquals("Player score is 0 ", 0, player.getScore());
	}

	@Test
	public void playerScoreHouseMatchesPlayerScore() {
		Player player = new Player("Aqib");
		for (int i = 0; i < 20; i++) {
			player.addSeedToScoreHouse(new Seed());
		}
		assertEquals("Player score is 20 ", player.getScoreHouse().getCount(), player.getScore());
		player.addSeedToScoreHouse(new Seed());
		assertEquals("Player score is 21 ", player.getScoreHouse().getCount(), player.getScore());
	}

	@Test
	public void afterClearingScoreHouseScoreIsZero() {
		Player player = new Player("Aqib");
		for (int i = 0; i < 20; i++) {
			player.addSeedToScoreHouse(new Seed());
		}
		player.clearScoreHouse();
		assertEquals("Player score is reset ", 0, player.getScore());
	}

}

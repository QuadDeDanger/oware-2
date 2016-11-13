package tests;

import model.BasicComputerPlayer;
import model.Board;
import model.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This class contains unit tests for the BasicComputerPlayer class.
 *
 * @author Haaris Memon
 */
public class BasicComputerPlayerTest {

    @Test
    public void checkNameIsCorrectWhenCreated() {
        BasicComputerPlayer computerPlayer = new BasicComputerPlayer();

        assertEquals("Basic Computer Player's Name is 'Computer'", "Computer", computerPlayer.getName());
    }

    @Test
    public void computerPlayerBoardIsSetWhenBoardIsMade() {
        BasicComputerPlayer computerPlayer = new BasicComputerPlayer();
        Player player2 = new Player("Haaris");
        Board board = new Board(computerPlayer, player2);

        assertEquals("Basic Computer Player's Board is Set", board, computerPlayer.getBoard());
    }

}

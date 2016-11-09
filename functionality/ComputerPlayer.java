package functionality;

import java.util.Random;

public class ComputerPlayer extends Player {

	public ComputerPlayer() {
		super("Computer");
		// TODO Auto-generated constructor stub
	}

	// Assuming the computer will always be row 0
	public int makeMove() {

		Random rnd = new Random();
		return rnd.nextInt(6);

	}

}

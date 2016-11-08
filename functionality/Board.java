package functionality;

import java.util.List;

/**
 * Created by Haaris on 08/11/2016.
 */
public class Board {

	private Pot[][] board;
	private Player player1;
	private Player player2;

	public Board(Player player1, Player player2) {
		board = new Pot[2][6];
		this.player1 = player1;
		this.player2 = player2;
		initialiseBoard();
	}

	private void initialiseBoard() {
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 6; j++) {
				board[i][j] = new Pot();
			}
		}
	}

	public void sow(int i, int j) {
		List<Seed> toSow = board[i][j].emptyPot();
		int currentX = i;
		int currentY = j;
		for (int index = 0; index < toSow.size(); ++index) {
			if (currentX == 0) {
				if (currentY == 0) {
					board[++currentX][currentY].addSeedInPot(toSow.get(index));
				} else {
					board[currentX][--currentY].addSeedInPot(toSow.get(index));
				}

			} else if (currentX == 1) {
				if (currentY == 5) {
					board[--currentX][currentY].addSeedInPot(toSow.get(index));
				} else {
					board[currentX][++currentY].addSeedInPot(toSow.get(index));
				}

			}
		}

	}

	public void print() {
		for (int i = 0; i < 2; ++i) {
			for (int j = 0; j < 6; ++j) {
				System.out.print(board[i][j].howManySeeds() + " ");
			}
			System.out.println("\n");
		}
	}

	// private Pot getNextPot(int i, int j) {
	// if (i == 0) {
	// if (j == 0) {
	// return board[1][0];
	// }
	// return board[0][j - 1];
	//
	// } else if (i == 1) {
	// if (j == 5) {
	// return board[0][5];
	// }
	// return board[1][j + 1];
	//
	// }
	//
	// return board[i][j];
	//
	// }

}

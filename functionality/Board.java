package functionality;

import java.util.List;

/**
 * This class represents a board.
 * Created by Haaris on 08/11/2016.
 * @author Haaris Memon
 * @author Aqib Rashid
 * @author Jaydene Green-Stevens
 */
public class Board {

	private House[][] board;
	private Player player1;
	private Player player2;

	public Board(Player player1, Player player2) {
		board = new House[2][6];
		this.player1 = player1;
		this.player2 = player2;
		initialiseBoard();
	}

	private void initialiseBoard() {
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 6; j++) {
				board[i][j] = new House();
			}
		}
	}

	public void sow(int i, int j) {
		List<Seed> toSow = board[i][j].getSeedsAndEmptyHouse();
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

	// For testing only
	public void print() {
		for (int i = 0; i < 2; ++i) {
			for (int j = 0; j < 6; ++j) {
				System.out.print(board[i][j].getCount() + " ");
			}
			System.out.println("\n");
		}
	}

	

}

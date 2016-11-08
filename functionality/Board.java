package functionality;

/**
 * Created by Haaris on 08/11/2016.
 */
public class Board {

    private Pot[][] board;
    private Player player1;
    private Player player2;


    public Board(Player player1, Player player2) {
        board  = new Pot[2][6];
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


}

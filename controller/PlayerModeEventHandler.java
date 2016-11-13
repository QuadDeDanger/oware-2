package controller;

import functionality.AIComputerPlayer;
import functionality.BasicComputerPlayer;
import functionality.Board;
import functionality.Player;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import view.BoardView;

/**
 * This class represents the Event Handler for the player modes at the welcome screen.
 *
 * @author Haaris Memon
 */
public class PlayerModeEventHandler implements EventHandler<ActionEvent> {

    private String playerString;

    /**
     * Contructs the Event Handler and stores the mode type
     * @param playerString
     */
    public PlayerModeEventHandler(String playerString) {
        this.playerString = playerString;
    }

    /**
     * Creates players and board, and sets up the board view depending on which game mode.
     * @param event of the button click
     */
    @Override
    public void handle(ActionEvent event) {
        Stage stage = new Stage();

        Player player1 = null;

        if(playerString.equals("player")) {
            player1 = new Player("Player1");
            stage.setTitle("Oware Two-Player");
        } else if(playerString.equals("basic")) {
            player1 = new BasicComputerPlayer();
            stage.setTitle("Oware Single Player");
        } else if(playerString.equals("advanced")) {
            player1 = new AIComputerPlayer();
            stage.setTitle("Oware Single Player (Advanced)");
        }

        Player player2 = new Player("Player");
        Board board = new Board(player1, player2);

        try {
            StackPane stack = new StackPane();
            stack.getChildren().add(new BoardView(board));

            Scene scene = new Scene(stack, 800, 450);
            stage.setMinWidth(800);
            stage.setMinHeight(500);
            stage.setScene(scene);
            stage.show();

            ((Node) event.getSource()).getScene().getWindow().hide();
        }catch (NullPointerException e){
            //Continue as user cancelled opening window so nothing needs to be done
        }
    }

}

# Project Crawl #

##Tasks to complete##
- Game Logic
  - Board class
    - All methods
      - capturing, letOpponentPlay, gameWon
      - is a move playable
  - Player class 
    - Single player mode: random number generator, extension of player
  - JUnit tests!
- UI
 - Give the board an interface
    - Randomise starting player
    - Deciding whose turn it is after a turn (disable half the board etc)
    - Updating player name and display
    - Updating score display
    - Upadting seeds in house display
    - Animations

- AI
  - To be discussed later

##Requirements##

| Requirement                | Met?           |
| -------------------------- |:--------------:| 
| In two player mode, the game selects a random player to make the first move.  | |
| The application must be able to display the initial board. | |
| The game must display the number of seeds in each house, either using images or numbers. It is not required to hide the number of seeds in a house from an opponent. |  |
| The application must be able to redistribute seeks according to the rules of the game. When selecting a house with 12 or more seeds, the starting house should be skipped as specified in the rules. | Yes |
| The application must implement the capture rule correctly, including the prohibition on capturing all of the opponent's seeds. Capture moves are not applicable to the player's own houses. | Yes, needs testing |
| The application must recognise when one player has won the game. | Yes, needs testing (also for draw) |
| The application must implement the rule that if the opponent has no seeds, and one or more moves is/are possible that gives the opponent seeds, then only such moves are permitted. | | |
|The application should include a graphical user interface that visualises the board, perhaps using sprites or other visual representations of the board.
|The application should include a basic single player mode. A basic single player mode is very easy to implement: just get the computer player to make random moves.| | |
|The application should include animations visualising the process of redistribution. Be careful if you decide to attempt this as developing animations can potentially be very time consuming if you are not experienced with this. Also note that this would not affect your mark much.| | |
|The application may include an AI player, using a basic search algorithm that explores all combinations of possible future moves up to a very limited number of moves, and identifies the best possible move for the AI player assuming the opponent also makes optimal moves for the opponent. It is extremely challenging to add this to the project in a single week and it does not affect your mark by much.| | |

##Project Brief##

https://keats.kcl.ac.uk/mod/page/view.php?id=1530390

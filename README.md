# Project Crawl #

##Tasks to complete##
- Game Logic
  - Board class
    - 2d array of pots
      - first row is player 1's
      - second row is player 2's
    - all methods
      - sowing, capturing etc
    - game logic
      - game won detection
  - Player class 
    - necessary attributes: house, name (optional, if none then use Player 1 or Player 2) etc
    - single player mode: random number generator
- UI
 - Give the board an interface
    - updating player name
    - updating captured seed number
    - updating seed number in pot
    - animations

- AI
  - to be discussed later


##Task distribution##
**Logic**
- Aqib
- Haaris
- Jay

**GUI**
- Ajeya
- Federico


##Project Brief##

The objective of this project is to implement the board game of Oware, using abapa rules as they are explained on the Wikipedia page of Oware (and not the Grand Slam variation, also explained on the same page).

Your team must implement the game using Java 8, with a JavaFX user interface. You must only use packages included with the Java 8 JDK and no external APIs may be used. It is essential that the application can be deployed easily. It must be possible to compile the entire application by running the following command from the root of the source code directory:

`$ javac MainApp.java`

The following application must start the application:

`$ java MainApp`

Note that in future projects, you will be using build tools instead of this somewhat convoluted approach. For this project, please adhere to the instructions above.

Requirements

Your application must meet the following requirements:

1. The submitted source code must meet the requirements specified in the section on Project Crawl deliverables
2. The submitted source code must compile and execute using the commands specified above.
3. The application must allow two players sharing a single computer to play the game:
3. In two player mode, the game selects a random player to make the first move.
4. The application must be able to display the initial board.
5. The game must display the number of seeds in each house, either using images or numbers. It is not required to hide the number of seeds in a house from an opponent.
6. The application must be able to redistribute seeks according to the rules of the game. When selecting a house with 12 or more seeds, the starting house should be skipped as specified in the rules.
7. The application must implement the capture rule correctly, including the prohibition on capturing all of the opponent's seeds. Capture moves are not applicable to the player's own houses.
8. The application must recognise when one player has won the game.
9. The application must implement the rule that if the opponent has no seeds, and one or more moves is/are possible that gives the opponent seeds, then only such moves are permitted.

It is absolutely essential that your application meets the "must-have" requirements. This is more important than any non-essential requirements. You may extend the application with the following "should-have" and "may-have" requirements:

1. The application should include a graphical user interface that visualises the board, perhaps using sprites or other visual representations of the board.
2. The application should include a basic single player mode. A basic single player mode is very easy to implement: just get the computer player to make random moves.
3. The application should include animations visualising the process of redistribution. Be careful if you decide to attempt this as developing animations can potentially be very time consuming if you are not experienced with this. Also note that this would not affect your mark much.
4. The application may include an AI player, using a basic search algorithm that explores all combinations of possible future moves up to a very limited number of moves, and identifies the best possible move for the AI player assuming the opponent also makes optimal moves for the opponent. It is extremely challenging to add this to the project in a single week and it does not affect your mark by much.


##Organisation, schedule, mark scheme, etc##

Please check the Module Handbook's Project Crawl chapter complete information about the organisation, schedule of work and mark scheme for this project.

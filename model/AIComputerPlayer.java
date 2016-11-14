package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class represents an simple AI based computer player in the advanced single player mode.
 *
 * @author Haaris Memon
 */
public class AIComputerPlayer extends BasicComputerPlayer {

    private HashMap<House, Integer> houseSeedMap;
    private int bestSeedsCaptured = 0;
    private House bestHouseToMakeMove;

    /**
     * Constructs a new AI Computer Player
     */
    public AIComputerPlayer() {
        setName("AI Computer");
        houseSeedMap = new HashMap<>();
    }

    /**
     * Computer searches for all the possible moves for best one.
     * If no move gives computer seeds, it makes a random move.
     */
    @Override
    public void makeMove() {
        //best house is reset every time new move is made
        bestHouseToMakeMove = null;
        //loops through all the houses on computer players row
        for(int i = 0; i < 6; ++i) {
            //the house that the computer would select, if the house is successful
            House startHouse = getBoard().getHouseOnBoard(0, i);
            //house that is reached at the end of sowing
            House lastHouse = checkSow(startHouse);
            //if the last house is on the opponents row then capture seeds
            if(lastHouse.getXPos() != 0) checkCapture(startHouse, lastHouse);
        }

        //if the best house was found then sow
        if(bestHouseToMakeMove != null) {
            getBoard().sow(bestHouseToMakeMove.getXPos(), bestHouseToMakeMove.getYPos());
            //sets the house that the computer has chosen
            setChosenMoveHouse(getBoard().getHouseOnBoard(bestHouseToMakeMove.getXPos(), bestHouseToMakeMove.getYPos()));
        } else { //make random move
            super.makeMove();
        }

    }

    //stores the new count of seeds in the hash map after sowing, without changing any of the count of seeds inside each house
    private House checkSow(House startHouse) {
        int numOfSeeds = startHouse.getCount();
        //the seeds in the starting house set to 0, as the house would be emptied
        houseSeedMap.put(startHouse, 0);

        House currentHouse = startHouse;
        for(int j = 0; j < numOfSeeds; ++j) {
            //moves to the next house
            currentHouse = getBoard().getNextHouse(currentHouse);

            //skip starting startHouse if revisited i.e. on 12th seed
            if (currentHouse.equals(startHouse)) {
                currentHouse = getBoard().getNextHouse(currentHouse);
            }

            //increments the number of seeds in each house that is visited while sowing
            houseSeedMap.put(currentHouse, currentHouse.getCount() + 1);
        }

        return currentHouse;
    }

    //checks the number of seeds captured from a specific move, and makes the best move.
    private void checkCapture(House startHouse, House lastHouse) {
        //stores the houses that would be captured
        List<House> toCapture = new ArrayList<>();
        // if the last house is on the opponents row and has 2 or 3 seeds
        if ((lastHouse.getXPos() == 1) && lastHouse.getCount() == 2 || lastHouse.getCount() == 3) {
            // add house to list of captured houses
            toCapture.add(lastHouse);
            // gets the previous house
            House previousHouse = getBoard().getPreviousHouse(lastHouse);
            for (int j = 0; j < 6; j++) {
                // if still on the opponents row and has size 2 or 3
                if (previousHouse.getXPos() == 1 && (previousHouse.getCount() == 2 || previousHouse.getCount() == 3)) {
                    // add house to list to capture
                    toCapture.add(previousHouse);
                    //move to the next previous house
                    previousHouse = getBoard().getPreviousHouse(previousHouse);
                } else { // quit the loop
                    break;
                }
            }
        }

        // Only go through if we have something to capture (list not empty)
        if (toCapture.size() > 0) {
            //keeps track of how many seeds captured
            int capturedSeedTotal = 0;
            //for each house that is captured, add the seed count to the counter
            for (House capturedHouse : toCapture) {
                capturedSeedTotal += houseSeedMap.get(capturedHouse);
            }

            //keeps track of how many seeds on the row
            int totalOnRow = 0;
            //for each house on the row add the seed count to the counter (to check if opponent still has seeds)
            for (int j = 0; j < 6; j++) {
                House houseAtBoard = getBoard().getHouseOnBoard(lastHouse.getXPos(), j);
                //gets the current number of seeds from the hashmap
                Integer houseSeedCount = houseSeedMap.get(houseAtBoard);
                //if the house was found in the hashmap, the count is added to the total
                if(houseSeedCount != null) {
                    totalOnRow += houseSeedCount;
                } else {
                    totalOnRow += getBoard().getHouseOnBoard(lastHouse.getXPos(), j).getCount();
                }
            }

            //counts the number of seeds that the computer can obtain by doing this move
            int seedsObtained = 0;
            // if the opponent now has no more seeds, then forfeit capture
            if (capturedSeedTotal != totalOnRow) {
                for (House house : toCapture) {
                    //for each house get and empty the seeds
                    List<Seed> toAddToScoreHouse = house.getSeeds();
                    // add the number seeds to the computer's seeds obtained count
                    seedsObtained = toAddToScoreHouse.size();
                }
            }

            //if the current seeds obtained is better than the previous best seeds obtained
            if(seedsObtained > bestSeedsCaptured) {
                //update the best seeds obtained
                bestSeedsCaptured = seedsObtained;
                //update the best house for computer to make move on
                bestHouseToMakeMove = startHouse;
            }
        }
    }
}

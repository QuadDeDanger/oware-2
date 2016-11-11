package functionality;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class represents an simple AI based computer player in the advanced single player mode.
 *
 * @author Haaris Memon
 */
public class AIComputerPlayer extends BasicComputerPlayer {

    HashMap<House, Integer> houseSeedMap;
    private int bestSeedsCaptured = 0;
    private House bestHouseToMakeMove = null;

    public AIComputerPlayer() {
        setName("AI Computer");
        houseSeedMap = new HashMap<>();
    }

    @Override
    public void makeMove() {
        for(int i = 0; i < 6; ++i) {
            House startHouse = getBoard().getHouseOnBoard(0, i);
            House lastHouse = checkSow(startHouse);
            checkCapture(startHouse, lastHouse);
        }

        if(bestHouseToMakeMove != null) {
            getBoard().sow(bestHouseToMakeMove.getXPos(), bestHouseToMakeMove.getYPos());
        } else {
            super.makeMove();
        }

    }

    //stores the new count of seeds in the hash map after sowing, without changing any of the count of seeds inside each house
    private House checkSow(House startHouse) {
        int numOfSeeds = startHouse.getCount();

        houseSeedMap.put(startHouse, 0);

        House currentHouse = startHouse;
        for(int j = 0; j < numOfSeeds; ++j) {
            currentHouse = getBoard().getNextHouse(currentHouse);

            //skip starting startHouse if revisited i.e. on 12th seed
            if (currentHouse.equals(startHouse)) {
                currentHouse = getBoard().getNextHouse(currentHouse);
            }

            houseSeedMap.put(currentHouse, currentHouse.getCount() + 1);
        }

        return currentHouse;
    }

    //checks the number of seeds captured from a specific move, and makes the best move. Else it makes a random move.
    private void checkCapture(House startHouse, House lastHouse) {
        List<House> toCapture = new ArrayList<>();
        if (lastHouse.getCount() == 2 || lastHouse.getCount() == 3) {
            toCapture.add(lastHouse); // add house to list of houses for now
            House previousHouse = getBoard().getPreviousHouse(lastHouse); // get previous house
            for (int j = 0; j < 6; j++) {
                // if on same row and has size 2 or 3
                if (previousHouse.getXPos() == lastHouse.getXPos() && (previousHouse.getCount() == 2 || previousHouse.getCount() == 3)) {
                    toCapture.add(previousHouse); // add to capture
                    previousHouse = getBoard().getPreviousHouse(previousHouse);
                } else { // quit the loop
                    break;
                }
            }
        }

        if (toCapture.size() > 0) { // Only go through if we have something to capture
            int capturedSeedTotal = 0;
            for (House capturedHouse : toCapture) {
                capturedSeedTotal += houseSeedMap.get(capturedHouse);
            }

            int totalOnRow = 0;
            for (int j = 0; j < 6; j++) {
                House houseAtBoard = getBoard().getHouseOnBoard(lastHouse.getXPos(), j);
                Integer houseSeedCount = houseSeedMap.get(houseAtBoard);
                if(houseSeedCount != null) {
                    totalOnRow += houseSeedCount;
                } else {
                    totalOnRow += getBoard().getHouseOnBoard(lastHouse.getXPos(), j).getCount();
                }
            }

            int seedCount = 0;
            if (capturedSeedTotal != totalOnRow) { // if the opponent now has no more seeds, then forfeit capture
                for (House house : toCapture) {
                    List<Seed> toAddToScoreHouse = house.getSeedsAndEmptyHouse();
                    for (Seed seed : toAddToScoreHouse) { // add each to the score house
                        ++seedCount;
                    }
                }
            }

            if(seedCount > bestSeedsCaptured) {
                bestSeedsCaptured = seedCount;
                bestHouseToMakeMove = startHouse;
            }
        }
    }
}

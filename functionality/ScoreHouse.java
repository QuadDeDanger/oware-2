package functionality;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a ScoreHouse.
 * 
 * @author Aqib Rashid
 *
 */

public class ScoreHouse {

	private List<Seed> seedsInHouse;

	/**
	 * Creates list of seeds
	 */
	public ScoreHouse() {
		seedsInHouse = new ArrayList<>();
	}

	/**
	 * Get the number of seeds in the house
	 * 
	 * @return the number of seeds in the house
	 */
	public int getCount() {
		return seedsInHouse.size();
	}

	/**
	 * Add a seed to the house
	 *
	 * @param seed to add to the house
	 */
	public void addSeed(Seed seed) {
		seedsInHouse.add(seed);
	}
	
	public void reset(){
		seedsInHouse.clear();
	}
	
}

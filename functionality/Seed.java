package functionality;

/**
 * This class represents a Seed
 * @author Aqib Rashid
 *
 */

public class Seed {

	private boolean isCaptured;
	
	/**
	 * Set whether a seed has been captured
	 * @param isCaptured true or false is seed has been captured
	 */
	public void setIsCaptured(boolean isCaptured){
		this.isCaptured = isCaptured;
	}
	
	/**
	 * Get whether the seed has been captured
	 * @return the captured status of the seed
	 */
	public boolean isCaptured(){
		return isCaptured();
	}
	
}

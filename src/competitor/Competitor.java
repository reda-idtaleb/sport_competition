package competitor;

/** A class of a competitor*/
public class Competitor {
	
	/** the competitor's name*/
	private String name;
	/** the number of points of the competitor*/
	private int nbPoints;
	
	/** Creates a competitor with a given name
	 * @param name the Competitor's name
	 */
	public Competitor (String name) {
		this.name = name;
		this.nbPoints = 0;
	}

	/** returns the name of the competitor
	 * @return the competitor's name
	 */
	public String getName() {
		return name;
	}
	
	/** sets the competitor's name 
	 * @param name the name of a competitor
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/** returns the number of points of the competitor
	 * @return the number of points
	 */
	public int getNbPoints() {
		return nbPoints;
	}
	/** sets the number of points of the competitor
	 * @param nbPoints
	 */
	public void setNbPoints(int nbPoints) {
		this.nbPoints = nbPoints;
	}

	/** 
	 * adds the points to the winner
	 */
	public void addPoints() {
		this.nbPoints++;
	}
	
	/** a string representation of the competitor
	 * @return the name of the competitor
	 */
	public String toString() {
		return name;
	}
	
}

package match;

import competitor.Competitor;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/** A class of a match in which we get the winner randomly*/
public class RandomMatch extends Match {	
	
	/**Creates a random match
	 */
	public RandomMatch() {
		super();
	}
	
	/** Creates a random match between two competitors
	 * 
	 * @param c1 first competitor
	 * @param c2 second competitor
	 */
	public RandomMatch(Competitor c1, Competitor c2) {
		super(c1, c2);
	}
	
	/** returns a random number between 0 and max
	 * @param max the biggest number
	 * @return a random number between 0 and max
	 */
	public int getRandomInt(int max) {
		Random rand = new Random();
		return rand.nextInt(2);
	}
	
	/**
	 * @param c1 the first competitor 
	 * @param c2 the second competitor 
	 * @return the winner of the match randomly
	 */
	protected Competitor calculateWinnerOfMatch(Competitor c1, Competitor c2) {
		List<Competitor> competitors = new ArrayList<Competitor>();
		competitors.add(c1);
		competitors.add(c2);
		int index = getRandomInt(1);
		return competitors.get(index);			
	}
	
	

}

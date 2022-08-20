package selection;

import java.util.List;
import java.util.Map;

import competitor.Competitor;

/** A selection is a way of selecting the competitors who will play the second  phase of the Master competition*/
public interface Selection {
	
	/**
	 * calculates the number of the groups
	 * @param competitors a List of the competitors
	 * @return the number of the groups
	 */
	public int getNbGroups(List<Competitor> competitors);
	
	/**
	 * returns a list of the competitors to play the second phase
	 * @param ranking a map containing the competitors and their ranking
	 * @param competitors a list of All the competitors
	 * @param nbOfPools 
	 * @return a list of competitors to play the second phase
	 */
	public List<Competitor> getPlayersSecondRound(Map<Competitor, Integer> ranking, List<Competitor> competitors, int nbOfPools);
	
	
	public List<Integer> possibleNbOfGroups(List<Competitor> competitors);

}

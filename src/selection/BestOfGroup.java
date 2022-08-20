package selection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import competitor.Competitor;
import util.Util;

/** BestOfGroup is a type of selection that consists on selecting the best player of each group (of the first phase) to play the second phase */
public class BestOfGroup implements Selection {
	
	/**
	 * calculates the number of the groups
	 * the possible number of groups is a divider of the number of the competitors (starting by 2) and also a power of two
	 * @param competitors the list of the competitors
	 * @return the number of the groups
	 */
	@Override
	public int getNbGroups(List<Competitor> competitors) {
		List<Integer> possibleNbOfGroups = possibleNbOfGroups(competitors);
        return Util.getRandomElementFromList(possibleNbOfGroups);
	}

	/**
	 * @param competitors
	 * @return
	 */
	public List<Integer> possibleNbOfGroups(List<Competitor> competitors) {
		List<Integer> possibleNbOfGroups = new ArrayList<Integer>();
		List<Integer> dividers = Util.dividers(competitors.size());
        for( int i=0;i< dividers.size() ;i++){
            if (Util.isPowerOfTwo(dividers.get(i))) {
            	possibleNbOfGroups.add(dividers.get(i));
            }
        }
		return possibleNbOfGroups;
	}
	
	/**
	 * returns a list of the competitors to play the second phase
	 * the competitors to play the second phase are the best players of their initial group
	 * @param ranking a map containing the competitors and their ranking
	 * @param competitors a list of All the competitors
	 * @return a list of competitors to play the second phase
	 */
	@Override
	public List<Competitor> getPlayersSecondRound(Map<Competitor, Integer> ranking, List<Competitor> competitors, int nbOfPools) {
		List<Competitor> result = new ArrayList<Competitor>();
		for(Competitor c : ranking.keySet()) {
			if (ranking.get(c)==1) {
				result.add(c);
			}
		}
		return result; 
	}

}

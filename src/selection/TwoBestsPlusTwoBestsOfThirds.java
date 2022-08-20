package selection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import competitor.Competitor;
import util.Util;

/** TwoBestsPlusTwoBestsOfThirds is a way of selecting where we select the first and second competitor on the ranking of each group 
 * plus a certain number of the third competitors on the ranking lists to complete the right number of competitors 
 * to play the tournament on the second phase of the master competition  */
public class TwoBestsPlusTwoBestsOfThirds implements Selection {
	
	/**
	 * calculates a list of the possible numbers of the groups
	 * the possible number of groups is a divider of the number of the competitors (starting by 2)
	 * @param competitors a list of groups
	 * @return a list containing the possible numbers of the groups 
	 */
	@Override
	public int getNbGroups(List<Competitor> competitors) {
		 List<Integer> possibleNbOfGroups = possibleNbOfGroups(competitors);
		 return Util.getRandomElementFromList(possibleNbOfGroups);
	}

	/**
	 * calculate a list which contains possible pool numbers such that the number of competitors in each pool is strictly greater than 2.
	 * @param competitors the list of the competitiors
	 * @return a list of possible number of pools such that the number of competitors in each pool is strictly greater than 2.
	 */
	public List<Integer> possibleNbOfGroups(List<Competitor> competitors) {
		List<Integer> dividers = Util.dividers(competitors.size());
		List<Integer> possibleNbOfGroups = new ArrayList<Integer>();
		for ( int d : dividers) {
			if (!((int) competitors.size() / d == 2)) {
				possibleNbOfGroups.add(d);
			}
		}
		return possibleNbOfGroups;
	}
	
	/**
	 * returns a list of the competitors to play the second phase
	 * the competitors to play the second phase are the first and second competitor on the ranking of each group 
	 * plus a certain number of the third competitors on the ranking lists
	 * @param ranking a map containing the competitors and their ranking
	 * @param competitors a list of All the competitors
	 * @return a list of competitors to play the second phase
	 */
	@Override
	public List<Competitor> getPlayersSecondRound(Map<Competitor, Integer> ranking, List<Competitor> competitors, int nbOfPools) {
		List<Competitor> result = new ArrayList<Competitor>();
		List<List<Object>>thirds = new ArrayList<List<Object>>();
		for(Competitor c : ranking.keySet()) {
			if (ranking.get(c)==1 || ranking.get(c)==2 ) {
				result.add(c);
			}
			if (ranking.get(c)==3) {
				List<Object> list = new ArrayList<Object>();
				list.add(c);
				list.add(c.getNbPoints());
				thirds.add(list);
			}
		}
		
		if (!thirds.isEmpty()) {
			List<Competitor> l = getBestThirds(thirds, competitors, nbOfPools);
			for(Competitor c : l) {
				result.add(c);
			}
		}
		return result; 
	}
	
	/**
	 * returns two bests of all the thirds
	 * @param thirds a map containing the third of each league
	 * @param competitors a list of All the competitors
	 * @return a list of the two bests of all the thirds
	 */
	public List<Competitor> getBestThirds(List<List<Object>> thirds,List<Competitor> competitors, int nbOfPools){
		List<Competitor> result = new ArrayList<Competitor>();
		List<Integer> nbPoints = new ArrayList<Integer>();
		
		if(thirds.size() == nbOfThirdsToAdd(competitors, nbOfPools)) {
			for(List<Object> l : thirds) {
				result.add((Competitor)l.get(0));
			}
		}
		
		else{
			for(List<Object> l : thirds) {
				nbPoints.add((int)l.get(1));
			}
			
			BestTwo(thirds, result, nbPoints, competitors, nbOfPools);
		}
		return result;
		
	}
	
	/**
	 * fills the result with the best of thirds 
	 * @param thirds a map containing the third of each league
	 * @param result a list of the best of the thirds
	 * @param competitors a list of All the competitors
	 * @param nbPoints the list of all the points of all the thirds
	 */
	public void BestTwo(List<List<Object>> thirds, List<Competitor> result, List<Integer> nbPoints, List<Competitor> competitors, int nbOfPools) {
		while(result.size() < nbOfThirdsToAdd(competitors, nbOfPools)) {
			int max = Collections.max(nbPoints) ; 
			for(int i = 0; i < thirds.size(); i++) {
				if((int)thirds.get(i).get(1) == max){
					result.add((Competitor) thirds.get(i).get(0));
					nbPoints.remove(i);
					thirds.remove(i);
				}
			}		
		}
	}	
	
	/** returns the number of the thirds to add to the competitors to play the tournament
	 * @param competitors the list of the competitor
	 * @return the number of the thirds to add to the competitors to play the tournament
	 */
	public int nbOfThirdsToAdd(List<Competitor> competitors, int nbOfPools) {
		int k = 0;
		int n = 2 *nbOfPools;
		while(! Util.isPowerOfTwo(n)) {
			n++;
			k++;
		}
		return k;	
	}

	
	
	

}

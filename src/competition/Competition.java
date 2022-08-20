package competition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import competitor.Competitor;
import match.Match;
import match.RandomMatch;
import observer.*;
import util.MapUtil;

/** A class of a competition*/
public abstract class Competition {
	/** A list of the observers of the competition*/
	protected ArrayList<CompetitionListener> competitionObservers;
	/** a match played in the competition*/
	protected final Match match;
	/** The list of the competitors*/
	protected List<Competitor> competitors;
	/** The set of matches that will be played*/
	protected Set<Match> setOfMatches;
	/** The played matches */
	protected List<Match> thePlayedMatchs;
	

	/** Creates a competition between the competitors where we get the winner randomly after the matches are played
	 * @param competitors the list of the competitors
	 * */
	public Competition(List<Competitor> competitors) {
		this(competitors, new RandomMatch());
	}
	
	/** Creates a competition between the competitors with a specific type of match
	 * @param competitors the list of the competitors of this competition
	 * @param match the match to be played
	 */
	public Competition(List<Competitor> competitors, Match match){
		this.competitionObservers = new ArrayList<CompetitionListener>();
		this.competitors = competitors;
		this.match = match;
		this.setOfMatches = new HashSet<Match>();
		this.thePlayedMatchs = new ArrayList<Match>(); 		
	}
	
	/** adds a competition observer to the list of the competition observers
	 * @param l the competition observer
	 */
	public synchronized void addCompetitionListener(CompetitionListener l) {
		if (competitionObservers.contains(l)) { return ; }
		competitionObservers.add(l);
	}
	/** removes the competition Listener
	 * @param l the listener to remove
	 */
	public synchronized void removeCompetitionListener(CompetitionListener l){
		competitionObservers.remove(l);
	}
	
	/** launches the event when the competition is played */
	private void fireCompetitionPlayed() {
		@SuppressWarnings("unchecked")
		ArrayList<CompetitionListener> clone = (ArrayList<CompetitionListener>) competitionObservers.clone();
		ArrayList<CompetitionListener> co = clone;
		if (co.size() == 0) { return; }
		CompetitionEvent event = new CompetitionEvent(this);
		for (CompetitionListener observer : co) {
			observer.competitionPlayed(event);
		}
	}
	
	/** launches the event when the competition is played */
	public void competitionPlayed(){
		fireCompetitionPlayed();
	}
	
	
	/**
	 * returns the list of the competitors
	 * @return the list of the competitors
	 */
	public List<Competitor> getCompetitors() {
		return competitors;
	}

	/**
	 * sets the competitors of the competition
	 * @param competitors the competitors of the competition
	 */
	public void setCompetitors(List<Competitor> competitors) {
		this.competitors = competitors;
	}
	
	/**returns the matches to be played 
	 * @return the matches to be played*/
	public Set<Match> getSetOfMatches() {
		return setOfMatches;
	}
	
	/**
	 * get the match of this competition
	 * @return the current match of this competition
	 */
	public Match getMatch() {
		return this.match;
	}
	
	/** returns a list of the matches that are already played
	 * @return a list of the played matches
	 */
	public List<Match> getThePlayedMatchs() {
		return thePlayedMatchs;
	}
	
	/** adds a competitor to the list of the competitors
	 * @param c the competitor to add
	 */
	public void addCompetitor(Competitor c) {
		competitors.add(c);
	}
	
	/** Plays All the matches of the competition*/
	public void play() {
		this.organizationOfMatches(competitors);
		this.play(this.competitors);
	}
	
	/** plays a match of the competition
	 * @param c1 the first competitor
	 * @param c2 the second competitor*/   
	public void playMatch(Competitor c1, Competitor c2) {
		this.match.playAMatch(c1, c2);
		this.match.getWinner().addPoints();
	}
	
	/** plays the competition between a list of competitors
	 * @param competitors the list of the competitors
	 * */
	protected abstract void play(List<Competitor> competitors);
	
	/**
	 * Organize the set of the matches between two each competitors
	 * The set is organized depending on the type of a competition
	 * @param competitors the list of the competitors
	 */
	protected abstract void organizationOfMatches(List<Competitor> competitors);
	
	/**
	 * a description of the competition
	 * @return  a description of the competition
	 */
	protected abstract String descriptionCompetition();
	
	/** returns a map containing all the competitors with their points
	 * @return a map containing all the competitors with their points
	 */
	public Map<Competitor, Integer> resultList(){
		Map<Competitor, Integer> rankList = new HashMap<Competitor, Integer>();
		
		for(Competitor c : this.competitors) {
			rankList.put(c, c.getNbPoints());
		}
	
		return rankList;
	}
		
	/** orders the map of competitors with their points and displays it 
	 * @return an ordered map containing the competitors with their points
	 */
	public Map<Competitor, Integer> ranking(){
		System.out.println("\n*****************************************************************");
		
		System.out.println("\t\t     *** " + this.descriptionCompetition() + " RANKING ***");
		System.out.println("*****************************************************************\n");
		
		Map<Competitor, Integer> rankList = resultList();
		Map<Competitor, Integer> listResult = MapUtil.sortByDescendingValue(rankList);
		
		for(Competitor c : listResult.keySet()){
			System.out.println(c + "\t - " + listResult.get(c));
		}
		return listResult;	
	}
	
	/**clones the match into a new match
	 * @param c1 the first competitor
	 * @param c2 the second competitor
	 * @return
	 */
	public Match cloneMatch(Competitor c1, Competitor c2) {
		Match m = this.match;
		m.setC1(c1);
		m.setC2(c2);
		
		Match thisMatch = null;
		try {
			thisMatch = (Match) m.clone();
		} catch (CloneNotSupportedException e) {
		}
		return thisMatch;
	}
	
}

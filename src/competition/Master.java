package competition;

import java.util.ArrayList;
import match.Match;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import Exception.NumberNotEvenException;
import competitor.Competitor;
import selection.Selection;

public class Master extends Competition {
	
	/** the leagues to be played in the first phase*/
	protected List<League> leagues;
	/** the method of selecting the players for the second phase*/
	protected Selection selection;
	/** The tournament  to be played in the second phase*/
	protected Tournament tournament; 
	/** The number of pools */
	protected int nbOfPools;
	
	/**Creates a master with a list of competitors and a specific type of selection for the second phase 
	 * @param competitors the competitors of the competition
	 * @param selection the type of selection for the second phase
	 * @param nbPool the number of pools 
	 */
	public Master(List<Competitor> competitors, Selection selection, int nbPool) {
		super(competitors);
		this.selection = selection;	
		this.nbOfPools = nbPool;
		this.leagues = new ArrayList<League>();
		this.tournament = new Tournament(null);
	}


	/**Creates a master with a list of competitors and a specific type of selection for the second phase 
	 * @param competitors the competitors of the competition
	 * @param selection the type of selection for the second phase
	 */
	public Master(List<Competitor> competitors, Selection selection) {
		super(competitors);
		this.selection = selection;	
		this.nbOfPools = selection.getNbGroups(competitors);
		this.leagues = new ArrayList<League>();
		this.tournament = new Tournament(null);
	}
	
	/** returns the Tournament
	 * @return the tournament
	 */
	public Tournament getTournament() {
		return tournament;
	}
	
	/** sets the tournament for the master competition
	 * @param tournament
	 */
	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}
	
	/** returns the method of selecting the players for the second phase 
	 * @return the selection
	 */
	public Selection getSelection() {
		return selection;
	}
	
	/** sets the selection for the master
	 * 
	 * @param selection the selection to set
	 */
	public void setSelection(Selection selection) {
		this.selection = selection;
	}
	
	/** returns the number of the pools
	 * 
	 * @return the number of the pools
	 */
	public int getNbOfPools() {
		return nbOfPools;
	}

	/** returns the league competitions of the first phase
	 * @return the leagues
	 */
	public List<League> getLeagues() {
		return leagues;
	}
	
	/** fills the played Matches with the played Matches of the leagues 
	 * and the tournament of the master
	 */
	public void fillsThePlayedMatches() {
		for(League l : leagues) {
			List<Match> leagueMatchs = l.getThePlayedMatchs();
			for (Match m : leagueMatchs) {
				this.thePlayedMatchs.add(m);
			}
		}
		List<Match> tournamentMatchs = this.tournament.getThePlayedMatchs();
		for (Match m : tournamentMatchs) {
			this.thePlayedMatchs.add(m);
		}
	}


	
	/** Verifies if the number of the competitors is even
	 * @throws NumberNotEvenException when the number of competitors is not even
	 * */
	public void masterVerification()throws NumberNotEvenException {
		if (super.competitors.size() % 2 != 0) {
			throw new NumberNotEvenException();
		}	
	}
	
	/**adds a League competition to the list of the leagues
	 * @param League
	 */
	public void addCompetition(League League) {
		this.leagues.add(League);
	}
	
	
	/** returns the groups to play the leagues
	 * @param competitors the list of the competitors
	 * @return the groups to play the leagues
	 */
	public List<List<Competitor>> getGroups(List<Competitor> competitors) {
		Collections.shuffle(competitors);
		List<Competitor> competitorsOfEachGroup = new ArrayList<Competitor>();
		List<List<Competitor>> listOfGroups = new ArrayList<List<Competitor>>();
		int nbCompetitorsByGroup = competitors.size() / this.nbOfPools;
		int indexCompetitor = 0;
		for(int i = 0 ; i < this.nbOfPools ; i++) {
			for (int j = 0; j < nbCompetitorsByGroup; j++ ) {	
				competitorsOfEachGroup.add(competitors.get(indexCompetitor));
				indexCompetitor ++;
			}
			listOfGroups.add(competitorsOfEachGroup);
			competitorsOfEachGroup = new ArrayList<Competitor>();
		}
		return listOfGroups;
		
	}

	/** plays the master between a list of competitors
	 * @param competitors the list of the competitors
	 * */
	@Override
	protected void play(List<Competitor> competitors) {
		Map < Competitor , Integer> map = new HashMap< Competitor, Integer>();
		int i = 1;
		this.phaseOneDescription(1);
		for(League c : leagues) {
			this.poulDescription(i, c);
			c.play();
			fillsTheRankingMap(map, c);
			i++;
		}
	    this.phaseOneDescription(2);
		this.tournament.setCompetitors(selection.getPlayersSecondRound(map, competitors, this.nbOfPools));
		this.tournamentDescription();
		tournament.play();	
		tournament.ranking();
		this.masterFinishedDescription();
	}
	
	/** fills the ranking map with all the competitors and their ranking
	 * @param map an empty map which will contain the rankings of each of the competitors.
	 * @param league a League to play
	 */
	public void fillsTheRankingMap(Map<Competitor, Integer> map, League league) {
		Map<Competitor, Integer> leagueRanking = league.ranking();
		int i = 0;
		for(Competitor k : leagueRanking.keySet()) {
			i++;
			map.put(k, i);
		}
	}

	/**
	 * Organizes the league competitions to be played in the first phase
	 * @param competitors the list of the competitors
	 */
	@Override
	protected void organizationOfMatches(List<Competitor> competitors) {
		try {
			masterVerification();
			List<List<Competitor>> groups = getGroups(competitors);
			for(int i = 0 ; i < nbOfPools; i++) {
				this.leagues.add(new League(groups.get(i)));
			}
		}
		catch (NumberNotEvenException e) {
			System.out.println(" The number of competitors must be even");
		}		
	}
	

	/**
	 * a simple description to show if the master is finished
	 */
	private void masterFinishedDescription() {
		System.out.println("\nœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœ");
		System.out.println("\t\t\tMaster Finished");
		System.out.println("œœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœ");
	}

	/**
	 * a description of the tournament
	 */
	private void tournamentDescription() {
		System.out.println("\n-----------------------------------------------------------------");
		System.out.println(" -> " + tournament);
		System.out.println("-----------------------------------------------------------------");
	}

	/**
	 * a description of a league group
	 * @param i an integer that represent a number of a Group
	 * @param c the current league
	 */
	private void poulDescription(int i, League c) {
		System.out.println("\n-----------------------------------------------------------------");
		System.out.println(" -> Group " + i + " : " + c);
		System.out.println("-----------------------------------------------------------------");
	}
	
	/**
	 * a description of the current phase
	 * @param i the number of the phase
	 */
	private void phaseOneDescription(int i) {
		System.out.println("\nœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœ");
		System.out.println("\t\t\tPhase " + i + " Begin");
		System.out.println("œœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœœ");
	}
	
	@Override
	protected String descriptionCompetition() {
		return "Master";
	}

}

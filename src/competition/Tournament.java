package competition;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import util.Util;
import Exception.InsufficientNumberOfParticipantsException;
import competitor.Competitor;
import match.Match;

public class Tournament extends Competition {

	/** the winners list*/
	private List<Competitor> winnerList;
	/** A boolean that indicates if the tournament is finished*/
	private boolean isFinished;
	/** The winner of the tournament*/
	private Competitor winnerOfTournament;


	/** Creates a Tournament competition between the competitors
	 * @param competitors the list of the competitors
	 */
	public Tournament(List<Competitor> competitors) {
		super(competitors);

		this.isFinished = false;
		this.winnerList = new ArrayList<Competitor>();	
		this.winnerOfTournament = null;
	}
	
	/** Creates a tournament between the competitors with a specific type of match
	 * @param competitors the list of the competitors of this competition
	 * @param match the match to be played
	 */
	public Tournament(List<Competitor> competitors, Match match) {
		super(competitors, match);
		
		this.isFinished = false;
		this.winnerList = new ArrayList<Competitor>();	
		this.winnerOfTournament = null;
		
	}


	/**returns the winner of the tournament
	 * @return the winner of tournament
	 */
	public Competitor getWinnerOfTournament() {
		return winnerOfTournament;
	}
	
	/**	returns if the Tournament is finished
	 * @returns if the Tournament is finished
	 */
	public boolean isFinished() {
		return isFinished;
	}
	
	/**
	 * Sets the Tournament as finished
	 */
	public void setAsFinished() {
		this.isFinished = true;
	}
	
	/** Tests if the number of competitors is sufficient to play a tournament
	 * @throwsInsufficientNumberOfParticipantsException throws an Exception if the number of participants is not efficient
	 */
	public void tournamentVerification() throws InsufficientNumberOfParticipantsException{
		int numberOfCompetitors = super.competitors.size();
		if(numberOfCompetitors != 0) {
			if(! Util.isPowerOfTwo(numberOfCompetitors)) {
				throw new InsufficientNumberOfParticipantsException();
			}
		}	
	}
	
	/**
	 * Choose two competitor randomly in the list of competitors
	 * The two competitors are distinct 
	 * @param competitors the list of the competitors
	 * @return return a list of two competitors chosen randomly
	 */
	public Match matchChooseTwoPlayerRandomly(List<Competitor> competitors) {
		Random rand = new Random();
		
		Competitor player1 = competitors.get(rand.nextInt(competitors.size()));
		competitors.remove(player1);
		
		Competitor player2 = competitors.get(rand.nextInt(competitors.size()));
		competitors.remove(player2);
		
		Match m = this.match;
		m.setC1(player1);
		m.setC2(player2);
		
		Match thisMatch = null;
		try {
			thisMatch = (Match) m.clone();
		} catch (CloneNotSupportedException e) {
		}
		
		return thisMatch;
	}
	

	/**Organizes the matches of tournament between the competitors
	 * @param competitors the list of the competitors
	 */
	@Override
	protected void organizationOfMatches(List<Competitor> competitors) {
		try {
			tournamentVerification();
			List<Competitor> cloneListCompetitors = new ArrayList<Competitor>();
			this.setOfMatches.clear();
			cloneListCompetitors.addAll(competitors);
			int nbOfMatchs = (competitors.size())/2;
			for(int i = 0; i < nbOfMatchs; i++) {
				Match matchBetweenTwoPlayerRandomly = matchChooseTwoPlayerRandomly(cloneListCompetitors);
				this.setOfMatches.add(matchBetweenTwoPlayerRandomly);		
			}
		} catch (InsufficientNumberOfParticipantsException e) {
			System.out.println("* Warning! insufficient number of competitors! *");
			System.out.println("  -> The number of competitors must be a power of two: 2, 4, 8, ... 2^n (n >= 1)");
			System.exit(0);
		}
	}

	/** plays the competition between a list of competitors
	 * @param competitors the list of the competitors
	 * */
	@Override
	protected void play(List<Competitor> competitors) {
		int tour = 0;
		Iterator<Match> currentRoundIterator = this.setOfMatches.iterator();
		while(!isFinished) {
			System.out.println("* Round " + (tour+1) + " : ");
			System.out.println("\"\"\"\"\"\"\"\"\"\"\"");
			while (currentRoundIterator.hasNext()){
				Match m = (Match) currentRoundIterator.next();
				this.match.setC1(m.getC1());
				this.match.setC2(m.getC2());
				playMatch(this.match.getC1(), this.match.getC2());
				if(this.match.isPlayed()) {
					Match matchC = cloneMatch(this.match.getC1(),this.match.getC2());
					thePlayedMatchs.add(matchC);
				}
				winnerList.add(this.match.getWinner());	
			}
			if (this.setOfMatches.size() == 1) {
				this.winnerOfTournament = winnerList.get(0);
				this.isFinished = true;			
			}
			organizationOfMatches(winnerList);
			winnerList.clear();
			currentRoundIterator = this.setOfMatches.iterator();
			System.out.println("");
			tour++; 
		}
		System.out.println("*****************************************************************");
		System.out.println("\t\t     THE TOURNAMENT IS FINISHED !\n");
		System.out.println("*****************************************************************");
		System.out.println("\n * THE WINNER Of The Tournament Is -> |" + this.winnerOfTournament + "|");

	}

	@Override
	protected String descriptionCompetition() {
		return "Tournament";
	}

	@Override
	public String toString() {
		return "Tournament, competitors = " + competitors;
	}
	
	
	

}

package observer;
import java.util.HashMap;
import java.util.Map;

import competition.Competition;
import competitor.*;
import match.Match;

/** A book maker gets the results of the competition and decreases the odds for the winner of each match and increases them for the loser*/
public class BookMaker implements CompetitionListener {
	
	/** A list of each competitor and their odds */
	private Map<Competitor,Integer> theCompetitorsOdds ;
	
	/** Creates the BookMaker 
	 * initializes the competitors adds
	 */
	public BookMaker() {
		this.theCompetitorsOdds = new HashMap<Competitor,Integer>();
	}
	
	/** displays the competition odds before and after the competition
	 * @param c the competition event
	 */
	@Override
	public void competitionPlayed(CompetitionEvent c) {
		Competition competition = (Competition) c.getSource();
		for (Competitor competitor : competition.getCompetitors()) {
			theCompetitorsOdds.put(competitor,1);
		}
		
		System.out.println("\n*****************************************************************");
		System.out.println("      *** THE COMPETITORS' ODDS BEFORE THE COMPETITION ***");
		System.out.println("*****************************************************************\n");
		
		fillsTheOddsMap(competition);
		
		System.out.println("\n*****************************************************************");		
		System.out.println("       *** THE COMPETITORS' ODDS AFTER THE COMPETITION ***");
		System.out.println("*****************************************************************\n");
		
		for(Competitor competitor : this.theCompetitorsOdds.keySet()){
			System.out.println(competitor + "\t - " + theCompetitorsOdds.get(competitor));
		}
	}
	

	/** calculates the odds of the competitors after each match of the competition
	 * @param competition
	 */
	public void fillsTheOddsMap(Competition competition) {
			
		for (Match m : competition.getThePlayedMatchs()) {

			if (m.getWinner() == m.getC1() ) {
				if ( theCompetitorsOdds.get(m.getC1()) >= 2 )
				theCompetitorsOdds.put(m.getC1(),theCompetitorsOdds.get(m.getC1()) - 1);
				theCompetitorsOdds.put(m.getC2(),theCompetitorsOdds.get(m.getC2()) + 1 );
			}
			else {
				theCompetitorsOdds.put(m.getC1(),theCompetitorsOdds.get(m.getC1()) + 1);
				if ( theCompetitorsOdds.get(m.getC2()) >= 2 )
				theCompetitorsOdds.put(m.getC2(),theCompetitorsOdds.get(m.getC2()) - 1);
			} 
			
			m.displayResult(m.getC1(), m.getC2());
			System.out.println("\n The odd of  "+m.getC1().getName()+" is now "+ theCompetitorsOdds.get(m.getC1())+" - The odd of "+m.getC2().getName()  + " is now "+ theCompetitorsOdds.get(m.getC2())+"\n");
		}
	}

}


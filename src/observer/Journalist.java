package observer;

import competition.Competition;
import match.Match;

/** A journalist gets the results of the matches and displays them*/
public class Journalist implements CompetitionListener {
	
	/** displays the result of all the matches
	 * @param c the competition event*/
	@Override
	public void competitionPlayed(CompetitionEvent c) {
		Competition competition = (Competition) c.getSource();
		System.out.println("\n*****************************************************************");
		System.out.println("  *** THE RESULTS OF THE MATCHES PUBLISHED BY THE JOURNALIST ***");
		System.out.println("*****************************************************************\n");
		for (Match m : competition.getThePlayedMatchs()) {
			m.displayResult(m.getC1(), m.getC2());
		}
	}

}

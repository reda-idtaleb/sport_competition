package competition;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import competitor.Competitor;
import match.Match;

/**
 * A league competition is a competition where each competitor plays twice against every other competitor
 * And the winner is the one who collects the biggest number of victories
 */
public class League extends Competition{
	
	/** Creates a League competition between the competitors
	 * @param competitors the list of the competitors
	 */
	public League(List<Competitor> competitors) {
		super(competitors);
	}	
	
	/** Creates a league between the competitors with a specific type of match
	 * @param competitors the list of the competitors of this competition
	 * @param match the match to be played
	 */
	public League(List<Competitor> competitors, Match match) {
		super(competitors, match);
	}

	/** plays the competition between a list of competitors
	 * @param competitors the list of the competitors
	 * */
	@Override
	protected void play(List<Competitor> competitors) {
		for (Match m : this.setOfMatches) {
			this.match.setC1(m.getC1());
			this.match.setC2(m.getC2());
			playMatch(this.match.getC1(),this.match.getC2());
			if(this.match.isPlayed()) {
				Match matchC = cloneMatch(this.match.getC1(),this.match.getC2());
				thePlayedMatchs.add(matchC);
			}		
		}
	}
	
	/** organizes the matches of the league
	 * @param competitors the list of the competitors */
	@Override
	protected void organizationOfMatches(List<Competitor> competitors) {
		List<Competitor> cloneListCompetitors = new ArrayList<Competitor>();
		cloneListCompetitors.addAll(competitors);
		Collections.shuffle(cloneListCompetitors);
		Match m = this.match;
		Match thisMatch = null;
		for(Competitor c1 : cloneListCompetitors) {
			for(Competitor c2 : cloneListCompetitors) {
				if(!c1.equals(c2)) {
					m.setC1(c1);
					m.setC2(c2);
					try {
						thisMatch = (Match) m.clone();
					}
					catch (CloneNotSupportedException e) {
						
					}
					this.setOfMatches.add(thisMatch);
				}
			}
		}
	}

	@Override
	protected String descriptionCompetition() {
		return "League";
	}

	@Override
	public String toString() {
		return "League, competitors = " + competitors;
	}
	
	
	
	
}

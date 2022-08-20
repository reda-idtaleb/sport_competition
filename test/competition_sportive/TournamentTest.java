package competition_sportive;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.*;

import competition.Competition;
import competition.Tournament;
import competitor.Competitor;
import match.Match;

public class TournamentTest extends CompetitionTest {
	
	/**
	 * create an instance of Tournament
	 */
	@Override
	protected Competition createCompetition() {
		return new Tournament(super.competitors , new MockMatch(super.c1 , super.c2));
	}
	
	/* test if a tournament return it's winner when all the matches are finished*/
	@Test
	public void testPlay() {
		MockTournament tournament = new MockTournament(super.competitors, mockMatch);
		assertNull(tournament.getWinnerOfTournament());
		
		tournament.play();
		assertSame(tournament.getWinnerOfTournament(), super.c1);
	}
	
	/* test if play(list) calls playMatch(c1,c2)*/
	@Test
	public void testPlayCallsPlayMatch() {
		MockTournament mockTournament = new MockTournament(super.competitors);	
		
		assertEquals(0, mockTournament.playMatchCalled);
		mockTournament.play(mockTournament.getCompetitors());
		
		// to make sure that there was only one match in the tournament(because we have just two competitors in the list of the competitors), 
		// and this one that was played.
		assertEquals(1, mockTournament.playMatchCalled);
		
	}
	
	/*test if the two competitors are assigned to a match in a tournament.*/
	@Test
	public void testmatchCoosenTwoPlayerRandomly() {
		MockTournament mockTournament = new MockTournament(super.competitors);
		Match matchBetweenC1C2 =  mockTournament.matchChooseTwoPlayerRandomly(super.competitors);
		assertSame(matchBetweenC1C2, mockTournament.getMatch());
	}
	
	/*test if the set of the matches of the tournament is organized when the number of competitors is a power of two*/
	@Test 
	public void testOrganizationOfMatches() {
		// we ensure that the set of matches (which will be played) will contain only one match. the one between c1 and c2.
		// because the list contains only two competitors, so there will only be one round in this tournament.
		MockTournament mockTournament = new MockTournament(super.competitors);
		mockTournament.organizationOfMatches(competitors);
		assertEquals(1, mockTournament.getSetOfMatches().size());
	}
	
	protected class MockTournament extends Tournament {
		
		public int playMatchCalled = 0;
		public int playCalled;
		
		public MockTournament(List<Competitor> competitors) {
			super(competitors);
		}

		public MockTournament(List<Competitor> competitors, Match match) {
			super(competitors, match);
		}
		
		/**
		 * create a Match between two competitors
		 * @param competitors the list of the competitors
		 * @return return an object Match between two competitors
		 */
		public Match matchChooseTwoPlayerRandomly(List<Competitor> competitors) {
			Competitor c1 = competitors.get(0);
			Competitor c2 = competitors.get(1);
			
			this.match.setC1(c1);
			this.match.setC2(c2);
			
			return this.match;
		}
		
		public void play(List<Competitor> competitors) {
			this.organizationOfMatches(competitors);
			super.play(competitors);
			playCalled ++;
		}
		
		public void playMatch(Competitor c1 , Competitor c2) {
			super.playMatch(c1, c2);
			playMatchCalled ++ ;
		}
		
		public void organizationOfMatches(List<Competitor> competitors) {
			super.organizationOfMatches(competitors);
		}
		

	}
	
	public static junit.framework.Test suite() {
		return new junit.framework.JUnit4TestAdapter(TournamentTest.class);
	}
}

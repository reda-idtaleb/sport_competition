package competition_sportive;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.*;

import competition.Competition;
import competition.League;
import competitor.Competitor;


public class LeagueTest extends CompetitionTest{

	@Override
	protected Competition createCompetition() {
		return new League(super.competitors, new MockMatch(super.c1, super.c2));
	}
	
	@Test
	public void testPlayCallsPlayMatch() {
		MockLeague mockLeague = new MockLeague(super.competitors);	
		
		assertEquals(0, mockLeague.playMatchCalled);
		mockLeague.play(mockLeague.getCompetitors());
		
		int n = mockLeague.getCompetitors().size();
		//to ensure that the set contains home and away matches.
		assertEquals(n*(n-1), mockLeague.playMatchCalled);
	}
	
	@Test
	public void testOrganizationOfMatches() {
		MockLeague mockLeague = new MockLeague(super.competitors);	
		mockLeague.organizationOfMatches(competitors);
		assertEquals(2, mockLeague.getSetOfMatches().size());
	}
	
	public static junit.framework.Test suite() {
		return new junit.framework.JUnit4TestAdapter(LeagueTest.class);
	}
	
	protected class MockLeague extends League{
		 
		public int playMatchCalled = 0;
		public int playCalled = 0;

		public MockLeague(List<Competitor> competitors) {
			super(competitors);
		}
		
		public void play(List<Competitor> competitors) {
			this.organizationOfMatches(competitors);
			super.play(competitors);
			playCalled ++ ;
		}
		
		public void playMatch(Competitor c1 , Competitor c2) {
			super.playMatch(c1, c2);
			playMatchCalled ++ ;

			
		}

		public void organizationOfMatches(List<Competitor> competitors) {
			super.organizationOfMatches(competitors);
		}

	}
}

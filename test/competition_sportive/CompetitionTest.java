package competition_sportive;

import static org.junit.Assert.*;

import org.junit.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import competition.Competition;
import competitor.Competitor;
import match.Match;
import selection.Selection;

public abstract class CompetitionTest {
	
	protected Competition competition; 	
	protected List<Competitor> competitors;
	protected Competitor c1;
	protected Competitor c2;
	protected MockCompetition mockCompetition;
	protected MockMatch mockMatch;
	protected MockSelection selection;
	protected Competitor c3;
	protected Competitor c4;
	
	protected abstract Competition createCompetition();
	
	@Before
	public void init() {		
		this.c1 = new Competitor("ML");
		this.c2 = new Competitor("RI");
		this.c3 = new Competitor("CD");
		this.c4 = new Competitor("ME");

		
		competitors = new ArrayList<Competitor>();
		competitors.add(c1);
		competitors.add(c2);
		
		this.competition = this.createCompetition();
		
		this.mockMatch = new MockMatch(c1, c2);
		this.mockCompetition = new MockCompetition(competitors, mockMatch);
		
		this.selection = new MockSelection();
	}
	

	/* Tests if addCompetitor adds a competitor to the list of the competitors*/
	@Test
	public void testAddCompetitor() {
		this.competition.addCompetitor(c1);
		assertTrue(competition.getCompetitors().contains(c1));
	}
	
	/* test if play calls the abstract method play */
	@Test 
	public void testPlayCallsAbstractPlay() {
		assertEquals(0, mockCompetition.playCalled);
		mockCompetition.play();
		assertEquals(1, mockCompetition.playCalled);
	}
	
	/* tests if playMatch calls PlayAMatch of the class Match*/
	@Test
	public void testPlayMatchCallsPlayAMatch() {
		assertEquals(mockMatch.nbCallsPlayAMatch, 0);
		mockCompetition.playMatch(c1, c2);
		assertEquals(1, mockMatch.nbCallsPlayAMatch);
	}
	
	/*tests if resultList */
	@Test 
	public void testresultList() {
		mockCompetition.play();
		Map<Competitor, Integer> map = mockCompetition.resultList();
		assertSame(1, map.get(c1)) ;
		assertSame(0, map.get(c2)) ;
	}
	
	private class MockCompetition extends Competition{

		public int playCalled = 0;
		public int playMatchCalled = 0;
		
		
		public MockCompetition(List<Competitor> competitors) {
			super(competitors);
		}

		public MockCompetition(List<Competitor> competitors, Match match) {
			super(competitors, match);
		}


		@Override
		public void play(List<Competitor> competitors) {
			super.playMatch(competitors.get(0),competitors.get(1));
			this.playCalled++;
		}
		
		public void playMatch(Competitor c1 , Competitor c2) {
			super.playMatch(c1, c2);
			playMatchCalled ++ ;
		}

		@Override
		public void organizationOfMatches(List<Competitor> competitors) {
			super.setOfMatches.add(this.match);
			
		}

		@Override
		protected String descriptionCompetition() {
			return "MockCompetition";
		}
				
	}
	
	/** A class of a fake match */
	protected class MockMatch extends Match {
		/** the number of the calls of the method calculateWinnerOfMatch */
		public int nbCallsCalculateWinner;
		public int nbCallsPlayAMatch;

		/**
		 * Creates a mock match
		 */
		public MockMatch() {
			super();
		}

		/**
		 * Creates a fake match between two competitors
		 * 
		 * @param c1 the first competitor of the match
		 * @param c2 the second competitor of the match
		 */
		public MockMatch(Competitor c1, Competitor c2) {
			super(c1, c2);
		}

		/**
		 * plays a match between two competitors
		 * 
		 * @param c1 the first competitor
		 * @param c2 the second competitor
		 */
		public void playAMatch(Competitor c1, Competitor c2) {
			super.playAMatch(c1, c2);
			nbCallsPlayAMatch++;
		}

		/**
		 * returns the first competitor as a winner
		 * 
		 * @param first  competitor
		 * @param second competitor
		 * @return the first competitor as a winner
		 */
		@Override
		public Competitor calculateWinnerOfMatch(Competitor c1, Competitor c2) {
			nbCallsCalculateWinner++;
			return c1;
		}

	}
	
	protected class MockSelection implements Selection{	
		public int nbCalled;
		
		@Override
		public int getNbGroups(List<Competitor> competitors) {
			nbCalled ++ ;
			return 1;
		}

		@Override
		public List<Competitor> getPlayersSecondRound(Map<Competitor, Integer> ranking, List<Competitor> competitors, int nbOfPools) {
			List<Competitor> l = new ArrayList<Competitor>();
			Competitor c3 = new Competitor("CD");
			Competitor c4 = new Competitor("ME");
			l.add(c3);
			l.add(c4);
			return l;
		}

		@Override
		public List<Integer> possibleNbOfGroups(List<Competitor> competitors) {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
}

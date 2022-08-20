package competition_sportive;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.Before;
import org.junit.Test;

import competitor.Competitor;
import match.Match;

public abstract class MatchTest {

	protected Competitor c1;
	protected Competitor c2;
	protected Match match;
	protected MockMatch mockMatch;

	
	public abstract Match createMatch();
	
	@Before
	public void init() {
		c1 = new Competitor("ML");
		c2 = new Competitor("RI");
		match = this.createMatch();	
		mockMatch = new MockMatch(c1, c2);
	}
	
	/** tests the creation of the match*/
	@Test
	public void creationOfMatch() {
		assertSame(c1, match.getC1());
		assertSame(c2, match.getC2());
	}
	
	/** tests if getWinner returns the winner when the match is played*/
	@Test
	public void testGetWinnerWhenOk() throws IllegalStateException {
		mockMatch.playAMatch( c1, c2);
		assertSame(c1, mockMatch.getWinner());
	} 
	
	/** tests if getWinner throws the exception when the match hasn't been played yet*/
	@Test(expected = IllegalStateException.class )
	public void testGetWinnerWhenNotOk() throws IllegalStateException{
		mockMatch.getWinner();
	}
	
	/** tests if PlayAMatch sets the match as played*/
	@Test
	public void testPlayAMatchSetsTheMatchAsPlayed() {
		mockMatch.playAMatch(c1, c2);
		assertTrue(mockMatch.isPlayed());
	}
	
	/** tests if playAMatch calls calculatesWinner */
	@Test	
	public void testPlayAMatchCallsCalculateWinner() {
		int nbCallsBefore = mockMatch.nbCallsCalculateWinner;
		mockMatch.playAMatch(c1, c2);
		assertEquals(1,mockMatch.nbCallsCalculateWinner-nbCallsBefore);
	}
	
	/** tests Equals*/
	public void testEquals() {
		Match match1 = new MockMatch(c1,c2);
		Match match2 = new MockMatch(c1,c2);
		assertTrue(match1.equals(match2));
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
	
	

}

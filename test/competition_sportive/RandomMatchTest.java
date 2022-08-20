package competition_sportive;

import static org.junit.Assert.*;
import org.junit.*;

import match.Match;
import match.RandomMatch;

public class RandomMatchTest extends MatchTest {

	@Override
	public Match createMatch() {
		return new RandomMatch(c1,c2);
	}
	
	/** tests if calculate winner returns a random winner*/
	@Test
	public void testCalculateWinnerOfMatch() {
		assertSame(c1, mockMatch.calculateWinnerOfMatch(c1, c2));
	}
	
	public static junit.framework.Test suite() {
		return new junit.framework.JUnit4TestAdapter(RandomMatchTest.class);
	}
}

package competition_sportive;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import competitor.Competitor;
import org.junit.Test;

import selection.BestOfGroup;
import selection.Selection;

public class BestOfGroupsTest extends SelectionTest{

	@Override
	protected Selection createSelection() {
		return new BestOfGroup();
	}
	
	@Override
	protected List<Integer> expectedListOfpossibleNbGroups() {
		List<Integer> result =  new ArrayList<Integer>();
	    result.add(2);
		return result;
	}
	
	/** test if getplayersSecondRound adds only the best players of each Leagues  */
	@Test
	public void getPlayersSecondRoundTest() {
		Map<Competitor, Integer> map = new HashMap<Competitor, Integer>();
		assertTrue(selection.getPlayersSecondRound(map, super.competitors, 2).isEmpty());
		
		map.put(super.competitors.get(0), 1);
		map.put(super.competitors.get(1), 2);
		
		assertEquals(selection.getPlayersSecondRound(map,super.competitors, 2).size(), 1);
		assertEquals(selection.getPlayersSecondRound(map,super.competitors, 2).get(0), super.competitors.get(0));
	}
	
	public static junit.framework.Test suite() {
		return new junit.framework.JUnit4TestAdapter(BestOfGroupsTest.class);
	}
}

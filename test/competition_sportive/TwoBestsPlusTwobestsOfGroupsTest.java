package competition_sportive;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import competitor.Competitor;
import selection.Selection;
import selection.TwoBestsPlusTwoBestsOfThirds;

public class TwoBestsPlusTwobestsOfGroupsTest extends SelectionTest {

	@Override
	protected Selection createSelection() {
		return new TwoBestsPlusTwoBestsOfThirds();
	}

	@Override
	protected List<Integer> expectedListOfpossibleNbGroups() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(2);
		return list;
	}
	
	@Test
	public void getPlayersSecondRoundAddsfirstsAndSecondsTest() {
		Map<Competitor, Integer> map = new HashMap<Competitor, Integer>();
		assertTrue(selection.getPlayersSecondRound(map, super.competitors, 2).isEmpty());
		
		map.put(super.competitors.get(0), 1);
		map.put(super.competitors.get(1), 2);
		
		assertEquals(selection.getPlayersSecondRound(map,super.competitors, 2).size(), 2);
	}
	
	@Test 
	public void getPlayersSecondRoundAddsThirds(){
		Map<Competitor, Integer> map = new HashMap<Competitor, Integer>();
		
		super.competitors.get(2).setNbPoints(9);
	    super.competitors.get(5).setNbPoints(5);
	    
	    
	    map.put(super.competitors.get(0), 1);
		map.put(super.competitors.get(1), 2);
		map.put(super.competitors.get(2), 3);
		map.put(super.competitors.get(3), 1);
		map.put(super.competitors.get(4), 2);
		map.put(super.competitors.get(5), 3);
		
		List<Competitor> listOfCompetitorsForSecondRound = selection.getPlayersSecondRound(map,super.competitors, 2);
		assertEquals(listOfCompetitorsForSecondRound.size(), 4);
			
	}
	
	public static junit.framework.Test suite() {
		return new junit.framework.JUnit4TestAdapter(TwoBestsPlusTwobestsOfGroupsTest.class);
	}
}

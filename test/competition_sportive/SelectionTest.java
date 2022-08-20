 package competition_sportive;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import competitor.Competitor;
import selection.Selection;

public abstract class SelectionTest {
	protected List<Competitor> competitors;
	protected Selection selection;
	
	/**
	 * create an instance of a selection
	 */
	protected abstract Selection createSelection();
	
	/**
	 * 
	 * @return a list of number of groups 
	 */
	protected abstract List<Integer> expectedListOfpossibleNbGroups();
	
	@Before
	public void init() {
		this.selection = createSelection();
		this.competitors = new ArrayList<Competitor>();
		
		Competitor c1 = new Competitor("Batter");
	        Competitor c2 = new Competitor("Raven");
		Competitor c3 = new Competitor("Blest");
		Competitor c4 = new Competitor("Roun");
		Competitor c5 = new Competitor("Moon");
		Competitor c6 = new Competitor("Earth");
		
		competitors.add(c1);
		competitors.add(c2);
		competitors.add(c3);
		competitors.add(c4);
		competitors.add(c5);
		competitors.add(c6);
	}
	
	@Test
	/** test if testGetPossibleNumberOfGroups return a list of possible numbers of groups depending on a selection*/
	public void testGetPossibleNumberOfGroups(){
		List<Integer> result = this.expectedListOfpossibleNbGroups();
	        assertEquals (result, this.selection.possibleNbOfGroups(this.competitors));
	}
	
	

}

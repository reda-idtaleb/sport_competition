package competition_sportive;

import static org.junit.Assert.*;
import org.junit.*;

import competitor.Competitor;

/* Tests the methods of the class Competitor */
public class CompetitorTest {


	
	/*tests the creation of the competitor */
	@Test
	public void testCompetitorCreation() {
		Competitor c1 = new Competitor("ML");
		assertEquals(c1.getName(), "ML");
	}
	
	/*tests if getName returns the right name*/
	@Test
	public void testGetName() {
		Competitor c1 = new Competitor("ML");

		assertEquals(c1.getName() ,"ML");
	}
	
	/*tests if setName sets the name correctly*/
	@Test
	public void testSetName() {
		Competitor c1 = new Competitor("ML");

		c1.setName("RI");
		assertEquals (c1.getName(), "RI");
	}
	
	/* tests if the points are added to the competitor*/
	@Test
	public void testAddPoints() {
		Competitor c1 = new Competitor("ML");
		int nbPointsBefore = c1.getNbPoints();
		c1.addPoints();
		assertEquals(1,c1.getNbPoints()-nbPointsBefore);
	}
	
	public static junit.framework.Test suite() {
		return new junit.framework.JUnit4TestAdapter(CompetitorTest.class);
	}
	

}

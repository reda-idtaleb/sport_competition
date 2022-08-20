package competition_sportive;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import Exception.NumberNotEvenException;
import competition.Competition;
import competition.League;
import competition.Master;
import competition.Tournament;
import competition_sportive.LeagueTest.MockLeague;
import competition_sportive.TournamentTest.MockTournament;
import competitor.Competitor;
import selection.BestOfGroup;
import selection.Selection;

public class MasterTest extends CompetitionTest {
	
	public LeagueTest leagueTest = new LeagueTest();
	
	@Override
	protected Competition createCompetition() {
		competitors.add(c3);
		competitors.add(c4);
		return new Master(super.competitors, super.selection,2);
	}
	
	/** tests the creation of the competition with the number of pools*/
	@Test
	public void testCreationOfMasterWithNbPools() {
		Master master = new Master(super.competitors, super.selection,2);
		assertEquals(2, master.getNbOfPools());
	}
	
	/** tests if set tournament tests the right tournament*/
	@Test
	public void testSetTournament() {
		Master master = new Master(super.competitors, super.selection,2);
		Tournament t = new Tournament (super.competitors);
		master.setTournament(t);
		assertEquals(t, master.getTournament());
	}
	
	/**tests  if set selection sets the right selection*/
	@Test
	public void testSetSelection() {
		Master master = new Master(super.competitors, null,2);
		Selection s =  new BestOfGroup();
		master.setSelection(s);
		assertEquals(s,master.getSelection());
		
	}
	/**
	 * @throws NumberNotEvenException **/
	@Test(expected =  NumberNotEvenException.class )
	public void testMasterVerificationWhenNotOk() throws NumberNotEvenException {
		Master master = new Master(super.competitors, super.selection,2);
		master.addCompetitor(new Competitor("CN"));
		master.masterVerification();
	}
	
	/**
	 * @throws NumberNotEvenException **/
	@Test
	public void testMasterVerificationWhenOk() throws NumberNotEvenException  {
		Master master = new Master(super.competitors, super.selection,2);
		master.masterVerification();
	}
	
	
	
	/** tests if add competition , adds a league competition to the list of leagues*/
	@Test
	public void testAddCompetition() {
		Master m = new Master(super.competitors, super.selection);
		League l = new League(super.competitors);	
		assertFalse(m.getLeagues().contains(l));
		m.addCompetition(l);
		assertTrue(m.getLeagues().contains(l));
	}
	
	/** tests if play plays all the leagues */
	@Test
	public void testPlayPlaysAllTheLeagues() {
		MockMaster mockMaster = new MockMaster(super.competitors, super.selection);
		mockMaster.play();
		List<MockLeague> leagues = mockMaster.getMLeagues();
		for (MockLeague l : leagues) {
			assertEquals(l.playCalled, 1);
		}	
	}
	
	/** tests if play plays the tournament*/
	@Test
	public void testPlayPlaysTournament() {
		MockMaster mockMaster = new MockMaster(super.competitors, super.selection);
		mockMaster.play();
		assertEquals(1, mockMaster.getMTournament().playCalled);
	}
	
	
	/** test if the number of the leagues equals the number of groups*/
	@Test
	public void testOrganisationOfMatches() {
		MockMaster mockMaster = new MockMaster(super.competitors, super.selection, 2);
		mockMaster.organizationOfMatches(competitors);
		assertEquals(2,mockMaster.getMLeagues().size());
	} 
	
	private class MockMaster extends Master {
		private TournamentTest tournamentTest = new TournamentTest();
		
		private List<MockLeague> mockLeagues;
		private MockTournament mockTournament = tournamentTest.new MockTournament(competitors) ;

		public MockMaster(List<Competitor> competitors, Selection selection) {
			super(competitors, selection);
			this.mockLeagues = new ArrayList<MockLeague>();
			setTournament(mockTournament);

		}
		
		public MockMaster(List<Competitor> competitors, Selection selection, int nbPools) {
			super (competitors, selection , nbPools);
			this.mockLeagues = new ArrayList<MockLeague>();
			setTournament(mockTournament);
		}

		public void play(List<Competitor> competitors) {
			Map < Competitor , Integer> map = new HashMap< Competitor, Integer>();
			for(MockLeague c : mockLeagues) {
				c.play(competitors);
				fillsTheRankingMap(map, c);
			}
			mockTournament.setCompetitors(selection.getPlayersSecondRound(map, competitors, nbOfPools));
			mockTournament.play(competitors);	
		}

		@Override
		public void organizationOfMatches(List<Competitor> competitors) {
				List<List<Competitor>> groups = getGroups(competitors);
				int n = super.nbOfPools;
				for(int i = 0 ; i < n ; i++) {
					this.mockLeagues.add(leagueTest.new MockLeague(groups.get(i)));
				}			
		}
		
		public List<MockLeague> getMLeagues(){
			return mockLeagues;
		}
		
		public MockTournament getMTournament() {
			return mockTournament;
		}
		
		
	}
	
	
	
	

}
	

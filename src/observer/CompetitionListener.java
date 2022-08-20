package observer;

/** A competition listener*/
public interface CompetitionListener extends java.util.EventListener {
	
	/**
	 * 
	 * @param c
	 */
	public void competitionPlayed(CompetitionEvent c);
}

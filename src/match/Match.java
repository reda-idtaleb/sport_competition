package match;

import competitor.Competitor;

/** A class of a match */
public abstract class Match implements Cloneable {
	/** the competitors of the match */
	protected Competitor c1;
	protected Competitor c2;
	
	/** the winner of the match*/
	protected Competitor winner;
	
	/** to know if the match is played or not*/
	protected boolean isPlayed;
	
	/** Creates a match
	 */
	public Match() {	
	}

	/**
	 * Creates a match to be played by two competitors
	 * @param c1 the first competitor
	 * @param c2 the second competitor
	 */
	public Match(Competitor c1, Competitor c2) {
		this.c1 = c1;
		this.c2 = c2;
		this.winner = null;
		this.isPlayed = false;
	}
	
	/**returns the Competitor c1
	 * @return the competitor c1
	 */
	public Competitor getC1() {
		return this.c1;
	}
	
	/**sets the first competitor
	 * @param c1 the first competitor to set
	 */
	public void setC1(Competitor c1) {
		this.c1 = c1;
	}

	/**returns the second Competitor 
	 * @return the second competitor
	 */
	public Competitor getC2() {
		return this.c2;
	}
	
	/**sets the second competitor
	 * @param c2 the second competitor to set
	 */
	public void setC2(Competitor c2) {
		this.c2 = c2;
	}

	/** returns if the competition has been played
	 * @return a boolean indicating if the match has been played
	 */
	public boolean isPlayed() {
		return isPlayed;
	}
	
	/**returns the winner of the match 
	 * @return the winner of the match
	 * @throws IllegalStateException if there's no winner yet
	 */
	public Competitor getWinner() throws IllegalStateException{
		if(this.isPlayed)
			return winner;
		else {
			throw new IllegalStateException("No winner yet! The match is not played yet!");
		}
	}
	
	/** Sets the calculated winner as the winner of the this match
	 * @param c1 first competitor of the match
	 * @param c2 second competitor of the match
	 */
	public void playAMatch(Competitor c1, Competitor c2) {
		Competitor winner = this.calculateWinnerOfMatch(c1, c2);
		this.isPlayed = true ;
		this.winner = winner;
		displayResult(c1, c2);
	}

	/** displays the result of the match
	 * @param c1 the first competitor of the match
	 * @param c2 the second competitor of the match
	 */
	public void displayResult(Competitor c1, Competitor c2) {
		System.out.println(c1.getName() + "\tvs\t" + c2.getName() + "\t-->\t" + this.winner + "\twins!");
	}
	
	/** Calculates the winner of the match depending on its type
	 * 
	 * @param c1 the first competitor
	 * @param c2 the second competitor
	 * @return the winner of the match depending on its type
	 */
	protected abstract Competitor calculateWinnerOfMatch(Competitor c1, Competitor c2);

		
	/** Creation of a cloned Match*
	 * @return an object of match
	 * @throws CloneNotSupportedException when the object can not be cloned*/
	public Object clone() throws CloneNotSupportedException {
		Match monClone = (Match) super.clone();
		return monClone;
	}
	
	/** compares two matches and returns if they're equal
	 * @param obj the match object to compare with
	 * @return true if the two matches are equal, false otherwise
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Match))
			return false;
		Match other = (Match) obj;
		if (c1 == null) {
			if (other.c1 != null)
				return false;
		} else if (!c1.equals(other.c1))
			return false;
		if (c2 == null) {
			if (other.c2 != null)
				return false;
		} else if (!c2.equals(other.c2))
			return false;
		if (isPlayed != other.isPlayed)
			return false;
		if (winner == null) {
			if (other.winner != null)
				return false;
		} else if (!winner.equals(other.winner))
			return false;
		return true;
	}
}



package domain.core;

/**
 * objects that represent the possible rates for songs
 * @author fc58199 - Daniela Camarinha
 * @author fc58193 - Rafael Ribeiro
 */
public enum Rate {
	
	UNRATED (0),
	LIKE (1);
	
	int rating;
	
	 Rate(int i) {this.rating = i;}

	/**
	 * getter method that returns the rate
	 * @return this.rating = the current rank of the song
	 */
	public int getRating() {return this.rating;}
	
	/**
	 * Increments rating by one, if the rate it's already at its max,
	 * it stays at its max
	 */
	public void incRating() {this.rating = 1;}
	
	/**
	 * Decrements rating by one, if the rate it's already at its minimum,
	 * it stays at its minimum
	 */
	public void decRating() {this.rating = 0;}
	
	/**
	 * toString method for Rate
	 */
	@Override
	public String toString() {return (this.rating == 1) ? "LIKED":"UNRATED";}
	
	public void oppositeRate() {
		this.rating = (this.rating == 1) ? 0 : 1;
	}
	
	


}

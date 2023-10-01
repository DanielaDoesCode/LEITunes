/**
 * 
 */
package domain.core;

import java.util.List;

import domain.facade.ISong;
import util.adts.RegExpMatchable;

/**
 * Objects of this type represent Songs.
 * @author fc58199 - Daniela Camarinha
 * @author fc58193 - Rafael Ribeiro
 *
 */
public class Song implements ISong, RegExpMatchable {
	
	int timesPlayed;
	Rate rating;
	String pathName;
	SongMetaInfo metaInfo;
	
	public Song(SongMetaInfo info, String fileName) {
		this.timesPlayed = 0;
		this.pathName = fileName;
		this.rating = Rate.UNRATED;
		metaInfo = info;
	}

	/**
	 * Increments the number of times the song was played
	 */
	@Override
	public void incTimesPlayed() {
		timesPlayed++;

	}
	
	
	/**
	 * Returns the number of times the song was played
	 * 
	 * @return number of times the song was played
	 */
	@Override
	public int getTimesPlayed() {
		return this.timesPlayed;
	
	}

	
	/**
	 * Increments the song's rating
	 * @ensures getRating().equals(\old(getRating().inc())
	 */
	@Override
	public void incRating() {
		this.rating = Rate.LIKE;
		rating.incRating();
	}
	
	
	/**
	 * Decrements the song's rating
	 * @ensures getRating().equals(\old(getRating().dec())
	 */
	@Override
	public void decRating() {
		this.rating = Rate.UNRATED;
		rating.decRating();
		
	}
	
	/**
	 * Returns the title of the song
	 * 
	 * @return the song's title
	 * @ensures \result != null
	 */
	@Override
	public String getSongTitle() {
		return metaInfo.title();
		
	}

	/**
	 * Returns the genre of the song
	 * 
	 * @return the song's genre
	 * @ensures \result != null
	 */
	@Override
	public String getGenre() {
		return metaInfo.genre();
	}
	
	/**
	 * Returns the artist list of the song
	 * 
	 * @return the song's artists list
	 * @ensures \result != null
	 */
	@Override
	public List<String> getArtists() {
		return metaInfo.artists();
	}
	
	/**
	 * Returns the album name of the song
	 * 
	 * @return the song's album name
	 * @ensures \result != null
	 */
	@Override
	public String getAlbum() {
		return metaInfo.album();
	}
	
	/**
	 * Return the filename  of the song
	 * 
	 * @return the song's filename
	 * @ensures \result != null 
	 */
	@Override
	public String getFilename() {
		return this.pathName;
	}

	/**
	 * Checks if any song data matches the given regular expression
	 *  
	 * @param regexp the regular expression to be used
	 * @requires regexp != null
	 * @return whether some data of the song matches with the given regexp
	 */
	@Override
	public boolean matches(String regexp) {
		return this.metaInfo.matches(regexp);
	}

	/**
	 * Returns the rating of the song
	 * 
	 * @return the song's rating
	 * @ensures \result != null
	 */
	@Override
	public Rate getRating() {
		return this.rating;
	}
	
	/**
	 * toString method for Song
	 * 
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[" + this.getSongTitle() + ", " + this.getAlbum() + ", " + this.getGenre() + ", [");
		List<String> songArtists = this.getArtists();
		for (int i = 0; i < songArtists.size(); i++) {
			sb.append(songArtists.get(i));
			if (i < songArtists.size() - 1)
				sb.append("; ");
		}
		sb.append("]] --- " + this.getRating().getRating() + " -- " + this.getTimesPlayed());
		return sb.toString();
	}
	
	
	/**
	 * equals method for Song
	 * @param other = object to compare with \this
	 * 
	 */
	@Override
	public boolean equals(Object other) {
		if(other == null) {
			return false;
		}
		if(! (other instanceof Song)) {
			return false;
		}
		if(this == other) {
			return true;
		}
		Song o = (Song) other;
	    return this.getAlbum() == o.getAlbum() && 
	    	   this.getGenre() == o.getGenre() && 
	    	   this.getRating() == o.getRating() &&
	    	   this.getSongTitle() == o.getSongTitle() &&
	    	   this.getTimesPlayed() == o.getTimesPlayed();
	    		

	}

}

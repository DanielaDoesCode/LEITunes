package domain.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author fc58199 Daniela Camarinha
 * @author fc58193 Rafael Ribeiro
 *
 *Tests for the Song Class
 */
class SongTests {
	
	private Song song;
	private SongMetaInfo info;
	private List<String> artists;
	public static final String SONG_TITLE = "We Are Number One";
	public static final String GENRE = "Heavy-Metal";
	public static final String ALBUM = "JoJo's Bizarre Adventure";
	public static final String ARTIST1 = "Joe Mama";
	public static final String ARTIST2 = "Eleven Seven";
	public static final String ARTIST3 = "6ixty9iner";
	public static final String FILE_NAME = "testName.mp3";

	/**
	 * Method to quickly set up the Song for testing
	 */
	@BeforeEach
	void setUp() throws Exception {
		artists = new ArrayList<>();
		artists.add(ARTIST1);
		artists.add(ARTIST2);
		artists.add(ARTIST3);
		info = new SongMetaInfo(SONG_TITLE, GENRE, artists, ALBUM);
		song = new Song(info, FILE_NAME);
	}

	/**
	 * Testing times played
	 */
	@Test
	void timesPlayedTest() {
		int timesPlayed = this.song.getTimesPlayed();
		assertTrue(timesPlayed == 0);
	}
	
	
	/**
	 * Testing times played
	 */
	@Test
	void inctimesPlayedTest() {
		song.incTimesPlayed();
		int timesPlayed = this.song.getTimesPlayed();
		
		assertTrue(timesPlayed == 1);
	}
	
	/**
	 * Tests if the artist information is correctly stored in the Song
	 */
	@Test
	void artistsTest() {
		List<String> songArtists = song.getArtists();
		assertTrue(songArtists.size() == 3 &&
				   songArtists.get(0).equals(ARTIST1) &&
				   songArtists.get(1).equals(ARTIST2) &&
				   songArtists.get(2).equals(ARTIST3));
	}
	
	
	/**
	 * Testing rating
	 */
	@Test
	void ratingTest() {
		boolean result = song.getRating() == Rate.UNRATED;
		assertTrue(result);
	}
	
	/**
	 * Testing the increasing of the rating
	 */
	@Test
	void incRatingTest() {
		song.incRating();
		boolean result = song.getRating() == Rate.LIKE;
		assertTrue(result);
	}
	
	
	/**
	 * Testing the decrease of rating
	 */
	@Test
	void decRatingTest() {
		song.incRating();
		song.decRating();
		boolean result = song.getRating() == Rate.UNRATED;
		assertTrue(result);
	}
	
	/**
	 * Testing toString
	 */
	@Test
	void toStringTest1() {
		String expected1 = "[We Are Number One, JoJo's Bizarre Adventure, Heavy-Metal, "
		                    + "[Joe Mama; Eleven Seven; 6ixty9iner]] --- 0 -- 0";

		boolean result = song.toString().equals(expected1);
		assertTrue(result);
	}
	
	/**
	 * Testing toString
	 */
	@Test
	void toStringTest2() {
		song.incRating();
		String expected2 = "[We Are Number One, JoJo's Bizarre Adventure, Heavy-Metal, "
		                    + "[Joe Mama; Eleven Seven; 6ixty9iner]] --- 1 -- 0";

		boolean result = song.toString().equals(expected2);
		assertTrue(result);
	}
	
	/**
	 * Testing toString
	 */
	@Test
	void toStringTest3() {
		song.incTimesPlayed();
		String expected1 = "[We Are Number One, JoJo's Bizarre Adventure, Heavy-Metal, "
		                    + "[Joe Mama; Eleven Seven; 6ixty9iner]] --- 0 -- 1";

		boolean result = song.toString().equals(expected1);
		assertTrue(result);
	}
	
	/**
	 * Testing matching the title with regex
	 */
	@Test
	void matchTitleTest() {
		boolean result = song.matches(SONG_TITLE);

		assertTrue(result);
	}
	
	/**
	 * Testing matching the genre with regex
	 */
	@Test
	void matchGenreTest() {
		boolean result = song.matches(GENRE);

		assertTrue(result);
	}
	
	/**
	 * Testing matching album
	 */
	@Test
	void matchAlbumTest() {
		boolean result = song.matches(ALBUM);

		assertTrue(result);
	}
	
	/**
	 * Testing matching artist with regex
	 */
	@Test
	void matchArtistTest() {
		boolean result = song.matches(ARTIST2);

		assertTrue(result);
	}
	
	/**
	 * Testing getSong
	 */
	@Test
	void getSongTitleTest() {
		boolean result = song.getSongTitle().equals(SONG_TITLE);

		assertTrue(result);
	}
	
	/**
	 * Testing getFilename
	 */
	@Test
	void getFilenameTest() {
		boolean result = song.getFilename().equals(FILE_NAME);

		assertTrue(result);
	}
	
	/**
	 * Testing getGenre
	 */
	@Test
	void getGenreTest() {
		boolean result = song.getGenre().equals(GENRE);

		assertTrue(result);
	}
	
	/**
	 * Testing getAlbum
	 */
	@Test
	void getAlbumTest() {
		boolean result = song.getAlbum().equals(ALBUM);

		assertTrue(result);
	}
	
	
	
	
	
	
	
	
	
	

}

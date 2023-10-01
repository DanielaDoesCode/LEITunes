package domain.facade;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;

import domain.core.MusicLibrary;
import domain.core.Song;
import domain.core.SongMetaInfo;
import domain.core.SongRatedLibraryEvent;


/**
 * @author fc58199 - Daniela Camarinha
 * @author fc58193 -  Rafael Ribeiro
 * 
 * Objects of this type represent controller for the interactions of MusicLibrary objects
 * 
 */
public class MusicLibraryController {

	public static final String NONE = "unknown";
	MusicLibrary library;

	public MusicLibraryController(MusicLibrary library) {
		this.library = library;
	}

	/**
	 * Returns the number of songs in the library
	 * @return library.size() = number of songs in the library
	 */
	public int numberOfSongs() {
		return this.library.size();
	}


	/**
	 * adds a song in a library controlled by \this
	 * obtaining the metaData file, if existent.
	 * @param filename = mp3 file name
	 * @requires filename to be an mp3 file
	 */
	public void addSong(String filename) {
		try {
			Mp3File song = new Mp3File(filename);
			if(song.hasId3v1Tag()) {
				
				ID3v1 id3v1Tag = song.getId3v1Tag();
				String name = id3v1Tag.getTitle() == null ? NONE: id3v1Tag.getTitle();
				String album = id3v1Tag.getAlbum() == null ? NONE: id3v1Tag.getAlbum();
				String genre = id3v1Tag.getGenreDescription() == null ? NONE: id3v1Tag.getGenreDescription();
				List<String> artists = Arrays.asList(id3v1Tag.getArtist().split(";"));
				SongMetaInfo info = new SongMetaInfo(name, genre, artists, album);
				this.library.add(new Song(info, filename));

			}else if(song.hasId3v2Tag()) {
				ID3v2 id3v2Tag = song.getId3v2Tag();
				String name = id3v2Tag.getTitle() == null ? NONE: id3v2Tag.getTitle();
				String album = id3v2Tag.getAlbum() == null ? NONE: id3v2Tag.getAlbum();
				String genre = id3v2Tag.getGenreDescription() == null ? NONE: id3v2Tag.getGenreDescription();
				List<String> artists = id3v2Tag.getArtist() == null ? Arrays.asList(NONE) : Arrays.asList(id3v2Tag.getArtist().split(";"));
				SongMetaInfo info = new SongMetaInfo(name, genre, artists, album);
				this.library.add(new Song(info, filename));
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Forwards the selection of a song in the controlled library, hence selecting
	 * and song in it
	 * @param i = index of song to select in the library
	 */
	public void selectSong(int i) {

		if(i >= 0 && i < this.numberOfSongs())
			this.library.select(i);

	}

	/**
	 * Returns the selected song, if existent
	 * @return
	 */
	public Optional<ISong> getSelectedSong(){

		if(this.library.someSelected())
			return Optional.of(this.library.getSelected());
		
		return Optional.empty();
	}

	/**
	 * Removes the selected song in the controlled library, if existent
	 * @ensures this.library.someSelected() = true
	 * 
	 */
	public void removeSelectedSong() {
		if(this.library.someSelected())
			this.library.remove();
	}

	/**
	 * Forwards the playing of the selected song in the library for the library itself
	 * 
	 *@requires this.library.someSelected() = true
	 */
	public void play() {
		if(this.library.someSelected())
			this.library.play();
	}


	/**
	 * Forwards the stopping of the selected song in the library for the library itself,
	 * if it's playing, otherwise does nothing
	 * 
	 *@requires this.library.someSelected() = true
	 */
	public void stop() {
		if(this.library.isPlaying())
			this.library.stop();
	}

	/**
	 * Forwards the increase of selected song rate in the library for the library itself,
	 * if there's a song selected, otherwise does nothing
	 * 
	 */
	public void incRateSelected() {
		if(this.library.someSelected())
		    this.library.incRateSelected();
	}

	/**
	 * Forwards the decrease of selected song rate in the library for the library itself,
	 * if there's a song selected, otherwise does nothing
	 * 
	 */
	public void decRateSelected() {
		if(this.library.someSelected())
			this.library.decRateSelected();
	}

	/**
	 * Returns an iterable structure with the songs in the library that match the regular expression,
	 * doing this by forwarding the request to the library
	 * 
	 */
	public Iterable<ISong> getMatches(String reexp){
		return this.library.getMatches(reexp);
	}

	/**
	 * Returns an iterable structure with all the songs in the library,
	 * forwarding the request to the library
	 * 
	 */
	public Iterable<ISong> getSongs(){
		return this.library.getSongs();
	}
	
	/**
	 * toString method for the library
	 * 
	 */
	@Override
	public String toString() {
		return this.library.toString();
	}




}

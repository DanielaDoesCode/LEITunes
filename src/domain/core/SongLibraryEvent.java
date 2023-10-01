package domain.core;
import util.observer.Event;

/**
 * @author fc58199 - Daniela Camarinha
 * @author fc58193 - Rafael Ribeiro
 * 
 * Objects of this type represent generic Events that target songs
 * 
 */
public abstract class SongLibraryEvent implements Event{
	
	private Song song;
	private MusicLibrary library;
	
	
	protected SongLibraryEvent(Song song, MusicLibrary library) {
		this.song = song;
		this.library = library;
		
	}
	
	/**
	 * returns the song targeted by the event
	 * @return song = song from the event
	 */
	public Song getSong() {
		return this.song;
	}
	
	/**
	 * returns the library where the targeted song is from
	 * @return this.library = library where song is from
	 */
	public MusicLibrary getLibrary() {
		return this.library;
	}

}

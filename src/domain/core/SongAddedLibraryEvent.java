package domain.core;

/**
 * @author fc58199 - Daniela Camarinha
 * @author fc58193 - Rafael Ribeiro
 * 
 * Objects of this type represent Events that target the addition of a song
 * to a library
 * 
 */
public class SongAddedLibraryEvent extends SongLibraryEvent {
       
	public SongAddedLibraryEvent(Song song, MusicLibrary library) {
		super(song, library);
	}
}

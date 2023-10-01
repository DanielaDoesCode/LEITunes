package domain.core;

/**
 * @author fc58199 - Daniela Camarinha
 * @author fc58193 - Rafael Ribeiro
 * 
 * Objects of this type represent Events that target the removal of a song
 * from a library
 * 
 */
public class SongRemovedLibraryEvent extends SongLibraryEvent {
	
	public SongRemovedLibraryEvent(Song song, MusicLibrary library) {
		super(song, library);
	}

}

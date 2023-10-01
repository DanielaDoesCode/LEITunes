package domain.core;

/**
 * @author fc58199 - Daniela Camarinha
 * @author fc58193 - Rafael Ribeiro
 * 
 * Objects of this type represent Events that target the rating of a song
 * in a library
 * 
 */
public class SongRatedLibraryEvent extends SongLibraryEvent{
	

	public SongRatedLibraryEvent(Song selected, MusicLibrary library) {
		super(selected, library);
	}
	


}

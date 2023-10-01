package domain.playlists;

import domain.core.MusicLibrary;
/**
 * @author fc58199 - Daniela Camarinha
 * @author fc58193 -  Rafael Ribeiro
 * 
 * Objects of this type represent Manual Playlist objects that allow manual removal and addition of songs
 * 
 */
public class ManualPlaylist extends AbsPlaylist{

	public ManualPlaylist(String playlistName, MusicLibrary library) {
		super(playlistName, library);

	}

	/**
	 * Equals method for Manual Playlists 
	 * 
	 * @return true= if the objects are the same/false= otherwise
	 */
	@Override
	public boolean equals(Object other) {
		return this==other;
	}


}

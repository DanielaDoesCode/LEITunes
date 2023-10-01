package domain.playlists;

import domain.core.MusicLibrary;
import domain.facade.ISong;

/**
 * @author fc58199 - Daniela Camarinha
 * @author fc58193 -  Rafael Ribeiro
 * 
 * Objects of this type represent playlists that automatically add and remove songs. these objects do not allow manual 
 * adding and removing of songs
 * 
 */
public abstract class SmartPlaylist extends AbsPlaylist{



	protected SmartPlaylist(String name, MusicLibrary library) {
		super(name, library);

	}

	/**
	 * 
	 * Automatically adds a song to the playlist
	 * 
	 */
	protected void addAutomatic(ISong song) {
		super.add(song);
	}

	/**
	 * 
	 * Automatically removes the selected song in the playlist
	 * @param index = position of the song to be removed
	 */
	protected void removeAutomatic(int index) {
		ISong songSelected = super.playlist.getSelected(); //stores the prior selected song
		/*Selects the song in the index given and removes it*/
		super.playlist.select(index);
		super.remove();
		int selected = 0;
		/*Checks for the index of the prior selected song and selects it again*/
		for(int i = 0; i < super.playlist.size(); i++) {
			if(songSelected.equals(super.playlist.get(i))) {
				selected = i;
				break;
			}
		}
		super.playlist.select(selected);

	}

}

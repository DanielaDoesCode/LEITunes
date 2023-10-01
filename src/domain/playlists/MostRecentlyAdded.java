package domain.playlists;

import domain.core.MusicLibrary;
import domain.core.SongAddedLibraryEvent;
import domain.core.SongLibraryEvent;
import domain.core.SongRemovedLibraryEvent;
/**
 * @author fc58199 - Daniela Camarinha
 * @author fc58193 -  Rafael Ribeiro
 * 
 * Objects of this type represent playlists that automatically store the latest added songs from a specific library,
 * these objects do not allow manual adding or removal of songs
 * 
 */
public class MostRecentlyAdded extends SmartPlaylist{

	private static final int MAX_SONGS = 5; //max songs that are allowed to be in the playlist, max size of the playlist

	protected MostRecentlyAdded(String name, MusicLibrary library) {
		super(name, library);
	}

	/**
	 * Reaction to property change events, namely those emitted by the player
	 * (can affect the selected song and song being played)
	 */
	@Override
	public void processEvent(SongLibraryEvent e) {

		if(e instanceof SongAddedLibraryEvent) { //when a songs has been added to the library

			if(this.playlist.size() <= MAX_SONGS) {
				super.addAutomatic(e.getSong());
			}else {
				super.removeAutomatic(0);
				super.addAutomatic(e.getSong());
			}

		}else if(e instanceof SongRemovedLibraryEvent) {

			if(super.playlist.size() < MAX_SONGS){
				//removing the event song from the playlist
				int index = 0;
				boolean test = true; //guards so we dont have breaks inside the for loop
				for(int i = 0; i < this.playlist.size() && test; i++){
					if(super.playlist.get(i).equals(e.getSong())){
						index = i;
						test = false;
					}
					removeAutomatic(index);
					boolean test2 = true;
					//finding another one which isnt in the playlist already and adding it
					for(int j = 0; j < super.lib.size() && test2; j++){
						for(int k = 0; k < super.playlist.size(); k++){
							if(!this.playlist.get(k).equals(super.lib.get(j))){
								super.playlist.add(super.lib.get(j));
								test2 = false;
							}
						}
					}

				}
			}else{
				super.removeAutomatic(0);
			}

		}

	}
	
	/**
	 * Equals method for MostRecentlyAdded Playlist 
	 * 
	 * @return true= if the objects are the same/false= otherwise
	 */

	@Override
	public boolean equals(Object other) {
		return (this==other);
	}

}

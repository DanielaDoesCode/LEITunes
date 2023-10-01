package domain.playlists;

import domain.core.MusicLibrary;
import domain.core.Rate;
import domain.core.SongLibraryEvent;
import domain.core.SongRatedLibraryEvent;
import domain.core.SongRemovedLibraryEvent;
import domain.facade.ISong;
import util.adts.ArrayQListWithSelection;
/**
 * @author fc58199 - Daniela Camarinha
 * @author fc58193 -  Rafael Ribeiro
 * 
 * Objects of this type represent playlists that automatically store the most rated songs from a specific library
 * these objects do not allow manual adding or removal of songs
 * 
 */
public class MostLikedSongsPlaylist extends SmartPlaylist{


	private static final int MAX_SONGS = 5; //max songs that are allowed to be in the playlist, max size of the playlist

	private ArrayQListWithSelection<ISong> playlist;



	public MostLikedSongsPlaylist(String name, MusicLibrary library) {
		super(name, library);
		this.playlist = new ArrayQListWithSelection<>();
	}

	public MostLikedSongsPlaylist(MusicLibrary library) {
		super("MostLikedPlaylist", library);
	}


	/**
	 * Reaction to property change events, namely those emitted by the player
	 * (can affect the selected song and song being played)
	 */
	@Override
	public void processEvent(SongLibraryEvent e) {
		int index = 0; //se for evento de remover e de alterar rating.

		if(e instanceof SongRatedLibraryEvent) {
			if(super.playlist.size() <= MAX_SONGS) {
				super.addAutomatic(e.getSong());
			}else{

				//finding is there is an Unrated song in the playlist and storing its index
				int indexUnrated = -1;
				for(int i = 0; (i < super.playlist.size()); i++) {
					if(this.playlist.get(i).getRating()==Rate.UNRATED) {
						indexUnrated = i;
						break;
					}
				}
				if(indexUnrated != -1) { //when there are rated songs in the playlist
					int songSelected = this.playlist.getIndexSelected();
					this.playlist.select(indexUnrated);
					this.playlist.remove();
					this.playlist.select(songSelected);
					super.addAutomatic(e.getSong());
				}else{ //when there's no unrated songs in the playlist
					int songSelected = this.playlist.getIndexSelected();
					this.playlist.select(0);
					this.playlist.remove();
					this.playlist.select(songSelected);
					super.addAutomatic(e.getSong());
				}

			}

		}else if(e instanceof SongRemovedLibraryEvent) { //SongRemovedLibraryEvent
			if(this.playlist != null) {
				for(int i = 0; i < super.playlist.size(); i++) {
					if(e.getSong().equals(super.playlist.get(index))) {
						index = i;
					}
				}
				if(super.playlist.getSelected().equals(e.getSong())) {
					super.removeAutomatic(this.playlist.getIndexSelected());
				}else{
					int selectedPrior = super.playlist.getIndexSelected();
					super.playlist.select(index);
					super.removeAutomatic(index);
					super.playlist.select(selectedPrior);

				}

			}
		}

	}
	
	/**
	 * Equals method for MostLikedSongs Playlist 
	 * 
	 * @return true= if the objects are the same/false= otherwise
	 */
	@Override
	public boolean equals(Object other) {
		return (this==other);
	}
}

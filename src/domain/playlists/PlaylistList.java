package domain.playlists;

import java.util.ArrayList;

import domain.core.MusicLibrary;
import util.adts.AbsQListWithSelection;
import util.adts.QListWithSelection;

/**
 * @uthor Daniela Camarinha fc58199
 * @author Rafael Ribeiro fc58193 
 * 
 * Objects of this type represent a list of playlists from a specific MusicLibrary.
 * At any point there can be a selected playlist.
 * 
 */
public class PlaylistList extends AbsQListWithSelection<Playlist> implements QListWithSelection<Playlist> {

	Playlist selectedPlaylist;
	MusicLibrary library;

	/**
	 * create list method that returns an ArrayList of playlists
	 * 
	 */
	@Override
	protected ArrayList<Playlist> createList() {
		return new ArrayList<>();
	}


	public PlaylistList (MusicLibrary library) {
		super();
		this.library = library;
		this.add(new MostLikedSongsPlaylist(library));
		this.add(new MostRecentlyAdded("Most Recently Added",library));

	}

	/**
	 * forwards the playing of a song to the playlist
	 * 
	 */
	public void play() {
		super.getSelected().play();
	}


	/**
	 * checks if a song is playing the selected playlist, forwarding
	 * it to the playlist
	 * @return true = if song is playing from selected/ false = otherwise
	 * 
	 */
	public boolean isPlaying() {
		return this.selectedPlaylist.isPlaying();
	}


	/**
	 * forwards the stopping of a song to the playlist
	 * 
	 */
	public void stop() {
		super.getSelected().stop();
	}


	/**
	 * Adds a playlist at the end of the list
	 * and registers it as a listener
	 * 	
	 * @param e the playlist to be added
	 */
	@Override
	public void add(Playlist e) {
		super.add(e);
		this.library.registerListener(e);

	}

	/**
	 * unregisters the selected playlist as a listener and removes it
	 * 
	 */
	@Override
	public void remove() {
		this.library.unregisterListener(selectedPlaylist);
		super.remove();

	}


	/**
	 * toString() method for PlaylistList
	 * 
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Playlist l: super.list) {
			sb.append(l.toString());
		}
		return sb.toString();
	}




}

package domain.facade;

import java.util.Iterator;

import domain.core.MusicLibrary;
import domain.playlists.ManualPlaylist;
import domain.playlists.Playlist;
import domain.playlists.PlaylistList;

/**
 * @author fc58199 - Daniela Camarinha
 * @author fc58193 -  Rafael Ribeiro
 * 
 * Objects of this type represent controller for the interactions of PlaylistList objects
 * 
 */
public class PlaylistListController {

	protected PlaylistList playlists;
	protected MusicLibrary library;

	public PlaylistListController(PlaylistList playlists, MusicLibrary library) {
		this.playlists = playlists;
		this.library = library;
	}
	
	/**
	 * creates a Manual playlist and adds it to the library,
	 * that playlist becomes the selected playlist.
	 * @param name = name for the new ManualPlaylist
	 * 
	 */
	public void createPlaylist(String name) {
		ManualPlaylist playlist = new ManualPlaylist(name, library);
		this.playlists.add(playlist);
		}

	/**
	 * selects a playlist in the list of playlists
	 * if i>= 0 && i < size(), otherwise does nothing
	 * @param i = index of the song to be selected
	 */
	public void selectPlaylist(int i) {
		this.playlists.select(i);
	}
	
	/**
	 * Verifies if there is a selected playlist on the list
	 * @return true = if there's a selected playlist/ false = otherwise
	 */
	public boolean somePlaylistSelected() {
		return this.playlists.someSelected();
	}
	
	/**
	 * returns the selected playlist on the list
	 * @requires this.somePlaylistSelected() = true
	 * 
	 */
	public Playlist getSelectedPlaylist() {
		return this.playlists.getSelected();
	}
	
	/**
	 * removes the selected playlist from the list if existent, otherwise does nothing
	 * 
	 */
	public void removePlaylist() {
		this.playlists.remove();
	}
	
	/**
	 * returns an iterator with the playlist on the controller list
	 * @return this.playlist.iterator() = iterator for the playlists
	 */
	public Iterator<Playlist> iterator(){
		return this.playlists.iterator();
	}
	
	/**
	 * returns the number of songs in the playlist selected
	 * @requires this.somePlaylistSelected() = true
	 * 
	 */
	public int numberOfSongs() {
		return this.playlists.getSelected().size();
	}
	
	/**
	 * adds the selected song on the library to the selected playlist on the controller
	 * @requires this.somePlaylistSelected() = true
     * 
	 */
	public void addSong() {
		ISong song = this.library.getSelected();
		this.playlists.getSelected().add(song);
	}
	
	/**
	 * if i>= 0 && i < getSelectedPlaylist().numberOfSongs(), selects the song on the i position
	 * of the selected playlist
	 * @requires this.somePlaylistSelected() = true
	 * 
	 */
	public void selectSong(int i) {
		if(i>= 0 && i < this.numberOfSongs())
			this.playlists.getSelected().select(i);
	}
	
	/**
	 * verifies if this.someSongSelected() =  true, if so also verifies if 
	 * there a song selected in the selected playlist
	 * @return true = if the conditions meet/false = otherwise
	 * 
	 */
	public boolean someSongSelected() {
		if(this.someSongSelected())
			return this.getSelectedPlaylist().someSelected();
	return false;
	}
	
	/**
	 * removes the selected song in the selected library
	 * @requires this.someSongSelected() = true
	 * 
	 */
	public void removeSelectedSong() {
		this.getSelectedPlaylist().remove();
	}
	
	/**
	 * forwards to the selected playlist, the selection of the next song after the prior selected song
	 * @requires this.somePlaylistSelected() = true
	 * 
	 */
	public void nextSong() {
		this.getSelectedPlaylist().next();
	}
	
	/**
	 * forwards to the selected playlist the selection of the previous song before the prior selected song
	 * @requires this.somePlaylistSelected() = true
	 * 
	 */
	public void previousSong() {
		this.getSelectedPlaylist().previous();
	}
	

	/**
	 * if this.someSongSelected() = true, stops the current song and plays the selected song
	 * 
	 */
	public void play() {
		if(this.playlists.getSelected().isPlaying()) {
			this.playlists.getSelected().stop();
		}
		this.playlists.getSelected().play();
	}
	
	/**
	 * stops the song if it was put to play from a specific playlist, otherwise does nothing
	 * 
	 */
	public void stop() {
		if(this.somePlaylistSelected()) {
			this.playlists.getSelected().stop();
		}
	}
	
	/**
	 * toString method for PlaylistListController
	 * 
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("*****PLAYLISTS*****\n");
		return sb.append(this.playlists.toString()).toString();
	}
	
	

}

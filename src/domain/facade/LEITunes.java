package domain.facade;

import domain.core.MusicLibrary;
import domain.playlists.PlaylistList;

public class LEITunes {

	private MusicLibrary library;
	private PlaylistList playlistList;
	private PlaylistListController playlistController;
	private MusicLibraryController libraryController;


	public LEITunes() {
		this.library = new MusicLibrary();
		this.playlistList = new PlaylistList(this.library);
		this.playlistController = new PlaylistListController(this.playlistList, this.library);
		this.libraryController = new MusicLibraryController(this.library);

	}

	/**
	 * getter method that returns the playlistListController for this LEITunes application
	 * @return this.playlistListController = controller of playlistLists
	 */
	public PlaylistListController getPlaylistController() {
		return this.playlistController;
	}

	/**
	 * getter method that returns the MusicLibraryController for this LEITunes application
	 * @return this.playlistListController = controller of MusicLibraries
	 */
	public MusicLibraryController getMusicLibraryController() {
		return this.libraryController;
	}

	/**
	 * getter method that returns the playlistList for this LEITunes application
	 * @return this.playlistList = playlist List
	 */
	public PlaylistList getPlaylistList() {
		return this.playlistList;
	}

	/**
	 * getter method that returns the Music Library for this LEITunes application
	 * @return this.library = Music Library with all the songs that play on the app
	 */
	public MusicLibrary getMusicLibrary() {
		return this.library;
	}

}

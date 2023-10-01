package domain.playlists;

import java.beans.PropertyChangeEvent;
import java.util.Iterator;
import domain.core.MusicLibrary;
import domain.core.Song;
import domain.core.SongLibraryEvent;
import domain.core.SongRemovedLibraryEvent;
import domain.facade.ISong;
import domain.player.Player;
import domain.player.PlayerFactory;
import util.adts.ArrayQListWithSelection;

/**
 * Objects of this abstract class represent general Playlists, providing a general implementation
 * for the methods involved.
 * @author fc58199 - Daniela Camarinha
 * @author fc58193 - Rafael Ribeiro
 *
 */
public abstract class AbsPlaylist implements Playlist {
	
	protected ArrayQListWithSelection<ISong> playlist;
	protected Player player = PlayerFactory.INSTANCE.getPlayer();
	protected String playlistName;
	protected MusicLibrary lib;
	protected ISong playingSong;
	protected boolean isPlaying;

	
	protected AbsPlaylist(String playlistName, MusicLibrary library) {
		this.playlist = new ArrayQListWithSelection<>();
		this.playlistName = playlistName;
		this.isPlaying = false;
		this.playingSong = null;
		this.lib = library;
		this.player.addListener(this);
		
	}
	
	/**
	 * Returns an iterator for the list of songs.
	 * @return The iterator
	 */
	@Override
	public Iterator<ISong> iterator() {
		return playlist.iterator();
	}

	
	/**
	 * Returns the number of songs in the playlist
	 * 
	 * @return the number of songs in the playlist
	 * @ensures \return >= 0
	 */
	@Override
	public int size() {
		return playlist.size();
	}

	/**
	 * Returns the selected song
	 * 
	 * @return the selected song
	 * @requires someSelected()
	 * @ensures \return != null
	 */
	@Override
	public ISong getSelected() {
		return this.playlist.getSelected();
	}

	/**
	 * Returns true if some element is selected
	 * 
	 * @return true if some element is selected, false otherwise
	 */
	@Override
	public boolean someSelected() {	
		return this.playlist.someSelected();
	}

	/**
	 * Adds a song to the end of the playlist, if it
	 * does not exist yet and selects it,
	 * if addition is possible
	 *
	 * @param song the element to be added
	 * @requires song != null 
	 * @return true if the song was added to the playlist, false otherwise
	 * @ensures \result ==> size() == \old(size()) + 1 &&
	 * 						someSelected() && 
	 * 						getIndexSelected() == size() - 1
	 */
	@Override
	public boolean add(ISong song) {
		
	   for(ISong s: playlist) {
		   if(s.equals(song))
			   return false;
	   }
	   this.playlist.add(song);
	   return true;
	}

	/**
	 * Removes the selected element from the playlist, if possible
	 *
	 * @return true if the song was removed, false otherwise
	 * @ensures \return && \old someSelected()  ==> 
	 * 					!someSelected() && size() == \old(size()) - 1
	 * @ensures !\return ==> \old someSelected() == someSelected()
	 * 							&& size() == \old(size()) 
	 */
	@Override
	public boolean remove() {
		if(playlist.someSelected()) {
			this.playlist.remove();
			return true;
		}
		return false;
	}

	/**
	 * Selects song at position i
	 * 
	 * @param i the position denoting the element to be selected
	 * @requires 0 <= i < size()
	 * @ensures someSelected() && getIndexSelected() == i &&
	 * 								size() == \old(size()) 
	 */
	@Override
	public void select(int i) {
		if(i >= 0 && i < this.playlist.size())
		  this.playlist.select(i);

	}

	/**
	 * Moves the current selected song up to position i, 
	 * shifting down all elements in the playlist from 
	 * positions i+1 to \old getIndexSelected()-1, 
	 * if movement in the playlist is possible 
	 * 
	 * @param i the index where this element is going to be moved
	 * @requires someSelected() && 0 <= i < getIndexSelected()
	 * @ensures \return ==> someSelected() && 
	 * 					getIndexSelected() == i  && 
	 * 					size() == \old(size()) 
	 */
	@Override
	public boolean moveUpSelected(int i) {
		
		ISong song = this.playlist.getSelected();
		int newPos = i;
        int oldPos = this.playlist.getIndexSelected();
        ISong[] arr = new ISong[this.playlist.size()];
        
       for(int k = 0; k < this.playlist.size(); k++) {
    	   arr[k] = this.playlist.get(k);//cobrir o array com todos os valores da playlist
       }
        
        // Shift elements to the right between the old and new positions
        if (oldPos != -1 && newPos > oldPos) {
            for (int j = oldPos; j < newPos; j++) {
            	
                arr[j] = arr[j+1];
            }
            
            // Move the number to the new position
            arr[newPos] = song;
        }
        
        for(int z = 0; z < this.playlist.size(); z++) {
        	//falta adicionar os elementos á playlist again
        }
        return true;

		
	}

	/**
	 * Returns the index of the selected element, if any	 
	 * 
	 * @return the index of the selected element, if any
	 * @requires someSelected()
	 * @ensures 0 <= \return < size()
	 */
	@Override
	public int getIndexSelected() {
		return this.playlist.getIndexSelected();
	}

	/**
	 * Selects the next element, if any. Otherwise, no element is selected.
	 *
	 * @requires someSelected() 
	 * @ensures if \old getIndexSelected() < size() - 1
	 *          then getIndexSelected() = \old getIndexSelected() + 1 
	 *          else !someSelected()
	 * @ensures size() == \old(size()) 
	 */
	@Override
	public void next() {
		
		this.playlist.next();
	}

	/**
	 * Selects the previous element, if any. Otherwise, no element is selected.
	 *
	 * @requires someSelected() 
	 * @ensures if \old getIndexSelected() > 0
	 *          then getIndexSelected() = \old getIndexSelected() - 1 
	 *          else !someSelected() 
	 * @ensures size() == \old(size()) 
	 */
	@Override
	public void previous() {
		
		if(this.playlist.getIndexSelected() > 0)
			this.playlist.select(playlist.getIndexSelected() - 1);
	}

	/**
	 * Returns the name of the playlist
	 * 
	 * @return the name of the playlist
	 * @ensures \result != null
	 */
	@Override
	public String getName() {
		return this.playlistName;
	}

	/**
	 * Returns if a song is playing and the play action has been performed via the playlist
	 * 
	 * @return true if a song is playing and the play action was done through the playlist,
	 *         false otherwise
	 */
	@Override
	public boolean isPlaying() {
		return isPlaying;
	}

	/**
	 * Plays the selected song
	 * 
	 * @requires someSelected()
	 * @ensures isPlaying()
	 */
	@Override
	public void play() {
		
		    this.playingSong = this.getSelected();
			this.player.load(this.playingSong.getFilename());
			this.isPlaying = true;
			this.player.play();
		}

	/**
	 * Stops the playing song
	 * 
	 * @requires isPlaying()
	 */
	@Override
	public void stop() {
		
		this.player.stop();
		this.isPlaying = false;
		this.playingSong = null;

	}

	/**
	 * Reaction to property change events, namely those emitted by the player
	 * (can affect the selected song and song being played)
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
		if(evt.getNewValue().equals(Player.PlayingState.ENDED) && this.isPlaying()) {
        	
        	this.playingSong.incTimesPlayed();
        	
        	if(this.someSelected() && this.playingSong.equals(this.getSelected())) {
        		this.next();
        		if(this.someSelected()) {
        			this.play();
        		}else {
        			this.playingSong = null;
        			this.isPlaying = false;
        		}
    
        	}
        		
        }else if(evt.getNewValue().equals(Player.PlayingState.STOPED)){
        	this.stop();
        }

	}

	/**
	 * Reaction to events, namely those emitted by the music library that 
	 * backs up this playlist (can affect the content of the playlist)
	 */
	@Override
	public void processEvent(SongLibraryEvent e) {

		if(e instanceof SongRemovedLibraryEvent) {

			if(this.playlist.someSelected()) {
				if(this.playlist.getSelected().equals(e.getSong()))
				   this.playlist.remove();
			}else{
				int index = 0;
			   for(int i = 0; i < this.playlist.size(); i++){
				 if(e.getSong().equals(this.playlist.get(i))){
					index = i;
					break;
				 }    
			   }
				int selectedPrior = this.playlist.getIndexSelected();
				this.playlist.select(index);
				this.playlist.remove();
				this.playlist.select(selectedPrior);
				
			}
		}

	}
	
	/**
	 * Gets a String representation of the Playlist.
	 * @return The String
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("*-- Playlist " + this.playlistName + "--*\n");
		int i = 0;
		for(ISong s: playlist) {
			sb.append(i + " " + s.toString() + "\n");
			i++;
		}
		return sb.toString();
		
	}
	
}

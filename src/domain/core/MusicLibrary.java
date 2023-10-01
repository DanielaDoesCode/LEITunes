
package domain.core;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import domain.facade.ISong;
import domain.player.Player;
import domain.player.PlayerFactory;
import util.observer.Listener;
import util.observer.Subject;
import util.adts.AbsQListWithSelection;

/**
 * Add songs to the Music Library 
 * 
 * @param slc the music library controller
 */
public class MusicLibrary extends AbsQListWithSelection<Song> implements Subject<SongLibraryEvent>, PropertyChangeListener{


	private Player player = PlayerFactory.INSTANCE.getPlayer();
	protected List<Listener<SongLibraryEvent>> listeners = new ArrayList<>();
	protected Song playingSong;
	protected boolean playing;
	
	
	public MusicLibrary() {
		super();
		this.player.addListener(this);
		this.playing = false;
		this.playingSong = null;
	}
	
	
	/**
	 * Checks the property event emitted and calls the appropriate methods 
	 * 
	 * @param evt = Event that occurred, can be of the values: Ended and Stoped
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {

		if(evt.getNewValue().equals(Player.PlayingState.ENDED) && this.isPlaying()) {

			this.playingSong.incTimesPlayed();

			if(this.someSelected() && this.playingSong.equals(this.getSelected()) && this.isPlaying()) {
				super.next();
				if(this.someSelected()) {
					this.play();
				}else {
					this.playingSong = null;
        			this.playing = false;
				}
		
			}

		}else if(evt.getNewValue().equals(Player.PlayingState.STOPED) && this.isPlaying()){
			this.playing = false;
			this.playingSong = null;
		}
	}
	
	
	/**
	 * Returns the element at index i of the list
	 * 
	 * @param i = index of the wanted element in the list
	 * @return super.list.get(i) = the element on the list in the position i
	 */
	@Override
	public Song get(int i) {
		return super.list.get(i);
	}
	
	
	/**
	 * Creates an iterator object from the list of elements and returns it
	 * 
	 * @return super.list.iterator() = iterator object from list list
	 */
	@Override
	public Iterator<Song> iterator() {
		return super.list.iterator();
	}
	
	
	/**
	 * Emits a given event to the listeners
	 * 
	 * @param e event that occurred
	 */
	@Override
	public void emitEvent(SongLibraryEvent e) { 
		for(Listener<SongLibraryEvent> l: listeners) {
			l.processEvent(e);
		}

	}
	
	
	/**
	 * Registers the given listener
	 * 
	 * @param obs listener to be added 
	 */
	@Override
	public void registerListener(Listener<SongLibraryEvent> obs) {
		this.listeners.add(obs);

	}
	
	
	/**
	 * Removes the registry of the given listener
	 * 
	 * @param obs listener to be removed
	 */
	@Override
	public void unregisterListener(Listener<SongLibraryEvent> obs) {
		this.listeners.remove(obs);

	}
	
	
	/**
	 * Adds an element at the end of the list
	 * and emits a SongLibraryEvent
	 * 	
	 * @param e the element to be added
	 */
	@Override
	public void add(Song e) {
		emitEvent(new SongAddedLibraryEvent(e,this));
		super.add(e);

	}
	
	
	/**
	 * deletes the selected element if someSelected() otherwise does nothing
	 * 
	 */
	@Override
	public void remove() {
			emitEvent(new SongRemovedLibraryEvent(getSelected(), this));
			super.remove();
		

	}
	
	
	/**
	 * Assuming someSelected(), returns it
	 * @return E e = selected element
	 *
	 */
	@Override
	public Song getSelected() {
		return super.get(getIndexSelected());
	}
	/**
	 * determines the interruption of the song that is playing,
	 * and starts playing the other selectedSong 
	 * @requires someSelected() = true;
	 * 
	 */
	public void play() {
		
		if(this.isPlaying()) {
			this.stop();
		}
			this.playingSong = super.getSelected();
			this.player.load(this.playingSong.getFilename());
			this.player.play();
			this.playing = true;

			
	}


	/**
	 * Indicates if there is a song playing from \this 
	 * 
	 * @return true = is a song is playing/ false = otherwise
	 */
	public boolean isPlaying() {
		return this.playing;

	}

	/**
	 * stops the song that is playing
	 * 
	 * @requires isPlaying() = true
	 */
	public void stop() {

		this.player.stop();
		this.playing = false;
		this.playingSong = null;
	}
	
	
	/**
	 * Increments the rating of the song by one,
	 * it stays the same if the song is already at its max
	 * rating 
	 * 
	 */
	public void incRateSelected() {
        if(this.someSelected()) {
		super.getSelected().incRating();
		emitEvent(new SongRatedLibraryEvent(this.getSelected(), this));
        }
	}
	
	
	/**
	 * Decrements the rating of the song by one,
	 * it stays the same if the song is already at its minimum
	 * rating 
	 * 
	 */
	public void decRateSelected() {
        if(this.someSelected()) {
		super.getSelected().decRating();
		emitEvent(new SongRatedLibraryEvent(getSelected(),this));
        }
	}

	/**
	 * Add songs to the Music Library 
	 * 
	 * @param slc the music library controller
	 */
	public Iterable<ISong> getMatches(String reexp){

		ArrayList<ISong> matchables = new ArrayList<>();
		for(Song s: super.list) {
			if(s.matches(reexp))
				matchables.add(s);
		}
		return matchables;
	}

	/**
	 * Returns an iterable structure with the songs in the library
	 * 
	 * @return allSongs = iterable structure withh all of the songs of the library
	 */
	public Iterable<ISong> getSongs(){

		ArrayList<ISong> allSongs = new ArrayList<>();
		for(Song s: super.list) {
			allSongs.add(s);
		}
		return allSongs;
	}
	
	
	/**
	 * Equals method for Music Library 
	 * 
	 * @return true= if the objects are the same/false= otherwise
	 */
	@Override
	public boolean equals(Object other) {
		return this==other;

	}
	
	
	/**
	 * toString() method for the Music Library
	 * 
	 */
	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("*****MUSIC LIBRARY****\n");
		int i = 0;
		for(ISong s: super.list) {
			sb.append(i + " " + s.toString());
			if (i < super.list.size() - 1)
				sb.append("\n");
			i++;
		}
		return sb.toString();
	}



}


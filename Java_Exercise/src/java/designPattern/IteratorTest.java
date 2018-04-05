package designPattern;

import java.util.ArrayList;
import java.util.Iterator;

public class IteratorTest {
	public static void main(String[] args) {
		SongList songlist1 = new SongList();
		SongInfo song;

		// What we need to do is to get the iterator and follow the same pattern to iterator every elements 
		Iterator songlistIterator = songlist1.createIterator();
		while(songlistIterator.hasNext()){
			song = (SongInfo) songlistIterator.next();
			System.out.println(song.songName);
		}
	}
}


class SongInfo{
	String songName; 
	String bandName; 
	int yearReleased;
	
	public SongInfo(String newSongName, String newBandName, int newYearReleased){
		songName = newSongName;
		bandName = newBandName;
		yearReleased = newYearReleased;
	}
	
	public String getSongName(){ return songName; }
	public String getBandName(){ return bandName; }
	public int getYearReleased(){ return yearReleased; }
}

// Unify the way to iterator collection, so we can iterator collection 
interface SongIterator {
	public Iterator createIterator();
}

class SongList implements SongIterator{
	ArrayList<SongInfo> bestSongs;
	
	public SongList() {
		bestSongs = new ArrayList<SongInfo>();
		addSong("Imagine", "John Lennon", 1971);
		addSong("American Pie", "Don McLean", 1971);
		addSong("I Will Survive", "Gloria Gaynor", 1979);
	}
	
	public void addSong(String songName, String bandName, int yearReleased){
		SongInfo songInfo = new SongInfo(songName, bandName, yearReleased);
		bestSongs.add(songInfo);
	}
	
	// Depends on the actual collection, implement it differently
	public Iterator createIterator() {
		return bestSongs.iterator();
	}
}
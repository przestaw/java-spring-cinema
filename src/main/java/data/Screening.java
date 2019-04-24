package data;

import java.util.Date;

public class Screening {
	private final long id;
	private final long movieId;
	private final long roomId;
	private final int hour;
	private final int day;
	private final int month;
	public Screening(long id, long movieId, long roomId) { //TODO: date
		this.id = id;
		this.movieId = movieId;
		this.roomId = roomId;
		this.hour = 0;
		this.day = 0;
		this.month = 0;
		
	}
	
	public long getId() {return id;}
	
	public long getMovieId() {return movieId;}
	
	public long getRoomId() {return roomId;}
	
	public int getHour() {return hour;}
	
	public int getDay() {return day;}
	
	public int getMonth() {return month;}
}

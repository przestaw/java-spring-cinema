package data;

import java.time.LocalDateTime;
import java.time.Month;


public class Screening {
	private final long id;
	private final String movieTitle;
	private final long roomId;
	private final LocalDateTime date;
	public Screening(long id, String movieTitle, long roomId) { //TODO: date
		this.id = id;
		this.movieTitle = movieTitle;
		this.roomId = roomId;
		date = LocalDateTime.of(2019, Month.APRIL, 28, 16, 30);
	}
	
	public long getId() {return id;}
	
	public String getMovieTitle() {return movieTitle;}
	
	public long getRoomId() {return roomId;}

	public LocalDateTime getDate() {return date;}
}

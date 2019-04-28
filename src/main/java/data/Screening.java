package data;

import java.time.LocalDateTime;
import java.time.Month;


public class Screening {
	private final int id;
	private final String movieTitle;
	private final int roomId;
	private final LocalDateTime date;
	
	public Screening(int id, String movieTitle, int roomId, LocalDateTime date) {
		this.id = id;
		this.movieTitle = movieTitle;
		this.roomId = roomId;
		this.date = date;
	}

	public Screening(int id, String movieTitle, int roomId) { //TODO: date
		this.id = id;
		this.movieTitle = movieTitle;
		this.roomId = roomId;
		date = LocalDateTime.of(2019, Month.APRIL, 28, 16, 30);
	}
	
	public int getId() {return id;}
	
	public String getMovieTitle() {return movieTitle;}
	
	public int getRoomId() {return roomId;}

	public LocalDateTime getDate() {return date;}
}

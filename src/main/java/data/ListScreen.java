package data;

import java.time.LocalDateTime;
import java.time.Month;


public class ListScreen extends Screening {
	private final int id;
	
	public ListScreen(int id, String movieTitle, int roomId, LocalDateTime date) {
		super(movieTitle, roomId, date);
		this.id = id;
	}

	public ListScreen(int id, String movieTitle, int roomId) { //TODO: date
		this(id, movieTitle, roomId, LocalDateTime.now());
	}
	
	public int getId() {return id;}
}

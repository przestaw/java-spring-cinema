package data;
/*
 * author : Przemysław Stawczyk
 */
import java.time.LocalDateTime;

public class ListScreen extends Screening {
	private final int id;
	
	public ListScreen(int id, String movieTitle, int roomId, LocalDateTime date) {
		super(movieTitle, roomId, date);
		this.id = id;
	}

	public int getId() {return id;}
}

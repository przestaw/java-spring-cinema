package data;
/*
 * author : Przemysław Stawczyk
 */
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Screening {
	@JsonProperty(value="movie")
	private final String movieTitle;
	@JsonProperty(value="room")
	private final int roomId;
	@JsonProperty(value="time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = DateTimeFormat.ISO.TIME)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	private final LocalDateTime date;

	public Screening(String movieTitle, int roomId, LocalDateTime date) {
		this.movieTitle = movieTitle;
		this.roomId = roomId;
		this.date = date;;
	}

	public String getMovieTitle() {return movieTitle;}

	public int getRoomId() {return roomId;}

	public LocalDateTime getDate() {return date;}

}
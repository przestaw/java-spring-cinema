package data;
/*
 * author : Przemysław Stawczyk
 */
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ListRes{

	@JsonProperty(value="name")
	private final String name;
	@JsonProperty(value="surname")
	private final String surname;
	@JsonProperty(value="movie")
	private final String movieTitle;
	@JsonProperty(value="room")
	private final int roomId;
	@JsonProperty(value="time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = DateTimeFormat.ISO.TIME)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	private final LocalDateTime date;
	@JsonProperty(value="cost")
	private final double totalCost;
	@JsonProperty(value="reserved places")
	private final List<ResPlace> places;
	
	public ListRes(String name, String surname, List<ResPlace> places, String movieTitle, int roomId,
			LocalDateTime date, double totalCost) {
		this.name = name;
		this.surname = surname;
		this.places = places;
		this.movieTitle = movieTitle;
		this.roomId = roomId;
		this.date = date;
		this.totalCost = totalCost;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public int getRoomId() {
		return roomId;
	}

	public LocalDateTime getDate() {
		return date;
	}
	
	public double getTotalCost() {
		return totalCost;
	}
	
	public List<ResPlace> getPlaces() {
		return places;
	}
}

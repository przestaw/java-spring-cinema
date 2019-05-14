package data;
/*
 * author : Przemysław Stawczyk
 */
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "movie")
public class Movie {
	@JsonProperty(value="id")
	private final int id;
	@JsonProperty(value="title")
	private final String title;
	
	public Movie(int id, String title) {
		this.id = id;
		this.title = title;
	}
	
	public int getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
}

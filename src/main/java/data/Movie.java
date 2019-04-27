package data;

public class Movie {
	
	private final long id;
	private final String title;
	
	public Movie(long id, String title) {
		this.id = id;
		this.title = title;
	}
	
	public long getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
}

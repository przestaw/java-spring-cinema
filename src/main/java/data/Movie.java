package data;

public class Movie {
	
	private final int id;
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

package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.DatabaseConnection;
import data.Movie;
import data.Place;
import data.Screening;
import data.TimePeriod;

public class DataController {
	DatabaseConnection databaseConn = new DatabaseConnection();
	
	private final String invalidInput = "Invalid input, pleas try again\n";
	private final String invalidName = "Invalid name/surname, name and surname should be at least 3 characters long and start with capital letter\n";
	private final String invalidDate = "Invalid date/time, please provide date and time in\n [YEAR]-[MONTH]-[DAY]T[HOUR]:[MINUTES]:[SECONDS]\n format by numbers only\n";
	private final String invalidPlace = "Invalid places, there should not be 1 place left between eserved seats\n";
	private final String invalidAlreadyReserved = "Invalid places, places already reserved\n";
	private final String invalidNoPlace = "Invalid place, you cannot reserve no seats\n";
	//private final String invalid
	
	private final String sucessReservation = "Reservation sucessfuly registered";
	
	public Screening getScreening(long screeningId) {
		//TODO: SQL logic
		return new Screening(screeningId, "Batman", 6);
	}
	
	public String addReservation(String name, String surname, long screeningId, Place[] places) {
		if(name.length() < 3 || Character.isUpperCase(name.charAt(0)) ||
				surname.length() < 3 || Character.isUpperCase(surname.charAt(0)))
			return invalidName;
		if(places.length < 1)
			return invalidNoPlace;
		//TODO: SQL logic
		return sucessReservation;
	}
	
	public List<Screening> showScreenings(){
		//TODO: SQL logic
		ArrayList<Screening> ret = new ArrayList<Screening>();
    	ret.add(new Screening(1, "Batman", 9));
    	ret.add(new Screening(2, "Batman Returns", 7));
    	return ret;
	}
	
	public List<Screening> showScreenings(TimePeriod time){
		//TODO: SQL logic
		return this.showScreenings();
	}
	
	public Movie getMovie(long movieId) {
		//TODO: SQL logic
		return new Movie(movieId,"The Dark Knight");
	}
	
	public List<Movie> getMovies() {
    	ArrayList<Movie> ret = new ArrayList<Movie>();
    	try {
    		ret.addAll(databaseConn.getMovies());
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	//ret.add(new Movie(19,"test-sample"));
        //ret.add(new Movie(1,"Batman"));
        //ret.add(new Movie(2,"Batman Returns"));
        return ret;
    }
}

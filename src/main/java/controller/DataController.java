package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.DatabaseConnection;
import data.Movie;
import data.SeatPlace;
import data.TicketPrice;
import data.Place;
import data.Reservation;
import data.Screening;
import data.TimePeriod;

public class DataController {
	
	DatabaseConnection databaseConn = new DatabaseConnection();
	//private final String invalid
	private final String invalidInput = "Invalid input, pleas try again\n";
	private final String invalidName = "Invalid name/surname, name and surname should be at least 3 characters long and start with capital letter\n";
	private final String invalidDate = "Invalid date/time, please provide date and time in\n [YEAR]-[MONTH]-[DAY]T[HOUR]:[MINUTES]:[SECONDS]\n format by numbers only\n";
	private final String invalidPlace = "Invalid places, there should not be 1 place left between eserved seats\n";
	private final String invalidAlreadyReserved = "Invalid places, places already reserved\n";
	private final String invalidNoPlace = "Invalid place, you cannot reserve no seats\n";
	
	//private final String success
	private final String successReservation = "Reservation sucessfuly registered";
	
	public Screening getScreening(int screeningId) {
		Screening ret = new Screening(-1, "not a screening", -1);
		try {
    		ret = databaseConn.getScreening(screeningId);
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
		return ret;
	}
	
	public String addReservation(Reservation newReservation) {
		if(newReservation.getName().length() < 3 || 
				Character.isUpperCase(newReservation.getName().charAt(1)) ||
				newReservation.getSurname().length() < 3 || 
				Character.isUpperCase(newReservation.getSurname().charAt(1)))
			return invalidName + " " + newReservation.getName() ;
		if(newReservation.getPlaces().isEmpty())
			return invalidNoPlace;
		//TODO: No free Seats between
		
		databaseConn.addReservation();//TODO: SQL logic
		
		return successReservation;
	}
	
	public List<Screening> getScreenings(){
		ArrayList<Screening> ret = new ArrayList<Screening>();
    	try {
    		ret.addAll(databaseConn.getScreenings());
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return ret;
	}
	
	public List<Screening> getScreenings(TimePeriod time){
		ArrayList<Screening> ret = new ArrayList<Screening>();
    	try {
    		ret.addAll(databaseConn.getScreenings(time));
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
		return this.getScreenings();
	}
	
	public Movie getMovie(int movieId) {
		Movie ret = new Movie(-1, "not a movie");
		try {
    		ret = databaseConn.getMovie(movieId);
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
		return ret;
	}
	
	public List<SeatPlace> getEmptyPlaces(int screeningId){//TODO : Rewrite 
		ArrayList<SeatPlace> temp = new ArrayList<SeatPlace>();
		ArrayList<SeatPlace> ret = new ArrayList<SeatPlace>();
		try {
    		temp.addAll(databaseConn.getOccupiedPlaces(screeningId));
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
		for(int i = 1; i < 7; ++i) {
    		for(int j = 1; j < 7; ++j) {
    			ret.add(new SeatPlace(i, j));
    		}
    	}
		ret.removeAll(temp);//- it's not working
    	return ret;
	}
	
	public List<TicketPrice> getTicketTypes(){
		ArrayList<TicketPrice> ret = new ArrayList<TicketPrice>();
    	try {
    		ret.addAll(databaseConn.getPrices());
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
        return ret;
	}
	
	public List<Movie> getMovies() {
    	ArrayList<Movie> ret = new ArrayList<Movie>();
    	try {
    		ret.addAll(databaseConn.getMovies());
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
        return ret;
    }
}

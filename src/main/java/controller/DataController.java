package controller;
/*
 * author : Przemysław Stawczyk
 */
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import java.time.LocalDateTime;

import data.DatabaseConnection;
import data.ListRes;
import data.Movie;
import data.ResPlace;
import data.SeatPlace;
import data.TicketPrice;
import data.Reservation;
import data.Screening;
import data.ListScreen;
import data.TimePeriod;

public class DataController {

	DatabaseConnection databaseConn = new DatabaseConnection();
	// private final String invalid
	private final String invalidInput = "Invalid input, pleas try again\n";
	private final String invalidName = "Invalid name/surname, name and surname should be at least 3 characters long and start with capital letter\n";
	private final String invalidDate = "Invalid date/time, please provide date and time in\n [YEAR]-[MONTH]-[DAY]T[HOUR]:[MINUTES]:[SECONDS]\n format by numbers only\n";
	private final String invalidOnePlaceGap = "Invalid places, there should not be 1 place left between eserved seats\n";
	private final String invalidAlreadyReserved = "Invalid places, places already reserved\n";
	private final String invalidNoPlace = "Invalid place, you cannot reserve no seats\n";
	private final String invalidPlace = "Invalid place, there is no such seat\n";
	private final String invalidTicket = "Invalit ticket type, please check [site]/tickets for information about ticket types";
	// private final String success
	private final String successReservation = "Reservation sucessfuly registered, total cost : ";

	private final String errorDatabase = "Could not register reservation for unknown reason, we are sorry for inconvinience";
	
	public String addReservation(Reservation newReservation) {
		double total = 0.0;
		int newResId=0;
		if (newReservation.getName().length() < 3 || Character.isUpperCase(newReservation.getName().charAt(1))
				|| newReservation.getSurname().length() < 3
				|| Character.isUpperCase(newReservation.getSurname().charAt(1)))
			return invalidName + " " + newReservation.getName();
		//check if reservation is not empty
		if (newReservation.getPlaces().isEmpty())
			return invalidNoPlace;
		//check for already occupied places and invalid places
		ArrayList<SeatPlace> occupied = new ArrayList<SeatPlace>();
		occupied.addAll(this.getOccupiedPlaces(newReservation.getScreeningId()));
		for(ResPlace newPlace : newReservation.getPlaces()) {
			for(SeatPlace occPlace : occupied) {
				//if()
				
				if(newPlace.getRow() != occPlace.getRow())
					continue;
				if(newPlace.getColumn() == occPlace.getColumn())
					return invalidAlreadyReserved;
			}
		}
		//check for one place left
			//TODO----+  + - + - + - + - + - + - + - *
			//TODO----+  + - + - + - + - + - + - + - *
		//calculate price
		HashMap<Integer, Double> prices = (HashMap<Integer, Double>)this.getMapPrices();
		try {
			for(ResPlace it : newReservation.getPlaces()) {
				total = total + prices.get(it.getType());
			}
		}catch(NullPointerException e) {
			return invalidTicket;
		}
		//add reservation to database
		newResId = databaseConn.addReservation(newReservation, total);
		if(newResId > 0) {
			return successReservation + total;
		}else {
			return errorDatabase;
		}
	}
	
	private List<SeatPlace> getOccupiedPlaces(int screeningId) {
		ArrayList<SeatPlace> ret = new ArrayList<SeatPlace>();
		ret.addAll(databaseConn.getOccupiedPlaces(screeningId));
		return ret;
	}
	
	private Map<Integer, Double> getMapPrices(){
		ArrayList<TicketPrice> tickets = (ArrayList<TicketPrice>) this.getPrices();
		HashMap<Integer, Double> ret = new HashMap<Integer, Double>();
		for(TicketPrice it : tickets) {
			ret.put(it.getId(), it.getPrice());
		}
		return ret;
	}
	
	public List<ListScreen> getScreenings() {
		ArrayList<ListScreen> ret = new ArrayList<ListScreen>();
		ret.addAll(databaseConn.getScreenings());
		return ret;
	}

	public List<ListScreen> getScreenings(TimePeriod time) {
		ArrayList<ListScreen> ret = new ArrayList<ListScreen>();
		ret.addAll(databaseConn.getScreenings(time));
		return ret;
	}
	
	public List<ListScreen> getScreenings(LocalDateTime beginTime, LocalDateTime endTime) {
		ArrayList<ListScreen> ret = new ArrayList<ListScreen>();
		ret.addAll(databaseConn.getScreenings(beginTime, endTime));
		return ret;
	}
	
	public Screening getScreening(int screeningId) {
		Screening ret = databaseConn.getScreening(screeningId);
		return ret;
	}

	public Object addScreening(Screening screening) {
		Screening ret = databaseConn.addScreening(screening);
		return ret;
	}
	
	public List<SeatPlace> getPlaces(int screeningId) {
		ArrayList<SeatPlace> ret = new ArrayList<SeatPlace>();
		ret.addAll(databaseConn.getPlaces(screeningId));
		return ret;
	}
	
	public List<TicketPrice> getTicketTypes() {
		ArrayList<TicketPrice> ret = new ArrayList<TicketPrice>();
		ret.addAll(databaseConn.getPrices());
		return ret;
	}
	
	public Movie getMovie(int movieId) {
		Movie ret =  databaseConn.getMovie(movieId);
		return ret;
	}


	public List<Movie> getMovies() {
		ArrayList<Movie> ret = new ArrayList<Movie>();
		ret.addAll(databaseConn.getMovies());
		return ret;
	}

	public List<TicketPrice> getPrices() {
		ArrayList<TicketPrice> ret = new ArrayList<TicketPrice>();
		ret.addAll(databaseConn.getPrices());
		return ret;
	}

	public ListRes getReservation(int reservationId) {
		return databaseConn.getReservation(reservationId);
	}
}

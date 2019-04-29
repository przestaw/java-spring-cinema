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
import data.ListScreen;
import data.TimePeriod;

public class DataController {

	DatabaseConnection databaseConn = new DatabaseConnection();
	// private final String invalid
	private final String invalidInput = "Invalid input, pleas try again\n";
	private final String invalidName = "Invalid name/surname, name and surname should be at least 3 characters long and start with capital letter\n";
	private final String invalidDate = "Invalid date/time, please provide date and time in\n [YEAR]-[MONTH]-[DAY]T[HOUR]:[MINUTES]:[SECONDS]\n format by numbers only\n";
	private final String invalidPlace = "Invalid places, there should not be 1 place left between eserved seats\n";
	private final String invalidAlreadyReserved = "Invalid places, places already reserved\n";
	private final String invalidNoPlace = "Invalid place, you cannot reserve no seats\n";

	// private final String success
	private final String successReservation = "Reservation sucessfuly registered";

	public String addReservation(Reservation newReservation) {
		double total = 0.0;
		if (newReservation.getName().length() < 3 || Character.isUpperCase(newReservation.getName().charAt(1))
				|| newReservation.getSurname().length() < 3
				|| Character.isUpperCase(newReservation.getSurname().charAt(1)))
			return invalidName + " " + newReservation.getName();
		if (newReservation.getPlaces().isEmpty())
			return invalidNoPlace;
		// TODO: No free Seats between

		//databaseConn.addReservation(newReservation, total);// TODO: TOTAL COST

		return successReservation + " " + total;
	}

	public List<ListScreen> getScreenings() {
		ArrayList<ListScreen> ret = new ArrayList<ListScreen>();
		ret.addAll(databaseConn.getScreenings());
		return ret;
	}

	public List<ListScreen> getScreenings(TimePeriod time) {
		ArrayList<ListScreen> ret = new ArrayList<ListScreen>();
		ret.addAll(databaseConn.getScreenings(time));
		return this.getScreenings();
	}

	public Screening getScreening(int screeningId) {
		Screening ret = databaseConn.getScreening(screeningId);
		return ret;
	}

	public Object addScreening(Screening screening) {
		Screening ret = databaseConn.addScreening(screening);
		return ret;
	}
	
	public List<SeatPlace> getPlaces(int screeningId) {// TODO : Rewrite
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
}

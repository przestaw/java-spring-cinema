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

public class DataController {
	
	//connection to database with SQL logic
	private DatabaseConnection databaseConn = new DatabaseConnection();
	// private final String invalid reservation
	private final String invalidScreening = "Invalid screeninig, there is no such screeninig\n";
	private final String invalidName = "Invalid name/surname, name and surname should be at least 3 characters long and start with capital letter, surname can consists of two parts separated by single '-'\n";
	private final String invalidDate = "Invalid time, you can only make a reservation at least 15 minutes before the screeninig\n";
	private final String invalidOnePlaceGap = "Invalid places, there should not be 1 place left between eserved seats\n";
	private final String invalidAlreadyReserved = "Invalid places, places already reserved\n";
	private final String invalidNoPlace = "Invalid place, you cannot reserve no seats\n";
	private final String invalidPlace = "Invalid place, there is no such seat\n";
	private final String invalidTicket = "Invalit ticket type, please check [site]/tickets for information about ticket types\n";
	// private final String other
	private final String successReservation = "Reservation sucessfuly registered, total cost : ";
	private final String successReservationId = " your reservation Id id : ";
	private final String successReservationValid = " your reservation is valid to : ";
	private final String errorDatabase = "Could not register reservation for unknown reason, we are sorry for inconvinience";
	
	//*Temp value - I assumed that screening room consist 6 rows and 6 columns*// 
		private final int maxPlace= 6;
	
	public String addReservation(Reservation newReservation) {
		double total = 0.0;
		int newResId=0;
		if (!this.checkNameAndSurname(newReservation.getName(), newReservation.getSurname()))
			return invalidName;// + " " + newReservation.getName() + " " + newReservation.getSurname() + "\n";
		//check screeninigId
		if(this.getScreening(newReservation.getScreeningId()).getId() == -1) {
			return invalidScreening;
		}
		//check reservation date
		if(databaseConn.getScreening(newReservation.getScreeningId()).getDate().plusMinutes(15).isBefore(LocalDateTime.now())) {
			return invalidDate;
		}
		//check if reservation is not empty
		if (newReservation.getPlaces().isEmpty())
			return invalidNoPlace;
		//check for already occupied places and invalid places
		ArrayList<SeatPlace> occupiedPlaces = new ArrayList<SeatPlace>();
		occupiedPlaces.addAll(this.getOccupiedPlaces(newReservation.getScreeningId()));
		ArrayList<ResPlace> reservationPlaces = new ArrayList<ResPlace>();
		reservationPlaces.addAll(newReservation.getPlaces());
		
		for(ResPlace newPlace : reservationPlaces) {
			if(newPlace.getRow() < 0 || newPlace.getRow() > maxPlace 
					||  newPlace.getColumn() < 0 || newPlace.getColumn() > maxPlace) {
				return invalidPlace;
			}
			for(SeatPlace occPlace : occupiedPlaces) {
				if(newPlace.getRow() != occPlace.getRow())
					continue;
				if(newPlace.getColumn() == occPlace.getColumn())
					return invalidAlreadyReserved;
			}
		}
		//check for one place left
			//TODO----+  + - + - + - + - + - + - + - *
		if(this.checkForOneSeatGap(reservationPlaces, occupiedPlaces))
			return invalidOnePlaceGap;
			//TODO----+  + - + - + - + - + - + - + - *
		//calculate price
		HashMap<Integer, Double> prices = (HashMap<Integer, Double>)this.getMapPrices();
		try {
			for(ResPlace it : reservationPlaces) {
				total = total + prices.get(it.getType());
			}
		}catch(NullPointerException e) {
			return invalidTicket;
		}
		//add reservation to database
		newResId = databaseConn.addReservation(newReservation, total);
		if(newResId > 0) {
			return successReservation + total + successReservationId + newResId + 
					successReservationValid + databaseConn.getScreening(newReservation.getScreeningId()).getDate().minusMinutes(15);
					
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
	
	private boolean checkNameAndSurname(String name, String surname) {
		//check name
		if (name.length() < 3 || Character.isLowerCase(name.charAt(0))) {
			return false;
		}
		//check surname
		String[] surnames = surname.split("-");
		if(surnames.length > 2) {
		return false;	
		}else if (surnames.length == 2){
			//check the second part of surname
			if(surnames[1].length() < 3 || Character.isLowerCase(surnames[1].charAt(0))) {
				return false;
			}
		}
		//check the first part [even if second does not exist]
		if(surnames[0].length() < 3 || Character.isLowerCase(surnames[0].charAt(0))) {
			return false;
		}
		return true;
	}
	
	private boolean checkForOneSeatGap(ArrayList<ResPlace> reservationPlaces, ArrayList<SeatPlace> occupiedPlaces) {
		int counter = 0;
		HashMap<Integer,HashMap<Integer,Boolean>> places = new HashMap<Integer,HashMap<Integer,Boolean>>();
		for(int i = 1; i < maxPlace; i++) {
			places.put(i, new HashMap<Integer,Boolean>());
		}
		for(ResPlace newPlace : reservationPlaces) {
			places.get(newPlace.getRow()).put(newPlace.getColumn(), true);
		}
		for(SeatPlace newPlace : occupiedPlaces) {
			places.get(newPlace.getRow()).put(newPlace.getColumn(), true);
		}
		for(int i = 1; i < maxPlace; i++) {
			for(int j = 1; j < maxPlace; j++) {
				if(places.get(i).get(j) == null) {
					counter++;
				}else {
					if(counter == 1 ) {
						return true;
					}else {
						counter = 0;
					}
				}
			}
			counter = 0;
		}
		return false;
	}
	
	public List<ListScreen> getScreenings() {
		ArrayList<ListScreen> ret = new ArrayList<ListScreen>();
		ret.addAll(databaseConn.getScreenings());
		return ret;
	}

	public List<ListScreen> getScreenings(LocalDateTime beginTime, LocalDateTime endTime) {
		ArrayList<ListScreen> ret = new ArrayList<ListScreen>();
		ret.addAll(databaseConn.getScreenings(beginTime, endTime));
		return ret;
	}
	
	public ListScreen getScreening(int screeningId) {
		ListScreen ret = databaseConn.getScreening(screeningId);
		return ret;
	}

	public ListScreen addScreening(Screening screening) {
		ListScreen ret = databaseConn.addScreening(screening);
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
	
	public Movie addMovie(String title) {
		return databaseConn.addMovie(title);
	}
	
	public Movie getMovie(int movieId) {
		return databaseConn.getMovie(movieId);
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
	
	public List<ListRes> getReservations() {
		return databaseConn.getReservations();
	}
	
	public List<ListRes> getReservations(LocalDateTime begin, LocalDateTime end) {
		return databaseConn.getReservations(begin, end);
	}
}

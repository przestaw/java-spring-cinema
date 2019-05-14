package controller;
/*
 * author : Przemysław Stawczyk
 */
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import data.Movie;
import data.SeatPlace;
import data.TicketPrice;
import data.Reservation;
import data.Screening;
import data.ListRes;
import data.ListScreen;

@RestController
public class CinemaController {
	private final DataController data = new DataController();
	
    @RequestMapping("/")
    public String index() {
    	return "Java_Cinema_Ticketing_App\n" +
                "Prepared for the recruitment assignment\n" +
                "by Przemysław Stawczyk\n";
    }
    
    @RequestMapping(value = "/prices", method = RequestMethod.GET)
    public List<TicketPrice> showPrices() {
        return data.getPrices();
    }
    
    @RequestMapping(value = "/movie/all", method = RequestMethod.GET)
    public List<Movie> showMovies() {
        return data.getMovies();
    }
    
    @RequestMapping(value = "/movie/{movieId}", method = RequestMethod.GET)
    public Movie showMovie(@PathVariable int movieId) {
        return data.getMovie(movieId);
    }
    
    @RequestMapping(value = "/screening/all", method = RequestMethod.GET)
    public List<ListScreen> showScreening() {
    	return data.getScreenings();
    }
    
    @RequestMapping(value = "/screening", method = RequestMethod.GET)
    public List<ListScreen> showScreening(@RequestParam(value="begin", required=true) String begin,
    		@RequestParam(value="end", required=true) String end) {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    	LocalDateTime beginTime = LocalDateTime.parse(begin, formatter);
    	LocalDateTime endTime = LocalDateTime.parse(end, formatter);
    	//TimePeriod time = new TimePeriod(beginTime,endTime);
    	return data.getScreenings(beginTime, endTime);
    }
    
    @RequestMapping(value = "/screening/{screeningId}", method = RequestMethod.GET)
    public Screening showScreening(@PathVariable int screeningId) {
        return data.getScreening(screeningId);
    }
    
    @RequestMapping(value = "/screening/{screeningId}/emptyplaces", method = RequestMethod.GET)
    public List<SeatPlace> showEmptyPlaces(@PathVariable int screeningId) {
        return data.getPlaces(screeningId);
    }
   
    @RequestMapping(value = "/reservation/{reservationId}", method = RequestMethod.GET)
    public ListRes showReservation(@PathVariable int reservationId) {
    	return data.getReservation(reservationId);
    }
    
    @RequestMapping(value = "/reservation/all", method = RequestMethod.GET)
    public List<ListRes> showReservation() {
    	return data.getReservations();
    }
    
    @RequestMapping(value = "/reservation", method = RequestMethod.GET)
    public List<ListRes> showReservations(@RequestParam(value="begin", required=false) String begin,
    		@RequestParam(value="end", required=true) String end) {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    	LocalDateTime beginTime;
    	if(begin == null) {
    		beginTime = LocalDateTime.now();
    	}else {
    		beginTime = LocalDateTime.parse(begin, formatter);
    	}
    	LocalDateTime endTime = LocalDateTime.parse(end, formatter);
    	return data.getReservations(beginTime, endTime);
    }
    
    @RequestMapping(value = "/screening/reservation", method = RequestMethod.POST)
    public String makeReservation(@RequestBody Reservation newRes) {
        return data.addReservation(newRes);
    }
    
    @RequestMapping(value = "/movie/new", method = RequestMethod.POST)
    public Movie addMovie(@RequestParam(value="title", required=true) String title) {
    	return data.addMovie(title);
    }
    
    @RequestMapping(value = "/screening/new", method = RequestMethod.POST)
    public Object addScreening(@RequestBody Screening screening) {
    	return data.addScreening(screening);
    }
}
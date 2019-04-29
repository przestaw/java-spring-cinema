package controller;

import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import data.Movie;
import data.ResPlace;
import data.SeatPlace;
import data.Reservation;
import data.Screening;
import data.ListScreen;
import data.TimePeriod;

@RestController
public class CinemaController {
	private final DataController data = new DataController();
	
    @RequestMapping("/")
    public String index() {
    	return "Java_Cinema_Ticketing_App\n" +
                "Prepared for the recruitment assignment\n" +
                "by Przemysław Stawczyk\n";
    }
    
    @RequestMapping(value = "/movie/all", method = RequestMethod.GET)
    public List<Movie> showMovie() {
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
    	TimePeriod time = new TimePeriod(beginTime,endTime);
    	return data.getScreenings(time);
    }
    
    @RequestMapping(value = "/screening/{screeningId}", method = RequestMethod.GET)
    public Screening showScreening(@PathVariable int screeningId) {
        return data.getScreening(screeningId);
    }
    
    @RequestMapping(value = "/screening/{screeningId}/emptyplaces", method = RequestMethod.GET)
    public List<SeatPlace> showEmptyPlaces(@PathVariable int screeningId) {
        return data.getPlaces(screeningId);
    }
   
    //curl -i -X POST -H 'Content-Type: application/json' -d '{"screeningId":1,"name":"Przemysław","surname":"Stawczyk","places":[{"row":1, "column":3, "type":1}, {"row":1,"column":4,"type":3}]}' localhost:8080/screening/1/reservation
    @RequestMapping(value = "/reservation/{reservationId}", method = RequestMethod.GET)
    public Reservation showReservation(@PathVariable int reservationId) {
    	//TODO
    	
    	ArrayList<ResPlace> temp = new ArrayList<ResPlace>();
    	temp.add(new ResPlace(1,3,1));
    	temp.add(new ResPlace(1,4,2));
        return new Reservation(1,"Przemysław", "Stawczyk",temp);
    }
    
    @RequestMapping(value = "/screening/{screeningId}/reservation", method = RequestMethod.POST)
    public String makeReservation(@PathVariable int screeningId, @RequestBody Reservation newRes) {
        return data.addReservation(newRes);
    }
    
    //example : curl -i -X POST -H 'Content-Type: application/json' -d '{"id": "1998", "title": "Przemysław"}' localhost:8080/movie
    @RequestMapping(value = "/movie/new", method = RequestMethod.POST, consumes = "application/json")
    public Movie addMovie(@RequestBody Movie movie) {
    	//TODO
        return new Movie(movie.getId(), movie.getTitle());
    }
    
    //curl -i -X POST -H 'Content-Type: application/json' -d '{"movie": "The Shining", "room": 4, "date" : "2019-05-14T10:30"}' localhost:8080/screening/new
    @RequestMapping(value = "/screening/new", method = RequestMethod.POST)
    public Object addScreening(@RequestBody Screening screening) {
    	return screening;//data.addScreening(screening);
    }
    @RequestMapping(value = "/test")
    public TimePeriod test(@RequestParam(value="time", required=true) String time,@RequestParam(value="flum", required=true) String flum) {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    	LocalDateTime xd = LocalDateTime.parse(time, formatter);
    	LocalDateTime yd = LocalDateTime.parse(flum, formatter);
    	TimePeriod ret = new TimePeriod(xd,yd);
    	return ret;
    }
}
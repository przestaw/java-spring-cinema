package controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import data.Movie;
import data.Screening;
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
    
    @RequestMapping(value = "/movie", method = RequestMethod.GET)
    public List<Movie> showMovie() {
        return data.getMovies();
    }
    
    @RequestMapping(value = "/movie/{movieId}", method = RequestMethod.GET)
    public Movie showMovie(@PathVariable long movieId) {
        return data.getMovie(movieId);
    }
    
    @RequestMapping(value = "/screening", method = RequestMethod.GET)
    public List<Screening> showScreening() {
    	return data.showScreenings();
    }
    
    @RequestMapping(value = "/screening/", method = RequestMethod.GET)
    public List<Screening> showScreening(@RequestBody TimePeriod time) {
    	return data.showScreenings(time);
    }
    
    @RequestMapping(value = "/screening/{screeningId}", method = RequestMethod.GET)
    public Screening showScreening(@PathVariable long screeningId) {
        return data.getScreening(screeningId);
    }
    
    //example : curl -i -X POST -H 'Content-Type: application/json' -d '{"id": "1998", "title": "Przemysław"}' localhost:8080/movie

    @RequestMapping(value = "/movie", method = RequestMethod.POST)
    public Movie addMovie(@RequestBody Movie movie) {
        return new Movie(movie.getId(), movie.getTitle());
    }
    @RequestMapping(value = "/screening", method = RequestMethod.POST)
    public Screening addScreening(@RequestBody Screening screening) {
        return new Screening(screening.getId(), screening.getMovieTitle(), screening.getRoomId());
    }
}
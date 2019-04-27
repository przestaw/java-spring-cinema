package controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;

import data.Movie;
import data.Screening;

@RestController
public class CinemaController {
    @RequestMapping("/")
    public String index() {
    	return "Java_Cinema_Ticketing_App\n" +
                "Prepared for the recruitment assignment\n" +
                "by Przemysław Stawczyk\n";
    }
    
    @RequestMapping(value = "/movie", method = RequestMethod.GET)
    public List<Movie> showMovie() {
    	ArrayList<Movie> ret = new ArrayList<Movie>();
        ret.add(new Movie(19,"test-sample"));
        return ret;
    }
    @RequestMapping(value = "/screening", method = RequestMethod.GET)
    public List<Screening> showScreening() {
    	//TODO : time period
        ArrayList<Screening> ret = new ArrayList<Screening>();
    	ret.add(new Screening(1, 6, 9));
    	return ret;
    }
    
    @RequestMapping(value = "/movie/{movieId}", method = RequestMethod.GET)
    public Movie showMovie(@PathVariable long movieId) {
        return new Movie(movieId,"test-sample");
    }
    @RequestMapping(value = "/screening/{screeningId}", method = RequestMethod.GET)
    public Screening showScreening(@PathVariable long screeningId) {
        return new Screening(screeningId, 6, 6);
    }
    
    //example : curl -i -X POST -H 'Content-Type: application/json' -d '{"id": "1998", "title": "Przemysław"}' localhost:8080/movie

    @RequestMapping(value = "/movie", method = RequestMethod.POST)
    public Movie addMovie(@RequestBody Movie movie) {
        return new Movie(movie.getID(), movie.getTitle());
    }
    @RequestMapping(value = "/screening", method = RequestMethod.POST)
    public Screening addScreening(@RequestBody Screening screening) {
        return new Screening(screening.getId(), screening.getMovieId(), screening.getRoomId());
    }

}
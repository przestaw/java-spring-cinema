package hello;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import data.Movie;
import data.Screening;

@RestController
public class HelloController {
    @RequestMapping("/")
    public String index() {
    	return "Java_Cinema_Ticketing_App\n" +
                "Prepared for the TouK recruitment\n" +
                "by Przemysław Stawczyk\n";
    }
    @RequestMapping(value = "/movie", method = RequestMethod.GET)
    public Movie showMovie(@RequestParam(value="id") long movieId) {
        return new Movie(movieId,"test-sample");
    }
    @RequestMapping(value = "/screening", method = RequestMethod.GET)
    public Screening showScreening(@RequestParam(value="id") long screening_id) {
        return new Screening(screening_id, 6, 6);
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
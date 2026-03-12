package co.istad.piseth.springwithmaven;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
    List<Movie> movies = new ArrayList<>();

    public MovieController() {
        movies.add(new Movie(1, "abc", "action", 2019));
        movies.add(new Movie(2, "def", "comedy", 2018));
        movies.add(new Movie(3, "ghi", "drama", 2017));
        movies.add(new Movie(4, "jkl", "horror", 2016));
    }

    @GetMapping
    public List<Movie> getAllMovies() {
        return movies;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable int id) {
        for (Movie movie : movies) {
            if (movie.getId() == id) {
                return ResponseEntity.ok(movie);
            }
        }
        return null;
    }

    @GetMapping("/search/title")
    public List<Movie> searchMovieByTitle(@RequestParam String title) {
        List<Movie> result = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getTitle().contains(title)) {
                result.add(movie);
            }
        }
        return result;
    }

    @PostMapping
    public Movie addMovie(@RequestBody Movie movie) {
        movies.add(movie);
        return movie;
    }
}

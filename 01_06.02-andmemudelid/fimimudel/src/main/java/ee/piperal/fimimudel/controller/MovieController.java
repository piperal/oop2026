package ee.piperal.fimimudel.controller;

import ee.piperal.fimimudel.entity.Movie;
import ee.piperal.fimimudel.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("movies")
    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    @DeleteMapping("movies/{id}")
    public List<Movie> delMovie(@PathVariable Long id){
        movieRepository.deleteById(id);
        return movieRepository.findAll();
    }

    @PostMapping("movies/add")
    public List<Movie> addMovie(@RequestParam Movie movie){
        movieRepository.save(movie);
        return movieRepository.findAll();
    }

}

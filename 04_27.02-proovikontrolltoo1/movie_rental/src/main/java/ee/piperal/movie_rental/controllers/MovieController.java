package ee.piperal.movie_rental.controllers;



import ee.piperal.movie_rental.entity.Movie;
import ee.piperal.movie_rental.repository.MovieInterface;
import ee.piperal.movie_rental.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class MovieController {

    @Autowired
    MovieInterface movieInterface;

    @Autowired
    MovieService movieService;

    @GetMapping("/all")
    public List<Movie> findAll(){
        List<Movie> movies = movieInterface.findAll();
        List<Movie> newMovies = new ArrayList<>(List.of());
        for(Movie movie : movies){
            if(movie.getMovieRented() == 0){
                newMovies.add(movie);
            }
        }
        return newMovies;
    }

    @PostMapping("/add")
    public void addMovie(@RequestBody Movie movie){
        movieService.MovieValidator(movie);
        String movieType = movie.getMovieType();
        double premium = 4;
        double basic = 3;
        switch(movieType){
            case "regular", "old":
                movie.setMoviePrice(basic);
                break;
            case "new":
                movie.setMoviePrice(premium);
                break;
        }
        movieInterface.save(movie);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMovie(@PathVariable Long id){
        movieInterface.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public void updateType( @PathVariable Long id,String newType,int rented){
        Movie movie = movieInterface.findById(id).orElseThrow();
        movie.setMovieType(newType);
        movie.setMovieRented(rented);
        double premium = 4;
        double basic = 3;
        switch(newType){
            case "basic", "regular", "old":
                movie.setMoviePrice(basic);
                break;
            case "new":
                movie.setMoviePrice(premium);
                break;
        }
        movieInterface.save(movie);
    }
}

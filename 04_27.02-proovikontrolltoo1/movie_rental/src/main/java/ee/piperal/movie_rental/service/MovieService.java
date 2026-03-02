package ee.piperal.movie_rental.service;


import ee.piperal.movie_rental.entity.Movie;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class MovieService {

    public void MovieValidator(Movie movie){
        if(movie.getMovieId() != null){
            throw new RuntimeException("Can't add with id");
        }
        if(movie.getMoviePrice() != 0){
            throw new RuntimeException("Price has to be 0");
        }
        if(Objects.equals(movie.getMovieType(), "")){
            throw new RuntimeException("Need to specify movie type");
        }
        if(movie.getMovieName() == null){
            throw new RuntimeException("Can't add movie with no name");
        }
    }
}

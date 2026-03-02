package ee.piperal.movie_rental.repository;

import ee.piperal.movie_rental.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieInterface extends JpaRepository<Movie,Long> {
}

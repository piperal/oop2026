package ee.piperal.fimimudel.repository;
import ee.piperal.fimimudel.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Long> {
}

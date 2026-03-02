package ee.piperal.movie_rental.repository;

import ee.piperal.movie_rental.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonInterface extends JpaRepository<Person, Long> {
}

package ee.piperal.veebipood.repository;

import ee.piperal.veebipood.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Long> {
    //SELECT email FROM person WHERE...
    Person findByEmail(String email);
}

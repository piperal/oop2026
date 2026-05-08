package ee.piperal.sportlasetulemused.repository;

import ee.piperal.sportlasetulemused.entity.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AthleteRepository extends JpaRepository<Athlete, Long> {
}

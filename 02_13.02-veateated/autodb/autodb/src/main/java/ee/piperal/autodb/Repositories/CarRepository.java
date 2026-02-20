package ee.piperal.autodb.Repositories;

import ee.piperal.autodb.Entitites.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}

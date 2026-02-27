package ee.mihkel.rentalstore.repository;

import ee.mihkel.rentalstore.entity.Rental;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<@NonNull Rental,@NonNull Long> {
}

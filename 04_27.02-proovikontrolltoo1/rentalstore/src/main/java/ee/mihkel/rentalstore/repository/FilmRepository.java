package ee.mihkel.rentalstore.repository;

import ee.mihkel.rentalstore.entity.Film;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<@NonNull Film,@NonNull Long> {
}

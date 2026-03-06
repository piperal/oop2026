package ee.piperal.kontrolltoo.repository;

import ee.piperal.kontrolltoo.entity.ReverseWord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReverseInterface extends JpaRepository<ReverseWord,Long> {
}

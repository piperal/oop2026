package ee.piperal.kontrolltoo.repository;

import ee.piperal.kontrolltoo.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordInterface extends JpaRepository<Word,Long> {
}

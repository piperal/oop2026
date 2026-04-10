package ee.piperal.veebipood.repository;

import ee.piperal.veebipood.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

//JpaRepository - downloads all functions
//CrudRepository - downloads minimal amount of functions

public interface CategoryRepository extends JpaRepository <Category, Long> {
}

package ee.piperal.veebipood.repository;
import ee.piperal.veebipood.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

//repository -> andmebaasiga suhtlemiseks

public interface ProductRepository extends JpaRepository<Product,Long> {
}

package app;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("FROM Product where stock > 0")
    List<Product> findOnlyInStock();

    @Query(value="SELECT * FROM Product ORDER BY stock DESC LIMIT 5", nativeQuery = true)
    List<Product> findByHighestStock();
}

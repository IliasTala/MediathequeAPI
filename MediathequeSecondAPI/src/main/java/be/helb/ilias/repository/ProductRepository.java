package be.helb.ilias.repository;

import be.helb.ilias.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByAvailable(boolean available);
    Optional<Product> findById(Long id);
    List<Product> findByIdAndAvailable(Long id, Boolean available);
}


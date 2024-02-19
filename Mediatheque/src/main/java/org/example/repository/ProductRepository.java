package org.example.repository;

import org.example.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);
    List<Product> findByPriceGreaterThanEqual(Double minPrice);
    List<Product> findByPriceLessThanEqual(Double maxPrice);
}
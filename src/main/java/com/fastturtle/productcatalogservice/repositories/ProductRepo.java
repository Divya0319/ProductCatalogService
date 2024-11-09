package com.fastturtle.productcatalogservice.repositories;

import com.fastturtle.productcatalogservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long> {

    Optional<Product> findById(Long id);

    List<Product> findAll();

    List<Product> findProductsByPriceBetween(Double low, Double high);

    List<Product> findAllByIsPrimeSpecific(Boolean value);
    List<Product> findAllByIsPrimeSpecificTrue();  // alternative to above query(only works for boolean values);

    //List<Product> findAllByStateActive();

    List<Product> findAllByOrderByPriceDesc();

    @Query("SELECT p.name FROM Product p WHERE p.id = ?1")
    String findProductNameFromId(Long id);

    @Query("SELECT c.name FROM Category c JOIN Product p ON p.category.id = c.id WHERE p.id = :productId")
    String findCategoryNameFromProductId(Long productId);

}

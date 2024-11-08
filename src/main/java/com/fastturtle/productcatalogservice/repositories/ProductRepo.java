package com.fastturtle.productcatalogservice.repositories;

import com.fastturtle.productcatalogservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long> {

    Optional<Product> findById(Long id);

    List<Product> findAll();

    List<Product> findProductsByPriceBetween(Double low, Double high);

    List<Product> findAllByIsPrimeSpecific(Boolean value);
    List<Product> findAllByIsPrimeSpecificTrue();  // alternative to above query(only works for boolean values);

    //List<Product> findAllByStateActive();

}

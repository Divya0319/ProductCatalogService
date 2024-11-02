package com.fastturtle.productcatalogservice.repositories;

import com.fastturtle.productcatalogservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long> {

    Optional<Product> findById(Long id);

    List<Product> findAll();

}

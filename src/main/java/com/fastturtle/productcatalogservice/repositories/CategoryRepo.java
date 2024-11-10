package com.fastturtle.productcatalogservice.repositories;

import com.fastturtle.productcatalogservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepo extends JpaRepository<Category, Long> {

    Optional<Category> findById(Long id);
}

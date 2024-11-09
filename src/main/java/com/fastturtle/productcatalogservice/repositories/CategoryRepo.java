package com.fastturtle.productcatalogservice.repositories;

import com.fastturtle.productcatalogservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
}

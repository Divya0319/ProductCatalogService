package com.fastturtle.productcatalogservice.repositories;

import com.fastturtle.productcatalogservice.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepoTest {

    @Autowired
    private ProductRepo productRepo;

    @Test
    @Transactional
    public void testJpaQueries() {
        List<Product> productList = productRepo.findProductsByPriceBetween(200D, 1100D);
    }

}
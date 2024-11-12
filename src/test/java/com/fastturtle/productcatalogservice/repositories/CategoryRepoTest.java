package com.fastturtle.productcatalogservice.repositories;

import com.fastturtle.productcatalogservice.models.Category;
import com.fastturtle.productcatalogservice.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryRepoTest {

    @Autowired
    private CategoryRepo categoryRepo;

    @Test
    @Transactional
    public void testFetchTypes() {
        Optional<Category> optionalCategory = categoryRepo.findById(3L);
        System.out.println(optionalCategory.get().getName());

        for(Product p : optionalCategory.get().getProductList()) {
            System.out.println(p.getName());
        }

    }

    @Test
    @Transactional
    public void testFetchTypesAndFetchModes() {
        Optional<Category> optionalCategory = categoryRepo.findById(2L);
        System.out.println(optionalCategory.get().getName());

        for(Product p : optionalCategory.get().getProductList()) {
            System.out.println(p.getName());
        }
    }

    @Test
    @Transactional
    public void testSubSelect() {
        List<Category> categoryList = categoryRepo.findAll();
        for(Category category : categoryList) {
            List<Product> products = category.getProductList();
            if(!products.isEmpty()) {
                System.out.println(products.get(0).getName());
            }
        }
    }

}
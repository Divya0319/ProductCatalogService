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
class ProductRepoTest {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Test
    @Transactional
    public void testJpaQueries() {
        //List<Product> productList = productRepo.findProductsByPriceBetween(200D, 1100D);
        //List<Product> productList = productRepo.findAllByOrderByPriceDesc();
        //String productName = productRepo.findProductNameFromId(1L);
        //System.out.println(productName);
        String catName = productRepo.findCategoryNameFromProductId(1L);
        System.out.println(catName);
    }

    @Test
    public void insertIntoAWSDb() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Himgange Oil");
        product.setDescription("Thanda tel");
        product.setPrice(180.00);
        Category category = new Category();
        category.setId(5L);
        category.setName("Hair Oils");
        category.setDescription("Hair Oils");
        product.setCategory(category);

        productRepo.save(product);

    }

    @Test
    public void insert10DifferentProductsWithSameTitleIntoAWSDb() {
        Category category = new Category();
        category.setId(6L);
        category.setName("Condoms");
        category.setDescription("Best quality condoms");

        // Array of product descriptions for the remaining 9 products
        String[] descriptions = {
                "Latex", "Non-Latex", "Internal", "Spermicidal", "Glow-in-the-dark",
                "Flavored", "Textured", "Warming", "Pleasure-Shaped", "Colored"
        };

        // Starting ID for the products
        long startingId = 2L;
        // Starting price
        double startingPrice = 30.00;

        for (int i = 0; i < descriptions.length; i++) {
            Product product = new Product();
            product.setId(startingId + i); // Increment ID for each product
            product.setName("Durex Condom");
            product.setImageUrl("https://images.google.com/" + descriptions[i]);
            product.setDescription(descriptions[i]);
            product.setPrice(startingPrice + (i * 20)); // Increment price by 20 for each product

            Optional<Category> existingCategory = categoryRepo.findById(6L);
            if(existingCategory.isPresent()) {
                product.setCategory(existingCategory.get());
            } else {
                product.setCategory(category);
            }

            productRepo.save(product); // Save the product
        }


    }

}
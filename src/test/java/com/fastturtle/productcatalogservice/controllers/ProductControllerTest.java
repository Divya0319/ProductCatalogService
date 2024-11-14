package com.fastturtle.productcatalogservice.controllers;

import com.fastturtle.productcatalogservice.dtos.ProductDTO;
import com.fastturtle.productcatalogservice.models.Product;
import com.fastturtle.productcatalogservice.service.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockBean
    private IProductService productService;

    @Test
    public void test_GetProductById_WithValidId_ReturnsProductSuccessfully() {
        // Arrange
        Long id = 1L;
        Product product = new Product();
        product.setId(id);
        product.setName("Iphone 20");
//        when(productService.getProductById(any(Long.class)))
//                .thenReturn(new Product());
        when(productService.getProductById(id))
                .thenReturn(product);

        //Act
        ResponseEntity<ProductDTO> response = productController.getProduct(id);

        //Assert
        assertNotNull(response.getBody());
        assertEquals(id, response.getBody().getId());
        assertEquals("Iphone 20", response.getBody().getName());
    }

}

package com.fastturtle.productcatalogservice.controllers;

import com.fastturtle.productcatalogservice.dtos.ProductDTO;
import com.fastturtle.productcatalogservice.exceptions.ProductNotFoundException;
import com.fastturtle.productcatalogservice.models.Product;
import com.fastturtle.productcatalogservice.service.IProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockBean
    private IProductService productService;

    @Captor
    ArgumentCaptor<Long> idCaptor;

    @Test
    public void test_GetProductById_WithValidId_ReturnsProductSuccessfully() {
        // Arrange
        Long id = 1L;
        Product product = new Product();
        product.setId(id);
        product.setName("Iphone 20");
//        when(productService.getProductById(any(Long.class)))
//                .thenReturn(product);
        when(productService.getProductById(id))
                .thenReturn(product);

        //Act
        ResponseEntity<ProductDTO> response = productController.getProduct(id);

        //Assert
        assertNotNull(response.getBody());
        assertEquals(id, response.getBody().getId());
        assertEquals("Iphone 20", response.getBody().getName());
    }

    @Test
    @DisplayName("parameter 0 resulted in product not present exception")
    public void test_GetProductById_WithInvalidId_ThrowsException() {
        //Act and Assert
        Exception ex = assertThrows(ProductNotFoundException.class,
                () -> productController.getProduct(0L));
        assertEquals("Product not present", ex.getMessage());
        verify(productService, times(0)).getProductById(0L);
    }

    @Test
    public void test_GetProductById_ProductServiceThrowsException() {
        //Arrange
        when(productService.getProductById(any(Long.class)))
                .thenThrow(new ProductNotFoundException("something went bad"));

        //Act and Assert
        assertThrows(RuntimeException.class,
                () -> productController.getProduct(0L));
    }

    // TODO: add tests for other methods of controller

    @Test
    public void test_GetProductById_CheckIfProductServiceCalledWithExpectedArguments() {
        //Arrange
        Long id = 1L;
        Product product = new Product();
        product.setId(id);
        product.setName("Iphone20");

        when(productService.getProductById(any(Long.class)))
                .thenReturn(product);

        //Act
        productController.getProduct(id);

        //Assert

        // Whenever need to check anything with mock(number of times called, argument passed)
        // we use verify
        verify(productService).getProductById(idCaptor.capture());
        assertEquals(id, idCaptor.getValue());

    }

}

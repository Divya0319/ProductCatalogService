package com.fastturtle.productcatalogservice.controllers;

import com.fastturtle.productcatalogservice.dtos.ProductDTO;
import com.fastturtle.productcatalogservice.models.Product;
import com.fastturtle.productcatalogservice.service.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProductControllerFlow {

    @Autowired
    private ProductController productController;

    @Autowired
    private IProductService productService;

    @Test
    public void test_Create_Replace_GetProduct_RunsSuccessfully() {
        //Arrange
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("iPhone15");
        productDTO.setId(10L);

        //Act
        ProductDTO response = productController.createProduct(productDTO);
//        ResponseEntity<ProductDTO> responseEntity = productController.getProduct(response.getId());
        ProductDTO responseEntity = productController.getProduct(response.getId());
        productDTO.setName("iPhone20");
        ProductDTO response2 = productController.replaceProduct(response.getId(), productDTO);
//        ResponseEntity<ProductDTO> responseEntity2 = productController.getProduct(response2.getId());
        ProductDTO responseEntity2 = productController.getProduct(response2.getId());

        //Assert
        assertEquals("iPhone15", responseEntity.getName());
        assertEquals("iPhone20", responseEntity2.getName());

    }
}

package com.fastturtle.productcatalogservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fastturtle.productcatalogservice.dtos.ProductDTO;
import com.fastturtle.productcatalogservice.models.Product;
import com.fastturtle.productcatalogservice.service.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ProductController.class)
public class ProductControllerMvcTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProductService productService;

    //object <-> json <-> string
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void test_GetAllProducts_RunsSuccessfully() throws Exception {
        // Because our productService is a Mock, so we need to arrange it

        //Arrange
        List<Product> productList = new ArrayList<>();
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Iphone");
        product1.setPrice(100000D);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("MacBook");
        product2.setPrice(200000D);

        productList.add(product1);
        productList.add(product2);

        when(productService.getAllProducts())
                .thenReturn(productList);

        // Act and Assert
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(productList)))
                .andExpect(jsonPath("$[1].name").value("MacBook"))
                .andExpect(jsonPath("$[1].length()").value(3))
                .andExpect(jsonPath("$.length()").value(2));
    }

    // TODO : try mockmvc test for post api

    @Test
    public void test_CreateProduct_RunsSuccessfully() throws Exception {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(5L);
        productDTO.setName("ipad");
        productDTO.setDescription("writing ipad");

        Product product = new Product();
        product.setId(5L);
        product.setName("ipad");
        product.setDescription("writing ipad");

        when(productService.createProduct(any(Product.class))).thenReturn(product);

        mockMvc.perform(post("/products").content(objectMapper.writeValueAsString(productDTO)).contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(productDTO)))
                .andExpect(jsonPath("$.name").value("ipad")) // whole json is referred by '$'
                .andExpect(jsonPath("$.length()").value(3));

//        {
//            "id" : 5,
//            "name" : "ipad",
//            "description": "kidnfknk"
//        }

    }
}

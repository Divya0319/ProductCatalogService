package com.fastturtle.productcatalogservice.controllers;

import com.fastturtle.productcatalogservice.dtos.ProductDTO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return new ArrayList<>();
    }

    @GetMapping("{id}")
    public ProductDTO getProduct(@PathVariable("id") Long productId) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(productId);
        return productDTO;
    }

    @PostMapping
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO) {
        return productDTO;
    }

    @PutMapping("{id}")
    public ProductDTO replaceProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        return productDTO;
    }
}

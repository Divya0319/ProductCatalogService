package com.fastturtle.productcatalogservice.controllers;

import com.fastturtle.productcatalogservice.dtos.CategoryDTO;
import com.fastturtle.productcatalogservice.dtos.ProductDTO;
import com.fastturtle.productcatalogservice.models.Product;
import com.fastturtle.productcatalogservice.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final IProductService productService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return new ArrayList<>();
    }

    @GetMapping("{id}")
    public ProductDTO getProduct(@PathVariable("id") Long productId) {
        Product product = productService.getProductById(productId);
        return from(product);
    }

    @PostMapping
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO) {
        return productDTO;
    }

    @PutMapping("{id}")
    public ProductDTO replaceProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        return productDTO;
    }

    private ProductDTO from(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setImageUrl(product.getImageUrl());
        productDTO.setPrice(product.getPrice());

        if(product.getCategory() != null) {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setName(product.getCategory().getName());
            categoryDTO.setId(product.getCategory().getId());
            categoryDTO.setDescription(product.getCategory().getDescription());
            productDTO.setCategory(categoryDTO);
        }

        return productDTO;
    }
}

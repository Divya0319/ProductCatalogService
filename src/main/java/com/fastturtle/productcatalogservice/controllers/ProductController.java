package com.fastturtle.productcatalogservice.controllers;

import com.fastturtle.productcatalogservice.dtos.CategoryDTO;
import com.fastturtle.productcatalogservice.dtos.ProductDTO;
import com.fastturtle.productcatalogservice.models.Category;
import com.fastturtle.productcatalogservice.models.Product;
import com.fastturtle.productcatalogservice.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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
        List<ProductDTO> productDTOS = new ArrayList<>();
        List<Product> products = productService.getAllProducts();
        for(Product product : products) {
            productDTOS.add(from(product));
        }

        return productDTOS;
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable("id") Long productId) {
        try {
            if(productId < 1) {
                throw new IllegalArgumentException("Product not present");
            }
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add("called by", "smart frontend");
            Product product = productService.getProductById(productId);
            if (product == null) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }

        ProductDTO productDTO = from(product);
        return new ResponseEntity<>(productDTO, headers, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO) {
        return productDTO;
    }

    @PutMapping("{id}")
    public ProductDTO replaceProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        Product product = from(productDTO);
        Product result = productService.replaceProduct(id, product);
        return from(result);
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

    private Product from(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImageUrl(productDTO.getImageUrl());
        if(productDTO.getCategory() != null) {
            Category category = new Category();
            category.setName(productDTO.getCategory().getName());
            product.setCategory(category);
        }

        return product;
    }
}

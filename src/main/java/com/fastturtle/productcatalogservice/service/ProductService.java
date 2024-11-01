package com.fastturtle.productcatalogservice.service;

import com.fastturtle.productcatalogservice.dtos.FakeStoreProductDTO;
import com.fastturtle.productcatalogservice.models.Category;
import com.fastturtle.productcatalogservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProductService implements IProductService {

    private final RestTemplateBuilder restTemplateBuilder;

    @Autowired
    public ProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public Product getProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity = restTemplate.getForEntity("https://fakestoreapi.com/products/{productId}", FakeStoreProductDTO.class, id);
        if(fakeStoreProductDTOResponseEntity.getBody() != null && fakeStoreProductDTOResponseEntity.getStatusCode().equals(HttpStatus.OK)) {
            return from(fakeStoreProductDTOResponseEntity.getBody());
        }
        return null;
    }

    public List<Product> getAllProducts() {
        return null;
    }

    public Product createProduct( Product product) {
        return null;
    }

    private Product from(FakeStoreProductDTO fakeStoreProductDTO) {
        Product product = new Product();
        product.setId(fakeStoreProductDTO.getId());
        product.setName(fakeStoreProductDTO.getTitle());
        product.setDescription(fakeStoreProductDTO.getDescription());
        product.setPrice(fakeStoreProductDTO.getPrice());
        product.setImageUrl(fakeStoreProductDTO.getImage());
        Category category = new Category();
        category.setName(fakeStoreProductDTO.getCategory());
        product.setCategory(category);

        return product;
    }

}

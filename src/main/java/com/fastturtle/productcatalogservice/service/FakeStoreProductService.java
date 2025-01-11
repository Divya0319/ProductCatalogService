package com.fastturtle.productcatalogservice.service;

import com.fastturtle.productcatalogservice.clients.FakeStoreApiClient;
import com.fastturtle.productcatalogservice.dtos.FakeStoreProductDTO;
import com.fastturtle.productcatalogservice.models.Category;
import com.fastturtle.productcatalogservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class FakeStoreProductService implements IProductService {

    private final RestTemplateBuilder restTemplateBuilder;

    private final FakeStoreApiClient fakeStoreApiClient;

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder, FakeStoreApiClient fakeStoreApiClient, RedisTemplate<String, Object> redisTemplate) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreApiClient = fakeStoreApiClient;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Product getProductById(Long id) {
        return from(fakeStoreApiClient.getProductById(id));
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDTO[] fakeStoreProductDTOs = restTemplate.getForEntity("https://fakestoreapi.com/products", FakeStoreProductDTO[].class).getBody();
        for(FakeStoreProductDTO fakeStoreProductDTO : fakeStoreProductDTOs) {
            products.add(from(fakeStoreProductDTO));
        }
        return products;
    }

    @Override
    public Product createProduct(Product product) {
        FakeStoreProductDTO fakeStoreProductDTO = from(product);
        FakeStoreProductDTO fakeStoreProductDTOResponse = fakeStoreApiClient.createProduct(fakeStoreProductDTO);
        return from(fakeStoreProductDTOResponse);
    }

    @Override
    public Product replaceProduct(Long id, Product product) {

        FakeStoreProductDTO fakeStoreProductDTOReq = from(product);
        FakeStoreProductDTO fakeStoreProductDTOResponse = fakeStoreApiClient.replaceProduct(id, fakeStoreProductDTOReq);
        return from(fakeStoreProductDTOResponse);

    }

    @Override
    public Product deleteProduct(Long id) {
        return from(fakeStoreApiClient.deleteProduct(id));
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

    private FakeStoreProductDTO from(Product product) {
        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        fakeStoreProductDTO.setId(product.getId());
        fakeStoreProductDTO.setTitle(product.getName());
        fakeStoreProductDTO.setDescription(product.getDescription());
        fakeStoreProductDTO.setPrice(product.getPrice());
        fakeStoreProductDTO.setImage(product.getImageUrl());
        if(product.getCategory() != null) {
            fakeStoreProductDTO.setCategory(product.getCategory().getName());

        }

        return fakeStoreProductDTO;
    }

}

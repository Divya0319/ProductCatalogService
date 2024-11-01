package com.fastturtle.productcatalogservice.service;

import com.fastturtle.productcatalogservice.dtos.FakeStoreProductDTO;
import com.fastturtle.productcatalogservice.models.Category;
import com.fastturtle.productcatalogservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService {

    private final RestTemplateBuilder restTemplateBuilder;

    @Autowired
    public ProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public Product getProductById(Long id) {
//        RestTemplate restTemplate = restTemplateBuilder.build();
//        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity = restTemplate.getForEntity("https://fakestoreapi.com/products/{productId}", FakeStoreProductDTO.class, id);
//        if(fakeStoreProductDTOResponseEntity.getBody() != null && fakeStoreProductDTOResponseEntity.getStatusCode().equals(HttpStatus.OK)) {
//            return from(fakeStoreProductDTOResponseEntity.getBody());
//        }

        FakeStoreProductDTO fakeStoreProductDTO = requestForEntity(HttpMethod.GET, "https://fakestoreapi.com/products/{productId}", null, FakeStoreProductDTO.class, id).getBody();
        return from(fakeStoreProductDTO);
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
    public Product createProduct( Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {

        FakeStoreProductDTO fakeStoreProductDTOReq = from(product);

        FakeStoreProductDTO fakeStoreProductDTO = requestForEntity(HttpMethod.PUT, "https://fakestoreapi.com/products/{productId}", fakeStoreProductDTOReq, FakeStoreProductDTO.class, id).getBody();

        return from(fakeStoreProductDTO);

    }

    public <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
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

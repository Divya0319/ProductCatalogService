package com.fastturtle.productcatalogservice.clients;

import com.fastturtle.productcatalogservice.dtos.FakeStoreProductDTO;
import com.fastturtle.productcatalogservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class FakeStoreApiClient {

    private final RestTemplateBuilder restTemplateBuilder;

    @Autowired
    public FakeStoreApiClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public FakeStoreProductDTO getProductById(Long id) {
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity = requestForEntity(HttpMethod.GET, "https://fakestoreapi.com/products/{productId}", null, FakeStoreProductDTO.class, id);
        if(fakeStoreProductDTOResponseEntity.getBody() != null && fakeStoreProductDTOResponseEntity.getStatusCode().equals(HttpStatus.OK)) {
            return fakeStoreProductDTOResponseEntity.getBody();
        }
        return null;
    }

    public FakeStoreProductDTO replaceProduct(Long id, FakeStoreProductDTO fakeStoreProductDTOReq) {
        FakeStoreProductDTO fakeStoreProductDTO = requestForEntity(HttpMethod.PUT, "https://fakestoreapi.com/products/{productId}", fakeStoreProductDTOReq, FakeStoreProductDTO.class, id).getBody();
        return fakeStoreProductDTO;
    }

    public FakeStoreProductDTO createProduct(FakeStoreProductDTO fakeStoreProductDTOReq) {
        FakeStoreProductDTO fakeStoreProductDTO = requestForEntity(HttpMethod.POST, "https://fakestoreapi.com/products", fakeStoreProductDTOReq, FakeStoreProductDTO.class).getBody();
        return fakeStoreProductDTO;
    }

    public FakeStoreProductDTO deleteProduct(Long id) {
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity = requestForEntity(HttpMethod.DELETE, "https://fakestoreapi.com/products/{productId}", null, FakeStoreProductDTO.class, id);
        if(fakeStoreProductDTOResponseEntity.getBody() != null && fakeStoreProductDTOResponseEntity.getStatusCode().equals(HttpStatus.OK)) {
            return fakeStoreProductDTOResponseEntity.getBody();
        }
        return null;
    }

    public <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }
}

package com.fastturtle.productcatalogservice.service;

import com.fastturtle.productcatalogservice.models.Product;
import com.fastturtle.productcatalogservice.repositories.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    private final ProductRepo productRepo;

    public SearchService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> searchProduct(String query) {
        return productRepo.findProductsByName(query);
    }
}

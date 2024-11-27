package com.fastturtle.productcatalogservice.service;

import com.fastturtle.productcatalogservice.models.Product;
import com.fastturtle.productcatalogservice.repositories.ProductRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    private final ProductRepo productRepo;

    public SearchService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Page<Product> searchProduct(String query, Integer pageNumber, Integer pageSize) {
        Sort sort = Sort.by("price").descending().and(Sort.by("id").ascending());
        return productRepo.findProductsByName(query, PageRequest.of(pageNumber, pageSize, sort));
    }
}

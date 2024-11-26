package com.fastturtle.productcatalogservice.controllers;

import com.fastturtle.productcatalogservice.dtos.ProductDTO;
import com.fastturtle.productcatalogservice.dtos.SearchProductRequestDTO;
import com.fastturtle.productcatalogservice.models.Product;
import com.fastturtle.productcatalogservice.service.SearchService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping
    public List<Product> searchProducts(@RequestBody SearchProductRequestDTO searchProductRequestDTO) {
        return searchService.searchProduct(searchProductRequestDTO.getQuery());
    }
}

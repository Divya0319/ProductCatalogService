package com.fastturtle.productcatalogservice.service;

import com.fastturtle.productcatalogservice.dtos.SortParam;
import com.fastturtle.productcatalogservice.dtos.SortType;
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

    public Page<Product> searchProduct(String query, Integer pageNumber, Integer pageSize, List<SortParam> sortParams) {
//        Sort sort = Sort.by("price").descending().and(Sort.by("id").ascending());

        Sort sort = null;

        if(!sortParams.isEmpty()) {
            if(sortParams.get(0).getSortType().equals(SortType.ASC)) {
                sort = Sort.by(sortParams.get(0).getParamName());
            } else {
                sort = Sort.by(sortParams.get(0).getParamName()).descending();
            }

        }

        for(int i = 1; i < sortParams.size(); i++) {
            if(sortParams.get(i).getSortType().equals(SortType.ASC)) {
                sort.and(Sort.by(sortParams.get(i).getParamName()));
            } else {
                sort.and(Sort.by(sortParams.get(i).getParamName()).descending());
            }
        }

        return productRepo.findProductsByName(query, PageRequest.of(pageNumber, pageSize, sort));
    }
}

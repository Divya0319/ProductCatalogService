package com.fastturtle.productcatalogservice.service;

import com.fastturtle.productcatalogservice.models.Product;

import java.util.List;

public interface IProductService {

    Product getProductById(Long id);

    List<Product> getAllProducts();

    Product getProductBasedOnScope(Long pid, Long uid);

    Product createProduct(Product product);

    Product replaceProduct(Long id, Product product);

    Product deleteProduct(Long id);
}

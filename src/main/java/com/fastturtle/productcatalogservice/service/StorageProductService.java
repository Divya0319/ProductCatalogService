package com.fastturtle.productcatalogservice.service;

import com.fastturtle.productcatalogservice.models.Product;
import com.fastturtle.productcatalogservice.models.State;
import com.fastturtle.productcatalogservice.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Primary
public class StorageProductService implements IProductService {

    private final ProductRepo productRepo;

    @Autowired
    public StorageProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> productOptional = productRepo.findById(id);
        if(productOptional.isPresent()) {
            return productOptional.get();
        }
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> productList = productRepo.findAll();
        if(productList.isEmpty()) {
            return null;
        }

        List<Product> filteredProducts = productList.stream().filter(product -> product.getState() == State.ACTIVE).toList();
        return filteredProducts;
    }

    @Override
    public Product createProduct(Product product) {
        product.setState(State.ACTIVE);
        Product newProduct = productRepo.save(product);
        return newProduct;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        Optional<Product> productOptional = productRepo.findById(id);
        if(productOptional.isPresent()) {
            Product oldProduct = productOptional.get();
            oldProduct.setId(product.getId());
            oldProduct.setName(product.getName());
            oldProduct.setPrice(product.getPrice());
            oldProduct.setImageUrl(product.getImageUrl());
            oldProduct.setDescription(product.getDescription());
            oldProduct.setPrice(product.getPrice());
            oldProduct.setCategory(product.getCategory());

            return productRepo.save(oldProduct);

        }
        return null;
    }

    @Override
    public Product deleteProduct(Long id) {
        Optional<Product> productOptional = productRepo.findById(id);
        if(productOptional.isPresent()) {
            Product oldProduct = productOptional.get();
            oldProduct.setState(State.INACTIVE);
            return productRepo.save(oldProduct);
        }
        return null;
    }
}

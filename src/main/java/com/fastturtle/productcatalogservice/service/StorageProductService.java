package com.fastturtle.productcatalogservice.service;

import com.fastturtle.productcatalogservice.dtos.UserDTO;
import com.fastturtle.productcatalogservice.exceptions.UserNotAdminOrSellerException;
import com.fastturtle.productcatalogservice.models.Category;
import com.fastturtle.productcatalogservice.models.Product;
import com.fastturtle.productcatalogservice.models.State;
import com.fastturtle.productcatalogservice.repositories.CategoryRepo;
import com.fastturtle.productcatalogservice.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class StorageProductService implements IProductService {

    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;
    private final RestTemplate restTemplate;

    public StorageProductService(ProductRepo productRepo, CategoryRepo categoryRepo, RestTemplate restTemplate) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
        this.restTemplate = restTemplate;
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
    public Product getProductBasedOnScope(Long pid, Long uid) {
        Product product = productRepo.findById(pid).get();

        if(product.getIsPublic()) {
            return product;
        }

        UserDTO userDTO = restTemplate.getForEntity("http://localhost:9000/users/{uid}", UserDTO.class, uid).getBody();
        boolean isAdminOrSeller = userDTO.getRoles().stream().anyMatch(r -> r.getName().equals("ADMIN") || r.getName().equals("SELLER"));
        if(!isAdminOrSeller) {
            throw new UserNotAdminOrSellerException("Provided user neither has ADMIN or SELLER role");
        }
        return product;
    }

    @Override
    public Product createProduct(Product product) {
        if(product.getCategory() != null && product.getCategory().getId() != null) {
            Optional<Category> existingCategory = categoryRepo.findById(product.getCategory().getId());
            existingCategory.ifPresent(product::setCategory);
        }
        Product newProduct = productRepo.save(product);
        return newProduct;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        Optional<Product> productOptional = productRepo.findById(id);
        if(productOptional.isPresent()) {
            Product oldProduct = productOptional.get();

            // Only update fields from the incoming product that are intended to change
            oldProduct.setName(product.getName());
            oldProduct.setPrice(product.getPrice());
            oldProduct.setImageUrl(product.getImageUrl());
            oldProduct.setDescription(product.getDescription());

            // Handle Category updates
            Category updatedCategory = product.getCategory();
            if (updatedCategory != null) {
                // Check if we need to update the category fields, without altering createdAt
                Category existingCategory = oldProduct.getCategory();
                if (existingCategory != null) {
                    existingCategory.setName(updatedCategory.getName());
                    existingCategory.setDescription(updatedCategory.getDescription());
                } else {
                    // If no existing category, assign the new one
                    oldProduct.setCategory(updatedCategory);
                }
            }

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

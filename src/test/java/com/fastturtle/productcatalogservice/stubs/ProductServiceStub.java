package com.fastturtle.productcatalogservice.stubs;

import com.fastturtle.productcatalogservice.models.Product;
import com.fastturtle.productcatalogservice.service.IProductService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
//@Primary
public class ProductServiceStub implements IProductService {

    private Map<Long, Product> productMap;

    public ProductServiceStub() {
        this.productMap = new HashMap<>();
    }

    @Override
    public Product getProductById(Long id) {
        return productMap.get(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return (List<Product>) productMap.values();
    }

    @Override
    public Product createProduct(Product product) {
        productMap.put(product.getId(), product);
        return productMap.get(product.getId());
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        productMap.put(id, product);
        return productMap.get(id);
    }

    @Override
    public Product deleteProduct(Long id) {
        Product removed = productMap.remove(id);
        return removed;
    }
}

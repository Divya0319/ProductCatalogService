package com.fastturtle.productcatalogservice.exceptions;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException() {
        super("Product not found");
    }
}

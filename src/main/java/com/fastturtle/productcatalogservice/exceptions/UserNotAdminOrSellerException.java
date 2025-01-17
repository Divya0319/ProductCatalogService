package com.fastturtle.productcatalogservice.exceptions;

public class UserNotAdminOrSellerException extends RuntimeException {
    public UserNotAdminOrSellerException(String message) {
        super(message);
    }
}

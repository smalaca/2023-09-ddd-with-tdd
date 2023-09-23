package com.smalaca.pruchase.domain.cart;

import java.util.UUID;

class ProductException extends RuntimeException {
    ProductException() {
        this("Not all product could be found.");
    }

    private ProductException(String message) {
        super(message);
    }

    static RuntimeException notAvailable(UUID productId) {
        return new ProductException("Product: " + productId + " is not available");
    }
}

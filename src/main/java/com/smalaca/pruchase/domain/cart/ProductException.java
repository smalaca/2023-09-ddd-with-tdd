package com.smalaca.pruchase.domain.cart;

class ProductException extends RuntimeException {
    ProductException() {
        super("Not all product could be found.");
    }
}

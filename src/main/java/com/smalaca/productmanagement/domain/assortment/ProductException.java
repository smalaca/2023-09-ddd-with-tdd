package com.smalaca.productmanagement.domain.assortment;

class ProductException extends RuntimeException {
    ProductException(String code) {
        super("Product: " + code + " cannot be added.");
    }
}

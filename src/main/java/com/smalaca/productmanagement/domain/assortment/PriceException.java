package com.smalaca.productmanagement.domain.assortment;

class PriceException extends RuntimeException {
    PriceException(int price) {
        super("Price " + price + " is invalid");
    }
}

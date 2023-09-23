package com.smalaca.productmanagement.domain.assortment;

import lombok.EqualsAndHashCode;

// Entity
@EqualsAndHashCode
class Product {
    private final String code;
    private final String name;
    private final int price;

    private Product(String productCode, String productName, int price) {
        code = productCode;
        name = productName;
        this.price = price;
    }

    static Product product(String productCode, String productName, int price) {
        return new Product(productCode, productName, price);
    }
}

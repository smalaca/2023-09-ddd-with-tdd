package com.smalaca.productmanagement.domain.assortment;

import lombok.EqualsAndHashCode;

// Entity
@EqualsAndHashCode
class Product {
    private final String code;
    private final String name;
    private int price;

    Product(String code, String name) {
        this.code = code;
        this.name = name;
    }

    Product(String productCode, String productName, int price) {
        code = productCode;
        name = productName;
        this.price = price;
    }

    static Product product(String productCode, String productName, int price) {
        return new Product(productCode, productName, price);
    }
}

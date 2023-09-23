package com.smalaca.productmanagement.domain.assortment;

import lombok.EqualsAndHashCode;

// Entity
@EqualsAndHashCode
class Product {
    private final String code;
    private final String name;

    Product(String code, String name) {
        this.code = code;
        this.name = name;
    }
}

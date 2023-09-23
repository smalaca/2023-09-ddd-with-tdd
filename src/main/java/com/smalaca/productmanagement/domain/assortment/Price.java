package com.smalaca.productmanagement.domain.assortment;

import lombok.EqualsAndHashCode;

// Value Object
@EqualsAndHashCode
class Price {
    private final int value;

    private Price(int value) {
        this.value = value;
    }

    static Price price(int value) {
        return new Price(value);
    }
}

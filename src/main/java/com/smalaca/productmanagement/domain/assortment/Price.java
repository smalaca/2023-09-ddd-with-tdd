package com.smalaca.productmanagement.domain.assortment;

import lombok.EqualsAndHashCode;

// Value Object
@EqualsAndHashCode
class Price {
    private final int value;

    private Price(int value) {
        this.value = value;
    }

    // Factory
    static Price price(int value) {
        if (value < 1) {
            throw new PriceException();
        }
        return new Price(value);
    }
}

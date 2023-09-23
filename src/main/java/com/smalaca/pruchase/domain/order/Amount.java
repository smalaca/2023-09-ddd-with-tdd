package com.smalaca.pruchase.domain.order;

import lombok.EqualsAndHashCode;

// Value Object
@EqualsAndHashCode
class Amount {
    private final Integer value;

    private Amount(Integer value) {
        this.value = value;
    }

    // Factory
    static Amount amount(Integer value) {
        if (value < 1) {
            throw new AmountException();
        }
        return new Amount(value);
    }
}

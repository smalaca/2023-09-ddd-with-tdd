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
        return new Amount(value);
    }
}

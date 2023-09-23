package com.smalaca.pruchase.domain.order;

import java.util.UUID;

// Value Object
class OrderNumber {
    private final String value;

    private OrderNumber(String value) {
        this.value = value;
    }

    static OrderNumber create() {
        return new OrderNumber(UUID.randomUUID().toString());
    }
}

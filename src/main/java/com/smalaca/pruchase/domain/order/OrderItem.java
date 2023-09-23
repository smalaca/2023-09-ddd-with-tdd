package com.smalaca.pruchase.domain.order;

import lombok.EqualsAndHashCode;

import java.util.UUID;

// Value Object
@EqualsAndHashCode
class OrderItem {
    private final UUID productId;
    private final Amount amount;

    private OrderItem(UUID productId, Amount amount) {
        this.productId = productId;
        this.amount = amount;
    }

    static OrderItem create(UUID productId, int amount) {
        return new OrderItem(productId, Amount.amount(amount));
    }
}

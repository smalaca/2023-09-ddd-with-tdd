package com.smalaca.pruchase.domain.order;

import lombok.EqualsAndHashCode;

import java.util.UUID;

// Value Object
@EqualsAndHashCode
class OrderItem {
    private final UUID productId;
    private final int productAmount;

    private OrderItem(UUID productId, int productAmount) {
        this.productId = productId;
        this.productAmount = productAmount;
    }

    static OrderItem create(UUID productId, int amount) {
        return new OrderItem(productId, amount);
    }
}

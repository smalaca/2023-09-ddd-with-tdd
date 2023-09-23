package com.smalaca.pruchase.domain.order;

import lombok.EqualsAndHashCode;

import java.util.UUID;

// Value Object
@EqualsAndHashCode
class OrderItem {
    private final UUID productId;
    private final int productAmount;

    OrderItem(UUID productId, int productAmount) {
        this.productId = productId;
        this.productAmount = productAmount;
    }
}

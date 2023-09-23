package com.smalaca.pruchase.domain.cart;

import java.util.UUID;

// Value Object
public class OrderItem {
    private final UUID productId;
    private final int productAmount;

    public OrderItem(UUID productId, int productAmount) {
        this.productId = productId;
        this.productAmount = productAmount;
    }
}

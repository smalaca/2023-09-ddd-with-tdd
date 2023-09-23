package com.smalaca.pruchase.domain.cart;

import com.smalaca.pruchase.domain.order.Order;

import java.util.Map;
import java.util.UUID;

// Aggregate Root
public class Cart {
    private final Map<UUID, Integer> products;

    Cart(Map<UUID, Integer> products) {
        this.products = products;
    }

    public Order chooseProducts(Map<UUID, Integer> products) {
        Order.Builder builder = new Order.Builder();

        products.forEach(builder::addProduct);

        return builder.build();
    }
}

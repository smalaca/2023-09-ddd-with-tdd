package com.smalaca.pruchase.domain.cart;

import com.smalaca.pruchase.domain.order.Order;

// Aggregate Root
public class Cart {
    public Order chooseProducts() {
        return new Order();
    }
}

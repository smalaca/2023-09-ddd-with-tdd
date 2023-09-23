package com.smalaca.pruchase.domain.cart;

import com.smalaca.pruchase.domain.order.Order;
import com.smalaca.pruchase.domain.order.OrderFactory;

// Aggregate Root
public class Cart {
    public Order chooseProducts() {
        return new OrderFactory().create();
    }
}

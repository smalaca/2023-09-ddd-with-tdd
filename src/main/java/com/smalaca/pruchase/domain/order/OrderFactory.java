package com.smalaca.pruchase.domain.order;

public class OrderFactory {
    public Order create() {
        return new Order(OrderNumber.create());
    }
}

package com.smalaca.pruchase.domain.order;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {
    private final OrderNumber orderNumber;
    private final List<OrderItem> items;

    private Order(OrderNumber orderNumber, List<OrderItem> items) {
        this.orderNumber = orderNumber;
        this.items = items;
    }

    public static class Builder {
        private final List<OrderItem> items = new ArrayList<>();

        public Order build() {
            return new Order(OrderNumber.create(), items);
        }

        public void addProduct(UUID productId, Integer amount) {
            items.add(OrderItem.create(productId, amount));
        }
    }
}

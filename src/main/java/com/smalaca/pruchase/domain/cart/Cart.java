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

    public Order chooseProducts(Map<UUID, Integer> products, ProductManagementService productManagementService) {
        if (isAnyNotPresent(products)) {
            throw new ProductException();
        }

        Order.Builder builder = new Order.Builder();
        products.forEach(builder::addProduct);

        for (Map.Entry<UUID, Integer> choice : products.entrySet()) {
            if (!productManagementService.book(choice.getKey(), choice.getValue())) {
                throw ProductException.notAvailable(choice.getKey());
            }
        }

        return builder.build();
    }

    private boolean isAnyNotPresent(Map<UUID, Integer> products) {
        return products.entrySet()
                .stream()
                .anyMatch(product -> isNotPresent(product.getKey()));
    }

    private boolean isNotPresent(UUID productId) {
        return products.entrySet()
                .stream()
                .noneMatch(product -> product.getKey().equals(productId));
    }
}

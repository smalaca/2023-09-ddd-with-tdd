package com.smalaca.productmanagement.domain.assortment;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// Aggregate Root
public class Assortment {
    private final UUID sellerId;
    private final List<Product> products = new ArrayList<>();

    Assortment(UUID sellerId) {
        this.sellerId = sellerId;
    }

    public void addProduct(String code, String name) {
        products.add(new Product(code, name));
    }
}

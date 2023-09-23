package com.smalaca.productmanagement.domain.assortment;

import java.util.UUID;

// Aggregate Root
public class Assortment {
    private final UUID sellerId;

    public Assortment(UUID sellerId) {
        this.sellerId = sellerId;
    }
}

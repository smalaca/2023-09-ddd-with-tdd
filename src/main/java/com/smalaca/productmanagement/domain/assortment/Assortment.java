package com.smalaca.productmanagement.domain.assortment;

import java.util.UUID;

// Aggregate Root
public class Assortment {
    private final UUID sellerId;

    Assortment(UUID sellerId) {
        this.sellerId = sellerId;
    }
}

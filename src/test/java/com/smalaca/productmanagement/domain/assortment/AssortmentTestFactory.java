package com.smalaca.productmanagement.domain.assortment;

import java.util.UUID;

public class AssortmentTestFactory {
    public static Assortment create(UUID sellerId) {
        return new Assortment(sellerId);
    }
}

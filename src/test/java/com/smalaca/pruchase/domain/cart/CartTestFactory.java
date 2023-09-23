package com.smalaca.pruchase.domain.cart;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

public class CartTestFactory {
    public Cart create(Map<UUID, Integer> products) {
        return new Cart(products);
    }

    public Cart create() {
        return new Cart(Collections.emptyMap());
    }
}

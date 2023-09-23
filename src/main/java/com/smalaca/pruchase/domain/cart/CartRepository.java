package com.smalaca.pruchase.domain.cart;

import java.util.UUID;

public interface CartRepository {
    Cart find(UUID buyerId);

    void save(Cart cart);
}

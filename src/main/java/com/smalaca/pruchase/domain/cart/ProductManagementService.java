package com.smalaca.pruchase.domain.cart;

import java.util.UUID;

public interface ProductManagementService {
    boolean book(UUID productId, int amount);
}

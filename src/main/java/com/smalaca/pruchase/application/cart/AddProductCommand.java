package com.smalaca.pruchase.application.cart;

import java.util.UUID;

public record AddProductCommand(UUID buyerId, UUID productId, int amount) {
}

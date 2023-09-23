package com.smalaca.pruchase.application.cart;

import java.util.UUID;

public record RemoveProductCommand(UUID buyerId, UUID productId, int amount) {
}

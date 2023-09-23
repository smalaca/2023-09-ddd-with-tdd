package com.smalaca.pruchase.application.cart;

import java.util.Map;
import java.util.UUID;

public record ChooseProductsCommand(UUID buyerId, Map<UUID, Integer> products) {
}

package com.smalaca.productmanagement.application.assortment;

import java.util.UUID;

public record AddProductCommand(UUID sellerId, String productCode, String productName) {
}

package com.smalaca.productmanagement.application.assortment;

import com.smalaca.productmanagement.domain.assortment.AddProductDto;

import java.util.UUID;

public record AddProductCommand(UUID sellerId, String productCode, String productName, int price) {
    AddProductDto asDto() {
        return new AddProductDto(productCode(), productName(), price());
    }
}

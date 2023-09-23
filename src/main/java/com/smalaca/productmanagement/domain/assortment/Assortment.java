package com.smalaca.productmanagement.domain.assortment;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// Aggregate Root
@Entity
public class Assortment {
    @Id
    private final UUID sellerId;
    @OneToMany
    private final List<Product> products = new ArrayList<>();

    Assortment(UUID sellerId) {
        this.sellerId = sellerId;
    }

    public void addProduct(AddProductDto dto, ProductValidationService productValidationService) {
        if (!productValidationService.isValid(dto.code())) {
            throw new ProductException(dto.code());
        }

        products.add(Product.product(dto.code(), dto.name(), dto.price()));
    }
}

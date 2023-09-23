package com.smalaca.productmanagement.domain.assortment;

import org.assertj.core.api.Assertions;

import java.util.List;
import java.util.UUID;

public class AssortmentAssertion {
    private final Assortment actual;

    private AssortmentAssertion(Assortment actual) {
        this.actual = actual;
    }

    public static AssortmentAssertion assertAssertion(Assortment actual) {
        return new AssortmentAssertion(actual);
    }

    public AssortmentAssertion hasSellerId(UUID expected) {
        Assertions.assertThat(actual).extracting("sellerId").isEqualTo(expected);
        return this;
    }

    public AssortmentAssertion containsProduct(String expectedProductCode, String expectedProductName) {
        Assertions.assertThat(actual).extracting("products").satisfies(actualProducts -> {
            List actualProductsList = (List) actualProducts;
            Assertions.assertThat(actualProductsList).contains(new Product(expectedProductCode, expectedProductName));
        });
        return this;
    }
}

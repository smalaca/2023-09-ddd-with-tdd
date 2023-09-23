package com.smalaca.productmanagement.domain.assortment;

import org.assertj.core.api.Assertions;

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
}

package com.smalaca.pruchase.domain.cart;

import com.smalaca.pruchase.domain.order.Order;
import org.assertj.core.api.Assertions;

import java.util.List;
import java.util.UUID;

public class OrderAssertion {
    private final Order actual;

    private OrderAssertion(Order actual) {
        this.actual = actual;
    }

    public static OrderAssertion assertOder(Order actual) {
        return new OrderAssertion(actual);
    }

    public OrderAssertion hasOrderNumber() {
        Assertions.assertThat(actual).extracting("orderNumber").isNotNull();
        return this;
    }

    public OrderAssertion hasProducts(int expected) {
        Assertions.assertThat(actual).extracting("items").satisfies(actualItems -> {
            List actualItemsList = (List) actualItems;
            Assertions.assertThat(actualItemsList).hasSize(expected);
        });
        return this;
    }

    public OrderAssertion containsProduct(UUID expectedProductId, int expectedProductAmount) {
        Assertions.assertThat(actual).extracting("items").satisfies(actualItems -> {
            List actualItemsList = (List) actualItems;
            Assertions.assertThat(actualItemsList).contains(new OrderItem(expectedProductId, expectedProductAmount));
        });
        return this;
    }
}

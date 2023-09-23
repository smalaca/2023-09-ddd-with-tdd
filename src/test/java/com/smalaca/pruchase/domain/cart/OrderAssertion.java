package com.smalaca.pruchase.domain.cart;

import com.smalaca.pruchase.domain.order.Order;
import org.assertj.core.api.Assertions;

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
}

package com.smalaca.pruchase.application.cart;

import com.google.common.collect.ImmutableMap;
import com.smalaca.pruchase.domain.cart.Cart;
import com.smalaca.pruchase.domain.cart.CartRepository;
import com.smalaca.pruchase.domain.order.Order;
import com.smalaca.pruchase.domain.order.OrderRepository;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.util.Map;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;

public class CartApplicationServiceTest {
    private final CartRepository cartRepository = Mockito.mock(CartRepository.class);
    private final OrderRepository orderRepository = Mockito.mock(OrderRepository.class);
    private final CartApplicationService service = new CartApplicationService(cartRepository, orderRepository);

    @Test
    void shouldChooseProductsFromCart() {
        UUID buyerId = randomBuyerId();
        Map<UUID, Integer> products = ImmutableMap.of(
                randomProductId(), 1,
                randomProductId(), 5,
                randomProductId(), 3
        );
        givenExistingCart(buyerId);

        service.chooseProducts(new ChooseProductsCommand(buyerId, products));

        BDDMockito.then(orderRepository).should().save(any(Order.class));
    }

    private void givenExistingCart(UUID buyerId) {
        BDDMockito.given(cartRepository.find(buyerId)).willReturn(new Cart());
    }

    private UUID randomProductId() {
        return UUID.randomUUID();
    }

    private UUID randomBuyerId() {
        return UUID.randomUUID();
    }
}

package com.smalaca.pruchase.application.cart;

import com.google.common.collect.ImmutableMap;
import com.smalaca.pruchase.domain.cart.Cart;
import com.smalaca.pruchase.domain.cart.CartRepository;
import com.smalaca.pruchase.domain.cart.OrderAssertion;
import com.smalaca.pruchase.domain.order.Order;
import com.smalaca.pruchase.domain.order.OrderRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
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
        UUID productIdOne = randomProductId();
        UUID productIdTwo = randomProductId();
        UUID productIdThree = randomProductId();
        Map<UUID, Integer> products = ImmutableMap.of(
                productIdOne, 1,
                productIdTwo, 5,
                productIdThree, 3
        );
        givenExistingCart(buyerId);

        service.chooseProducts(new ChooseProductsCommand(buyerId, products));

        OrderAssertion.assertOder(thenOrderSaved())
                .hasOrderNumber()
                .hasProducts(3)
                .containsProduct(productIdOne, 1)
                .containsProduct(productIdTwo, 5)
                .containsProduct(productIdThree, 3);
    }

    private Order thenOrderSaved() {
        ArgumentCaptor<Order> captor = ArgumentCaptor.forClass(Order.class);
        BDDMockito.then(orderRepository).should().save(captor.capture());
        return captor.getValue();
    }

    @Test
    void shouldAddProductToCart() {
        UUID buyerId = randomBuyerId();
        givenExistingCart(buyerId);

        service.addProduct(new AddProductCommand(buyerId, randomProductId(), 42));

        BDDMockito.then(cartRepository).should().save(any(Cart.class));
    }

    @Test
    void shouldRemoveProductFromCart() {
        UUID buyerId = randomBuyerId();
        givenExistingCart(buyerId);

        service.removeProduct(new RemoveProductCommand(buyerId, randomProductId()));

        BDDMockito.then(cartRepository).should().save(any(Cart.class));
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

package com.smalaca.pruchase.application.cart;

import com.google.common.collect.ImmutableMap;
import com.smalaca.pruchase.domain.cart.Cart;
import com.smalaca.pruchase.domain.cart.CartRepository;
import com.smalaca.pruchase.domain.cart.CartTestFactory;
import com.smalaca.pruchase.domain.cart.ProductManagementService;
import com.smalaca.pruchase.domain.order.Order;
import com.smalaca.pruchase.domain.order.OrderAssertion;
import com.smalaca.pruchase.domain.order.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

public class CartApplicationServiceTest {
    private final CartRepository cartRepository = Mockito.mock(CartRepository.class);
    private final OrderRepository orderRepository = Mockito.mock(OrderRepository.class);
    private final ProductManagementService productManagementService = Mockito.mock(ProductManagementService.class);
    private final CartApplicationService service = new CartApplicationService(cartRepository, orderRepository, productManagementService);
    private final CartTestFactory cartTestFactory = new CartTestFactory();

    @Test
    void shouldRecognizeTheAmountOfProductIsNotGreaterThanZeroWhenChoosingProducts() {
        UUID buyerId = randomBuyerId();
        Map<UUID, Integer> products = ImmutableMap.of(randomProductId(), -42);
        givenExistingCartWith(buyerId, products);

        Executable executable = () -> service.chooseProducts(new ChooseProductsCommand(buyerId, products));

        RuntimeException actual = assertThrows(RuntimeException.class, executable);
        Assertions.assertThat(actual).hasMessage("Amount must be greater than zero.");
    }

    @Test
    void shouldRecognizeProductIsNotInTheCartWhenChoosingProducts() {
        UUID buyerId = randomBuyerId();
        Map<UUID, Integer> products = ImmutableMap.of(randomProductId(), 13);
        givenExistingCartWith(buyerId, ImmutableMap.of(randomProductId(), 10));

        Executable executable = () -> service.chooseProducts(new ChooseProductsCommand(buyerId, products));

        RuntimeException actual = assertThrows(RuntimeException.class, executable);
        Assertions.assertThat(actual).hasMessage("Not all product could be found.");
    }

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
        givenAvailableProduct(productIdOne, 1);
        givenAvailableProduct(productIdTwo, 5);
        givenAvailableProduct(productIdThree, 3);
        givenExistingCartWith(buyerId, products);

        service.chooseProducts(new ChooseProductsCommand(buyerId, products));

        OrderAssertion.assertOder(thenOrderSaved())
                .hasOrderNumber()
                .hasProducts(3)
                .containsProduct(productIdOne, 1)
                .containsProduct(productIdTwo, 5)
                .containsProduct(productIdThree, 3);
    }

    @Test
    void shouldRecognizeNotAvailableProducts() {
        UUID buyerId = randomBuyerId();
        UUID productIdOne = randomProductId();
        Map<UUID, Integer> products = ImmutableMap.of(productIdOne, 1);
        givenNotAvailableProduct(productIdOne, 1);
        givenExistingCartWith(buyerId, products);

        Executable executable = () -> service.chooseProducts(new ChooseProductsCommand(buyerId, products));

        RuntimeException actual = assertThrows(RuntimeException.class, executable);
        Assertions.assertThat(actual).hasMessage("Product: " + productIdOne + " is not available");
    }

    private void givenNotAvailableProduct(UUID productId, int amount) {
        BDDMockito.given(productManagementService.book(productId, amount)).willReturn(false);
    }

    @Test
    void shouldBookChosenProducts() {
        UUID buyerId = randomBuyerId();
        UUID productIdOne = randomProductId();
        UUID productIdTwo = randomProductId();
        Map<UUID, Integer> products = ImmutableMap.of(
                productIdOne, 1,
                productIdTwo, 5
        );
        givenAvailableProduct(productIdOne, 1);
        givenAvailableProduct(productIdTwo, 5);
        givenExistingCartWith(buyerId, products);

        service.chooseProducts(new ChooseProductsCommand(buyerId, products));

        thenProductWasBooked(productIdOne, 1);
        thenProductWasBooked(productIdTwo, 5);
    }

    private void thenProductWasBooked(UUID productId, int amount) {
        BDDMockito.then(productManagementService).should().book(productId, amount);
    }

    private void givenAvailableProduct(UUID productId, int amount) {
        BDDMockito.given(productManagementService.book(productId, amount)).willReturn(true);
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

    private void givenExistingCartWith(UUID buyerId, Map<UUID, Integer> products) {
        BDDMockito.given(cartRepository.find(buyerId)).willReturn(cartTestFactory.create(products));
    }

    private void givenExistingCart(UUID buyerId) {
        BDDMockito.given(cartRepository.find(buyerId)).willReturn(cartTestFactory.create());
    }

    private UUID randomProductId() {
        return UUID.randomUUID();
    }

    private UUID randomBuyerId() {
        return UUID.randomUUID();
    }
}

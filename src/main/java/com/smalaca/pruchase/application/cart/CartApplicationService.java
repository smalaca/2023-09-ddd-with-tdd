package com.smalaca.pruchase.application.cart;

import com.smalaca.pruchase.domain.cart.Cart;
import com.smalaca.pruchase.domain.cart.CartRepository;
import com.smalaca.pruchase.domain.order.Order;
import com.smalaca.pruchase.domain.order.OrderRepository;

class CartApplicationService {
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;

    CartApplicationService(CartRepository cartRepository, OrderRepository orderRepository) {
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
    }

    void chooseProducts(ChooseProductsCommand command) {
        Cart cart = cartRepository.find(command.buyerId());

        Order order = cart.chooseProducts();

        orderRepository.save(order);
    }

    void addProduct(AddProductCommand command) {
        Cart cart = cartRepository.find(command.buyerId());

        cartRepository.save(cart);
    }

    void removeProduct(RemoveProductCommand command) {

    }
}

package com.smalaca.pruchase.application.cart;

import com.smalaca.pruchase.domain.cart.CartRepository;
import com.smalaca.pruchase.domain.order.OrderRepository;

class CartApplicationService {
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;

    CartApplicationService(CartRepository cartRepository, OrderRepository orderRepository) {
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
    }

    void chooseProducts(ChooseProductsCommand command) {

    }
}

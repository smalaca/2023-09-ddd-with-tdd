package com.smalaca.pruchase.application.cart;

import com.smalaca.pruchase.domain.cart.Cart;
import com.smalaca.pruchase.domain.cart.CartRepository;
import com.smalaca.pruchase.domain.cart.ProductManagementService;
import com.smalaca.pruchase.domain.order.Order;
import com.smalaca.pruchase.domain.order.OrderRepository;

class CartApplicationService {
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final ProductManagementService productManagementService;

    CartApplicationService(CartRepository cartRepository, OrderRepository orderRepository, ProductManagementService productManagementService) {
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.productManagementService = productManagementService;
    }

    void chooseProducts(ChooseProductsCommand command) {
        Cart cart = cartRepository.find(command.buyerId());

        Order order = cart.chooseProducts(command.products(), productManagementService);

        orderRepository.save(order);
    }

    void addProduct(AddProductCommand command) {
        Cart cart = cartRepository.find(command.buyerId());

        cartRepository.save(cart);
    }

    void removeProduct(RemoveProductCommand command) {
        Cart cart = cartRepository.find(command.buyerId());

        cartRepository.save(cart);
    }
}

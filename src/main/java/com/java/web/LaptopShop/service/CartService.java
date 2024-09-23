package com.java.web.LaptopShop.service;

import org.springframework.stereotype.Service;

import com.java.web.LaptopShop.domain.Cart;
import com.java.web.LaptopShop.domain.CartDetail;
import com.java.web.LaptopShop.domain.Order;
import com.java.web.LaptopShop.domain.OrderDetail;
import com.java.web.LaptopShop.domain.User;
import com.java.web.LaptopShop.repository.CartRepository;
import com.java.web.LaptopShop.repository.OrderDetailReposiroty;
import com.java.web.LaptopShop.repository.OrderRepository;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final OrderDetailReposiroty orderDetailReposiroty;
    private final OrderRepository orderRepository;

    public CartService(CartRepository cartRepository, OrderDetailReposiroty orderDetailReposiroty,
            OrderRepository orderRepository) {
        this.cartRepository = cartRepository;
        this.orderDetailReposiroty = orderDetailReposiroty;
        this.orderRepository = orderRepository;
    }

    public Cart findByUser(User user) {
        return this.cartRepository.findByUser(user);
    }

    public Cart save(Cart cart) {
        return this.cartRepository.save(cart);
    }

}

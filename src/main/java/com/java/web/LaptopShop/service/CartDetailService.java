package com.java.web.LaptopShop.service;

import org.springframework.stereotype.Service;

import com.java.web.LaptopShop.domain.Cart;
import com.java.web.LaptopShop.domain.CartDetail;
import com.java.web.LaptopShop.domain.Order;
import com.java.web.LaptopShop.domain.OrderDetail;
import com.java.web.LaptopShop.domain.Product;
import com.java.web.LaptopShop.repository.CartDetailRepository;

import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartDetailService {
    private final CartDetailRepository cartDetailRepository;

    public CartDetailService(CartDetailRepository cartDetailRepository) {
        this.cartDetailRepository = cartDetailRepository;
    }

    public CartDetail findByProductAndCart(Product product, Cart cart) {
        return this.cartDetailRepository.findByProductAndCart(product, cart);
    }

    public CartDetail save(CartDetail cartDetail) {
        return this.cartDetailRepository.save(cartDetail);
    }

    public List<CartDetail> findByCart(Cart cart) {
        return this.cartDetailRepository.findByCart(cart);
    }

    public long totalPrice(List<CartDetail> cartDetails) {
        long totalPrice = 0;
        for (CartDetail cartDetail : cartDetails) {
            totalPrice += cartDetail.getPrice() * cartDetail.getQuantity();
        }
        return totalPrice;
    }

    public List<CartDetail> getAfterConfirm(Cart cart) {
        List<CartDetail> cartDetails = cart == null ? new ArrayList<>() : cart.getCartDetails();
        List<CartDetail> _cartDetails = new ArrayList<>();
        for (CartDetail cartDetail : cartDetails) {
            CartDetail _cartDetail = this.cartDetailRepository.findById(cartDetail.getId()).get();
            _cartDetail.setQuantity(cartDetail.getQuantity());
            _cartDetails.add(_cartDetail);
        }
        return _cartDetails;
    }

    public static OrderDetail toOrderDetail(CartDetail cartDetail) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProduct(cartDetail.getProduct());
        orderDetail.setQuantity(cartDetail.getQuantity());
        orderDetail.setTotalPrice(cartDetail.getPrice());
        return orderDetail;
    }

    public void handleDeleteById(long id, HttpSession session) {
        CartDetail cartDetail = this.cartDetailRepository.findById(id).get();
        Cart cart = cartDetail.getCart();
        this.cartDetailRepository.delete(cartDetail);
        cart.setSum(cart.getSum() - 1);
        session.setAttribute("sum", cart.getSum());
    }
}

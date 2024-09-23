package com.java.web.LaptopShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.web.LaptopShop.domain.Cart;
import com.java.web.LaptopShop.domain.CartDetail;
import com.java.web.LaptopShop.domain.Product;
import java.util.List;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {
    CartDetail findByProductAndCart(Product product, Cart cart);

    List<CartDetail> findByCart(Cart cart);

    void deleteByCart(Cart cart);
}

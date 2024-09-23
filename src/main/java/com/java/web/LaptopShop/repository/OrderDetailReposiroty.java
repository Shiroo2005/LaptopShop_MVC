package com.java.web.LaptopShop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.web.LaptopShop.domain.Order;
import com.java.web.LaptopShop.domain.OrderDetail;

@Repository
public interface OrderDetailReposiroty extends JpaRepository<OrderDetail, Long> {
    void deleteByOrder(Order order);
}

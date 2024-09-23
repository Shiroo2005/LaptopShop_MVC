package com.java.web.LaptopShop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.web.LaptopShop.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    void deleteByIdIn(List<Long> list);
}

package com.java.web.LaptopShop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.java.web.LaptopShop.domain.Order;
import com.java.web.LaptopShop.repository.OrderDetailReposiroty;
import com.java.web.LaptopShop.repository.OrderRepository;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailReposiroty orderDetailReposiroty;

    public OrderService(OrderRepository orderRepository, OrderDetailReposiroty orderDetailReposiroty) {
        this.orderRepository = orderRepository;
        this.orderDetailReposiroty = orderDetailReposiroty;
    }

    public List<Order> getAll() {
        return this.orderRepository.findAll();
    }

    public long coundAll() {
        return this.orderRepository.count();
    }

    public Page<Order> findAll(Pageable pageable) {
        return this.orderRepository.findAll(pageable);
    }

    public Order findById(long id) {
        return this.orderRepository.findById(id).get();
    }

    public void deleteByList(List<Long> list) {
        if (list.size() > 1) {
            if (list.contains(0L)) {
                this.orderDetailReposiroty.deleteAll();
                this.orderRepository.deleteAll();
            }

            else
                for (Long id : list)
                    if (id != -1) {
                        this.orderDetailReposiroty.deleteByOrder(this.orderRepository.findById(id).get());
                    }
            this.orderRepository.deleteByIdIn(list);
        }
    }

    public Order save(Order order) {
        return this.orderRepository.save(order);
    }
}

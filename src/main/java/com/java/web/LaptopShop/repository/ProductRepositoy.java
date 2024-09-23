package com.java.web.LaptopShop.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.java.web.LaptopShop.domain.Product;

public interface ProductRepositoy extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    void deleteByIdIn(List<Long> list);

    Page<Product> findAll(Specification<Product> productSpec, Pageable pageable);
}

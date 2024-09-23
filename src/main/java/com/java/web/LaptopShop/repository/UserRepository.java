package com.java.web.LaptopShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

import com.java.web.LaptopShop.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    void deleteByIdIn(List<Long> list);

    User findByEmail(String email);

    boolean existsByEmail(String email);
}

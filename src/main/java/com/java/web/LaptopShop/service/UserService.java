package com.java.web.LaptopShop.service;

import com.java.web.LaptopShop.domain.User;
import com.java.web.LaptopShop.repository.RoleRepository;
import com.java.web.LaptopShop.repository.UserRepository;
import java.util.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public Page<User> findAll(Pageable pageable) {
        return this.userRepository.findAll(pageable);
    }

    public void saveUser(User user) {
        this.userRepository.save(user);
    }

    public User findById(long id) {
        return this.userRepository.findById(id).get();
    }

    public void deleteById(long id) {
        this.userRepository.deleteById(id);
    }

    public void deleteByList(List<Long> list) {
        if (list.size() != 1) {
            if (list.contains(0))
                this.userRepository.deleteAll();
            else
                this.userRepository.deleteByIdIn(list);
        }
    }

    public long coundUser() {
        return this.userRepository.count();
    }

    public User getByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public boolean checkEmailExist(String email) {
        return this.userRepository.existsByEmail(email);
    }
}

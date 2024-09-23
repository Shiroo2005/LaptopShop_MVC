package com.java.web.LaptopShop.service;

import org.springframework.stereotype.Service;

import com.java.web.LaptopShop.domain.Role;
import com.java.web.LaptopShop.repository.RoleRepository;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findByName(String name) {
        return this.roleRepository.findByName(name);
    }
}

package com.java.web.LaptopShop.controller.admin;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.java.web.LaptopShop.domain.User;
import com.java.web.LaptopShop.service.RoleService;
import com.java.web.LaptopShop.service.UploadService;
import com.java.web.LaptopShop.service.UserService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Controller
public class UserController {
    private final UserService userService;
    private PasswordEncoder passwordEncoder;
    private final UploadService uploadService;
    private final RoleService roleService;

    public UserController(UserService userService, UploadService uploadService,
            RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.uploadService = uploadService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/admin/user/create")
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @PostMapping("/admin/user/create")
    public String postCreateUser(Model model, @ModelAttribute("newUser") @Valid User user,
            BindingResult userBindingResult, @RequestParam("avatarFile") MultipartFile file) {
        if (userBindingResult.hasErrors()) {
            return "admin/user/create";
        }

        if (this.userService.checkEmailExist(user.getEmail())) {
            userBindingResult.rejectValue("email", "error.email", "Email đã tồn tại.");
            return "admin/user/create";
        }

        user.setAvatar(this.uploadService.handleSaveUploadFile(file, "avatar/"));
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        user.setRole(this.roleService.findByName(user.getRole().getName()));
        this.userService.saveUser(user);
        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user/{id}")
    public String getUserDetailPage(Model model, @PathVariable("id") long id) {
        User user = this.userService.findById(id);
        model.addAttribute("user", user);
        return "admin/user/detail";
    }

    @GetMapping("/admin/user/update/{id}")
    public String getUserUpdatePage(Model model, @PathVariable("id") long id) {
        User user = this.userService.findById(id);
        model.addAttribute("user", user);
        return "admin/user/update";
    }

    @PostMapping("/admin/user/update")
    public String postUserUpdatePage(Model model, @ModelAttribute("user") @Valid User user,
            BindingResult userBindingResult, @RequestParam("avatarFile") MultipartFile file) {
        if (userBindingResult.hasErrors()) {
            return "admin/user/update";
        }
        String img = this.uploadService.handleSaveUploadFile(file, "avatar/");
        if (img != null && !img.isEmpty()) {
            user.setAvatar(img);
        } else
            user.setAvatar(this.userService.findById(user.getId()).getAvatar());
        user.setRole(this.roleService.findByName(user.getRole().getName()));
        this.userService.saveUser(user);
        return "redirect:/admin/user";
    }

    @Transactional
    @PostMapping("/admin/user/delete")
    public String postUserDeletePage(@RequestParam("selectDel") List<Long> list) {
        this.userService.deleteByList(list);
        return "redirect:/admin/user";
    }
}

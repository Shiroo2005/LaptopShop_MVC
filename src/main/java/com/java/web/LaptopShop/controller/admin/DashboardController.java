package com.java.web.LaptopShop.controller.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.web.LaptopShop.domain.Cart;
import com.java.web.LaptopShop.domain.CartDetail;
import com.java.web.LaptopShop.domain.Order;
import com.java.web.LaptopShop.domain.Product;
import com.java.web.LaptopShop.domain.User;
import com.java.web.LaptopShop.service.CartDetailService;
import com.java.web.LaptopShop.service.CartService;
import com.java.web.LaptopShop.service.OrderService;
import com.java.web.LaptopShop.service.ProductService;
import com.java.web.LaptopShop.service.UserService;
import java.util.*;

@Controller
public class DashboardController {
    private final UserService userService;
    private final ProductService productService;
    private final CartDetailService cartDetailService;
    private final OrderService orderService;

    public DashboardController(OrderService orderService, CartDetailService cartDetailService, UserService userService,
            ProductService productService) {
        this.userService = userService;
        this.productService = productService;
        this.cartDetailService = cartDetailService;
        this.orderService = orderService;
    }

    @GetMapping("/admin")
    public String getDashboardPage(Model model) {
        model.addAttribute("userCounts", this.userService.coundUser());
        model.addAttribute("productCounts", this.productService.countProduct());
        model.addAttribute("orderCounts", this.orderService.coundAll());
        return "admin/dashboard/show";
    }

    @GetMapping("/admin/user")
    public String getUserPage(Model model, @RequestParam("page") Optional<String> pageOptional) {
        int page = 1;
        try {
            if (pageOptional.isPresent()) {
                page = Integer.parseInt(pageOptional.get());
                if ((this.userService.coundUser() + 4) / 5 < page || page < 1)
                    return "error/not-found";
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        Pageable pageable = PageRequest.of(page - 1, 5);
        Page<User> userPage = this.userService.findAll(pageable);
        List<User> users = userPage.getContent();
        model.addAttribute("users", users);
        model.addAttribute("totalPages", userPage.getTotalPages());
        model.addAttribute("currentPage", page);
        return "admin/user/show";
    }

    @GetMapping("/admin/order")
    public String getOrderPage(Model model, @RequestParam("page") Optional<String> pageOptional) {
        int page = 1;
        if (pageOptional.isPresent()) {
            try {
                page = Integer.parseInt(pageOptional.get());
                if ((this.orderService.coundAll() + 4) / 5 < page || page < 1)
                    return "error/not-found";
            } catch (Exception e) {
                // TODO: handle exception
            }
        }

        Pageable pageable = PageRequest.of(page - 1, 5);
        Page<Order> orderPage = this.orderService.findAll(pageable);
        List<Order> orders = orderPage.getContent();
        model.addAttribute("orders", orders);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", orderPage.getTotalPages());
        return "admin/order/show";
    }

    @GetMapping("/admin/product")
    public String getProductPage(Model model, @RequestParam("page") Optional<String> pageOptional) {
        int page = 1;
        try {
            if (pageOptional.isPresent()) {
                page = Integer.parseInt(pageOptional.get());
                if ((this.productService.countProduct() + 4) / 5 < page || page < 1)
                    return "error/not-found";
            }
        } catch (Exception e) {

        }

        Pageable pageable = PageRequest.of(page - 1, 5);
        Page<Product> productPage = this.productService.findAll(pageable);
        List<Product> products = productPage.getContent();
        model.addAttribute("products", products);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        return "admin/product/show";
    }

}

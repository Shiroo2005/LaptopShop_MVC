package com.java.web.LaptopShop.controller.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Pageable;

import com.java.web.LaptopShop.domain.CartDetail;
import com.java.web.LaptopShop.domain.Product;
import com.java.web.LaptopShop.domain.User;
import com.java.web.LaptopShop.domain.DTO.FilterProductDTO;
import com.java.web.LaptopShop.domain.DTO.RegisterDTO;
import com.java.web.LaptopShop.service.CartDetailService;
import com.java.web.LaptopShop.service.ProductService;
import com.java.web.LaptopShop.service.RoleService;
import com.java.web.LaptopShop.service.UploadService;
import com.java.web.LaptopShop.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import java.util.*;

@Controller
public class HomepageController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final ProductService productService;
    private final RoleService roleService;
    private final CartDetailService cartDetailService;

    public HomepageController(CartDetailService cartDetailService, ProductService productService,
            UserService userService, PasswordEncoder passwordEncoder,
            RoleService roleService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.productService = productService;
        this.cartDetailService = cartDetailService;
    }

    @GetMapping("/")
    public String getHomepage(Model model) {
        List<Product> products = this.productService.findAll();
        model.addAttribute("products", products);
        return "client/homepage/show";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "client/auth/login";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("newRegisterDTO", new RegisterDTO());
        return "client/auth/register";
    }

    @PostMapping("/register")
    public String postRegister(@ModelAttribute("newRegisterDTO") @Valid RegisterDTO registerDTO,
            BindingResult newRegisterDTOBindingResult) {
        if (newRegisterDTOBindingResult.hasErrors()) {
            return "client/auth/register";
        }
        User user = registerDTO.toUser();
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        user.setRole(this.roleService.findByName("USER"));
        this.userService.saveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/product")
    public String getProductPage(Model model,
            @RequestParam("page") Optional<String> pageOptional,
            FilterProductDTO filterProductDTO, HttpServletRequest request) {
        int page = 1;
        try {
            if (pageOptional.isPresent()) {
                page = Integer.parseInt(pageOptional.get());
                if ((this.productService.countProduct() + 4) / 5 < page || page < 1)
                    return "error/not-found";
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        Pageable pageable = PageRequest.of(page - 1, 5);
        Page<Product> productPage = this.productService.findAll(pageable, filterProductDTO);
        List<Product> products = productPage.getContent();

        String queryString = request.getQueryString();
        if (queryString != null && !queryString.isEmpty()) {
            queryString = queryString.replace("page=" + page, "");
        }

        model.addAttribute("products", products);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("queryString", queryString);
        return "client/homepage/product";
    }

    @GetMapping("/cart")
    public String getCartPage(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User user = this.userService.findById((long) session.getAttribute("id"));
        List<CartDetail> cartDetails = this.cartDetailService.findByCart(user.getCart());
        model.addAttribute("cartDetails", cartDetails);
        model.addAttribute("cart", user.getCart());
        model.addAttribute("totalPrice", this.cartDetailService.totalPrice(cartDetails));
        return "client/cart/show";
    }

    @GetMapping("/access-deny")
    public String getDenyPage() {
        return "client/auth/deny";
    }
}

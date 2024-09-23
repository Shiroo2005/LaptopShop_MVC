package com.java.web.LaptopShop.controller.user;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.java.web.LaptopShop.domain.Cart;
import com.java.web.LaptopShop.domain.CartDetail;
import com.java.web.LaptopShop.domain.Product;
import com.java.web.LaptopShop.domain.User;
import com.java.web.LaptopShop.domain.DTO.FilterProductDTO;
import com.java.web.LaptopShop.domain.DTO.ReceiverDTO;
import com.java.web.LaptopShop.service.CartDetailService;
import com.java.web.LaptopShop.service.CartService;
import com.java.web.LaptopShop.service.ProductService;
import com.java.web.LaptopShop.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ItemController {
    private final ProductService productService;
    private final CartDetailService cartDetailService;
    private final UserService userService;

    public ItemController(UserService userService, ProductService productService, CartDetailService cartDetailService) {
        this.productService = productService;
        this.cartDetailService = cartDetailService;
        this.userService = userService;
    }

    @PostMapping("/add-product-to-cart/{id}")
    public String postAddProductToCart(@PathVariable("id") long productId,
            HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        this.productService.handleAddProductToCart(session, productId, 1);
        return "redirect:/";
    }

    @GetMapping("/product/{id}")
    public String getProductDetailHomepage(Model model, @PathVariable("id") long id) {
        model.addAttribute("product", this.productService.findById(id));
        return "client/product/detail";
    }

    @PostMapping("add-product-from-view-detail")
    public String postMethodName(@ModelAttribute("product") Product product, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        this.productService.handleAddProductToCart(session, product.getId(), 1);
        return "redirect:/";
    }

    @PostMapping("/confirm-checkout")
    public String postConfirmPage(Model model, @ModelAttribute("cart") Cart cart) {
        List<CartDetail> cartDetails = cartDetailService.getAfterConfirm(cart);
        model.addAttribute("cartDetails", cartDetails);
        return "client/cart/checkout";
    }

    @Transactional
    @PostMapping("/place-order")
    public String getOrderPage(@ModelAttribute("ReceiverDTO") ReceiverDTO receiverDTO,
            HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User user = this.userService.findById((long) session.getAttribute("id"));
        Cart cart = user.getCart();
        cart = receiverDTO.addReceiverInform(cart);

        this.productService.handleOrder(session, user);
        return "client/cart/thanks";
    }

    @PostMapping("/cart/delete-item/{id}")
    public String postDeleteCartDetail(@PathVariable("id") long id, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        cartDetailService.handleDeleteById(id, session);
        return "redirect:/cart";
    }
}

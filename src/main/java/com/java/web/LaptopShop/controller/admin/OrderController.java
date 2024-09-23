package com.java.web.LaptopShop.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.java.web.LaptopShop.domain.Order;
import com.java.web.LaptopShop.service.OrderService;

import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/admin/order/{id}")
    public String getOrderDetailPage(Model model, @PathVariable("id") long id) {
        Order order = this.orderService.findById(id);
        model.addAttribute("order", order);
        model.addAttribute("orderDetails", order.getOrderDetails());
        return "admin/order/detail";
    }

    @Transactional
    @PostMapping("/admin/order/delete")
    public String postDeleteOrderPage(@RequestParam("selectDel") List<Long> list) {
        this.orderService.deleteByList(list);
        return "redirect:/admin/order";
    }

    @GetMapping("/admin/order/update/{id}")
    public String getUpdateOrderPage(Model model, @PathVariable("id") long id) {
        model.addAttribute("order", this.orderService.findById(id));
        return "admin/order/update";
    }

    @PostMapping("/admin/order/update")
    public String postUpdateOrderPage(@ModelAttribute("order") Order order) {
        this.orderService.save(order);
        return "redirect:/admin/order";
    }
}

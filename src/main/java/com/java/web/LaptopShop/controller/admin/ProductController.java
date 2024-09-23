package com.java.web.LaptopShop.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.java.web.LaptopShop.domain.Product;
import com.java.web.LaptopShop.service.ProductService;
import com.java.web.LaptopShop.service.UploadService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Controller
public class ProductController {
    private ProductService productService;
    private UploadService uploadService;

    public ProductController(ProductService productService, UploadService uploadService) {
        this.productService = productService;
        this.uploadService = uploadService;
    }

    @GetMapping("/admin/product/{id}")
    public String getDetailProductPage(@PathVariable("id") long id, Model model) {
        model.addAttribute("product", this.productService.findById(id));
        return "admin/product/detail";
    }

    @GetMapping("/admin/product/update/{id}")
    public String getUpdateProductPage(@PathVariable("id") long id, Model model) {
        model.addAttribute("product", this.productService.findById(id));
        return "admin/product/update";
    }

    @PostMapping("/admin/product/update")
    public String postUpdateProductPage(@ModelAttribute("product") @Valid Product product,
            BindingResult producBindingResult, @RequestParam("avatarFile") MultipartFile file) {
        if (producBindingResult.hasErrors())
            return "admin/product/update";
        String img = this.uploadService.handleSaveUploadFile(file, "product/");
        if (img == null || img.isEmpty()) {
            product.setImage(this.productService.findById(product.getId()).getImage());
        } else
            product.setImage(img);

        this.productService.save(product);
        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/create")
    public String getCreateProduct(Model model) {
        model.addAttribute("newProduct", new Product());
        return "admin/product/create";
    }

    @PostMapping("/admin/product/create")
    public String getCreateProduct(@ModelAttribute("newProduct") @Valid Product product,
            BindingResult producBindingResult, @RequestParam("avatarFile") MultipartFile file) {
        if (producBindingResult.hasErrors()) {
            return "admin/product/create";
        }
        String img = this.uploadService.handleSaveUploadFile(file, "product/");
        product.setImage(img);
        this.productService.save(product);
        return "redirect:/admin/product";
    }

    @Transactional
    @PostMapping("/admin/product/delete")
    public String postDeleteProduct(@RequestParam("selectDel") List<Long> list) {
        this.productService.deleteByList(list);
        return "redirect:/admin/product";
    }
}

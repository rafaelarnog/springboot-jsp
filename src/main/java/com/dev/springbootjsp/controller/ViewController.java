package com.dev.springbootjsp.controller;

import com.dev.springbootjsp.domain.ProductRequest;
import com.dev.springbootjsp.domain.ProductResponse;
import com.dev.springbootjsp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ComponentScan
@Controller
public class ViewController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String listProducts(Model model){
        List<ProductResponse> products = productService.listProducts();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/add-product")
    public String showProductRegistrationScreen(Model model){
        model.addAttribute("create", true);
        return "save-product";
    }

    @GetMapping("/show-product/{id}")
    public String showProductScreen(@PathVariable Long id, Model model){
        ProductResponse productResponse = productService.getProduct(id);
        model.addAttribute("product", productResponse);
        return "show-product";
    }

    @GetMapping("/edit-product/{id}")
    public String showProductEditScreen(@PathVariable Long id, Model model){
        ProductResponse productResponse = productService.getProduct(id);
        model.addAttribute("product", productResponse);
        model.addAttribute("create", false);
        return "save-product";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/";
    }

    @PostMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id,
                              @RequestBody ProductRequest productRequest,
                              Model model) {
        ProductResponse productResponse = productService.updateProduct(id, productRequest);
        model.addAttribute("product", productResponse);
        return "redirect:/";
    }

    @PostMapping("/add")
    public String addProduct(@RequestBody ProductRequest productRequest){
        productService.createProduct(productRequest);
        return "redirect:/";
    }
}

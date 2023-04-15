package com.dev.springbootjsp.controller;

import com.dev.springbootjsp.domain.ProductRequest;
import com.dev.springbootjsp.domain.ProductResponse;
import com.dev.springbootjsp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok().body(productService.createProduct(productRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok().body(productService.getProduct(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> listProducts(){
        return ResponseEntity.ok().body(productService.listProducts());
    }
}

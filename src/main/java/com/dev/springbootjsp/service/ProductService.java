package com.dev.springbootjsp.service;

import com.dev.springbootjsp.domain.ProductRequest;
import com.dev.springbootjsp.domain.ProductResponse;
import com.dev.springbootjsp.entity.Product;
import com.dev.springbootjsp.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = modelMapper.map(productRequest, Product.class);
        return modelMapper.map(productRepository.save(product), ProductResponse.class);
    }

    public ProductResponse getProduct(Long id) {
        return modelMapper.map(productRepository.getById(id),ProductResponse.class);
    }

    public List<ProductResponse> listProducts() {
        return productRepository.findAll().stream()
                .map(result -> modelMapper.map(result, ProductResponse.class))
                .collect(Collectors.toList());
    }
}

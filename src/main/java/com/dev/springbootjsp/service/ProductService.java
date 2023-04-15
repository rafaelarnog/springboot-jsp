package com.dev.springbootjsp.service;

import com.dev.springbootjsp.domain.ProductRequest;
import com.dev.springbootjsp.domain.ProductResponse;
import com.dev.springbootjsp.entity.Product;
import com.dev.springbootjsp.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        return modelMapper.map(productRepository.findById(id)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND)),
                ProductResponse.class);
    }

    public List<ProductResponse> listProducts() {
        return productRepository.findAll().stream()
                .map(result -> modelMapper.map(result, ProductResponse.class))
                .collect(Collectors.toList());
    }

    public ProductResponse updateProduct(Long id, ProductRequest productRequest) {
        Product product = modelMapper.map(productRequest, Product.class);
        product.setId(id);
        return modelMapper.map(productRepository.save(product), ProductResponse.class);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}

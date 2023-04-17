package com.dev.springbootjsp.service;

import com.dev.springbootjsp.builder.ProductBuilder;
import com.dev.springbootjsp.domain.ProductRequest;
import com.dev.springbootjsp.domain.ProductResponse;
import com.dev.springbootjsp.entity.Product;
import com.dev.springbootjsp.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ModelMapper modelMapper;

    private ProductResponse productResponse;

    private ProductRequest productRequest;

    private Product product;

    private Product productSave;

    @BeforeEach
    public void init() {
        this.productResponse = ProductBuilder.buildProductResponse();
        this.productRequest = ProductBuilder.buildProductRequest();
        this.product = ProductBuilder.buildProduct();
        this.productSave = ProductBuilder.buildSavedProduct();
    }

    @Test
    public void shouldCreateProduct() {
        when(modelMapper.map(productRequest, Product.class)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(productSave);
        when(modelMapper.map(productSave, ProductResponse.class)).thenReturn(productResponse);
        ProductResponse productResponseResult = productService.createProduct(productRequest);

        Assertions.assertEquals(productResponseResult, productResponse);
    }

    @Test
    public void shouldGetProduct(){
        when(productRepository.findById(Long.valueOf(1))).thenReturn(Optional.of(productSave));
        when(modelMapper.map(productSave, ProductResponse.class)).thenReturn(productResponse);
        ProductResponse productResponseResult = productService.getProduct(Long.valueOf(1));

        Assertions.assertEquals(productResponseResult, productResponse);
    }

    @Test
    public void shouldListProducts() {
        when(productRepository.findAll()).thenReturn(List.of(productSave));
        when(modelMapper.map(productSave, ProductResponse.class)).thenReturn(productResponse);

        List<ProductResponse> productResponses = productService.listProducts();

        Assertions.assertEquals(productResponses, List.of(productResponse));
    }


    @Test
    public void shouldUpdateProduct() {
        when(modelMapper.map(productRequest, Product.class)).thenReturn(product);
        when(productRepository.save(any())).thenReturn(productSave);
        when(modelMapper.map(productSave, ProductResponse.class)).thenReturn(productResponse);
        ProductResponse productResponseResult = productService.updateProduct(Long.valueOf(1), productRequest);

        Assertions.assertEquals(productResponseResult, productResponse);
    }

    @Test
    public void shouldDeleteProduct() {
        doNothing().when(productRepository).deleteById(anyLong());
        productService.deleteProduct(Long.valueOf(1));
        verify(productRepository, times(1)).deleteById(Long.valueOf(1));
    }

}

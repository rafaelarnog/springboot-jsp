package com.dev.springbootjsp.builder;

import com.dev.springbootjsp.domain.ProductRequest;
import com.dev.springbootjsp.domain.ProductResponse;
import com.dev.springbootjsp.entity.Product;

import java.math.BigDecimal;

public class ProductBuilder {

    public static ProductRequest buildProductRequest() {
        ProductRequest product = new ProductRequest();
        product.setName("Produto 1");
        product.setValue(new BigDecimal(100));
        product.setDescription("Descrição do produto 1");
        product.setDiscount(new BigDecimal(10));
        return product;
    }

    public static ProductResponse buildProductResponse() {
        ProductResponse product = new ProductResponse();
        product.setId(Long.valueOf(1));
        product.setName("Produto 1");
        product.setValue(new BigDecimal(100));
        product.setDescription("Descrição do produto 1");
        product.setDiscount(new BigDecimal(10));
        product.setDiscountedValue(new BigDecimal(90));
        return product;
    }

    public static Product buildProduct(){
        Product product = new Product();
        product.setName("Produto 1");
        product.setValue(new BigDecimal(100));
        product.setDescription("Descrição do produto 1");
        product.setDiscount(new BigDecimal(10));
        return product;
    }

    public static Product buildSavedProduct(){
        Product product = new Product();
        product.setId(Long.valueOf(1));
        product.setName("Produto 1");
        product.setValue(new BigDecimal(100));
        product.setDescription("Descrição do produto 1");
        product.setDiscount(new BigDecimal(10));
        return product;
    }
}

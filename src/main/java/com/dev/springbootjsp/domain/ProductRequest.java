package com.dev.springbootjsp.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest {
    private String name;
    private BigDecimal value;
    private String description;
    private BigDecimal discount;
}

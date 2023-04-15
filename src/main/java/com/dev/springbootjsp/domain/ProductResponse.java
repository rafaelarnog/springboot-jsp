package com.dev.springbootjsp.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductResponse {
    private Long id;
    private String name;
    private BigDecimal value;
    private String description;
    private BigDecimal discount;
    private BigDecimal discountedValue;
}

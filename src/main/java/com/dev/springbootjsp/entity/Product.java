package com.dev.springbootjsp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

import java.math.BigDecimal;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private BigDecimal value;
    private String description;
    private BigDecimal discount;
    @Transient
    private BigDecimal discountedValue;
}

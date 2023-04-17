package com.dev.springbootjsp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private BigDecimal value;
    private String description;
    private BigDecimal discount;
    @Transient
    private BigDecimal discountedValue;

    public BigDecimal getDiscountedValue() {
        return value.subtract((value.multiply(discount.divide(BigDecimal.valueOf(100))))).setScale(2);
    }
}

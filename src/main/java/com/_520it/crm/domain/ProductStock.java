package com._520it.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductStock {
    private Long id;

    private Long salePrice;

    private Long vsalePrice;

    private BigDecimal storeNumber;

    private String category;

    private String specification;

    private BigDecimal totalPrice;

    private String state;

    private Product product;
    private BigDecimal tsNumber;


}
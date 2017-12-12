package com._520it.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class Product {
    private Long id;


    private String sn;

    private String name;

    private String allergen;

    private String ingredient;

    private String remark;

    private String image;

    private Byte state;

    private Long categoryId;

    private String categoryName;

    private Long brandId;

    private String brandName;

    private Long originId;

    private String originName;

    private BigDecimal salePrice;

    private BigDecimal vsalePrice;

    private Long supplierId;

    private String supplierName;


    private String specification;



}
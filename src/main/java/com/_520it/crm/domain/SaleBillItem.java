package com._520it.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter@Setter
public class SaleBillItem {
    private Long id;

    private BigDecimal originalprice;

    private BigDecimal discount;

    private BigDecimal realprice;

    private BigDecimal number;

    private BigDecimal amount;

    private Product product;

    private Long productStockId;

    private Long billId;
    private byte state;
    private String salemanName;

    public BigDecimal getReleasePrice() {
        return realprice.subtract(originalprice);
    }
}
package com._520it.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class VipLevel {
    private Long id;
    private String name;
    private BigDecimal serveDiscount;
    private BigDecimal productDiscount;
    private int enable;

}



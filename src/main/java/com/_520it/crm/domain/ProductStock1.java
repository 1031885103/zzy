package com._520it.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductStock1 extends BaseDomain {
    private BigDecimal salePrice;//销售价

    private BigDecimal vsalePrice;//会员价

    private BigDecimal bulkNumber;//散装库存

    private BigDecimal storeNumber;//整装库存

    private String category;

    private String specification;//单位

    private BigDecimal totalPrice;//库存结余

    private String state;//状态

    private Integer warningNumber;//预警数量

    private Product product;//商品

}
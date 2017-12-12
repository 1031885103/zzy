package com._520it.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class ProductStockItem extends BaseDomain {
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date vdate;

    private BigDecimal costPrice;

    private BigDecimal amount;

    private String specification;//商品规格

    private BigDecimal totalPrice;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date warningDate;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date safeDate;

    private String employeeName;

    private Product product;

}
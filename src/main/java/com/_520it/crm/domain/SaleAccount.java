package com._520it.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

//销售账,记录销售相关的数据
@Getter
@Setter
public class SaleAccount {
    private Long id;
    private Date vdate;//业务时间
    private BigDecimal saleNumber;//销售数量
    private BigDecimal number;//销售数量
    private BigDecimal costPrice;//成本价格
    private BigDecimal costAmount;//成本总额
    private BigDecimal salePrice;//销售价格
    private BigDecimal saleAmount;//销售总额
    private Product product;//产品
    private Employee saleman;//销售人员
    private Employee client;//客户
}

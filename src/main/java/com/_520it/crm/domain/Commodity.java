package com._520it.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class Commodity {
    public static final int START_OK = 0;//正常
    public static final int START_BAD = 1;//不正常

    public static final int START_YES=0;//是
    public static final int START_NO=1;//否

    private Long id;

    private String sn;//编号

    private String name;//名称

    private String allergen;//过敏

    private String image;//图片

    private String remark;//备注

    private Byte state = START_OK;//状态

    private ProductType category;//类别

    private ProductBrand brand;//品牌

    private ProductManufacturer origin;//产地

    private BigDecimal salePrice;//价格

    private String ingredient;//原料

    private int display = START_YES;

}
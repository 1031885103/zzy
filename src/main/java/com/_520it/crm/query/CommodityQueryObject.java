package com._520it.crm.query;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommodityQueryObject extends QueryObject{
    private String keyword1;
    private String keyword2;
    private String categoryName;//类别
    private String brandName;//品牌
}

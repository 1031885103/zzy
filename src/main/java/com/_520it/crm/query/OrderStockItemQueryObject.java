package com._520it.crm.query;


import com.alibaba.druid.util.StringUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderStockItemQueryObject extends QueryObject {
    private String productSn;
    private String productName;
    private String subbranchName;

    public String getProductSn() {
        return StringUtils.isEmpty(productSn) ? null : productSn;
    }

    public String getProductName() {
        return StringUtils.isEmpty(productName) ? null : productName;
    }

    public String getSubbranchName() {
        return StringUtils.isEmpty(subbranchName) ? null : subbranchName;
    }
}

package com._520it.crm.query;


import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

@Getter
@Setter
public class ProductStockItemQueryObject extends QueryObject {
    private String productSn;
    private String productName;

    public String getProductSn() {
        return StringUtils.isEmpty(productSn) ? null : productSn;
    }

    public String getProductName() {
        return StringUtils.isEmpty(productName) ? null : productName;
    }
}

package com._520it.crm.service;

import com._520it.crm.domain.ProductStockItem;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.ProductStockItemQueryObject;

import java.util.List;

public interface IProductStockItemService {
    void deleteByPrimaryKey(Long id);

    void insert(ProductStockItem record);

    ProductStockItem selectByPrimaryKey(Long id);

    List<ProductStockItem> selectAll();

    void updateByPrimaryKey(ProductStockItem record);

    PageResult queryPage(ProductStockItemQueryObject qo);

    PageResult queryByProductSn(ProductStockItemQueryObject qo);
}

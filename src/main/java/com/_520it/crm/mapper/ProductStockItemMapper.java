package com._520it.crm.mapper;

import com._520it.crm.domain.ProductStockItem;
import com._520it.crm.query.ProductStockItemQueryObject;

import java.util.List;

public interface ProductStockItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductStockItem record);

    ProductStockItem selectByPrimaryKey(Long id);

    List<ProductStockItem> selectAll();

    int updateByPrimaryKey(ProductStockItem record);

    List<ProductStockItem> queryPageData(ProductStockItemQueryObject qo);

    Long queryPageCount(ProductStockItemQueryObject qo);

    List<ProductStockItem> queryPageSnData(ProductStockItemQueryObject qo);

    Long queryPageSnCount(ProductStockItemQueryObject qo);
}
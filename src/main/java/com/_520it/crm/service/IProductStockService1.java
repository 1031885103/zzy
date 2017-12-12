package com._520it.crm.service;

import com._520it.crm.domain.ProductStock1;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.ProductStockQueryObject;

import java.util.List;

public interface IProductStockService1 {
    void deleteByPrimaryKey(Long id);

    void insert(ProductStock1 record);

    ProductStock1 selectByPrimaryKey(Long id);

    List<ProductStock1> selectAll();

    void updateByPrimaryKey(ProductStock1 record);

    PageResult queryPage(ProductStockQueryObject qo);

    PageResult  queryForStockWarning(ProductStockQueryObject qo);
}

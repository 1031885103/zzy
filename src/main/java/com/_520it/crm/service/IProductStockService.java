package com._520it.crm.service;

import com._520it.crm.domain.ProductStock;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

import java.util.List;

public interface IProductStockService {
	int deleteByPrimaryKey(Long id);
    int insert(ProductStock record);
    ProductStock selectByPrimaryKey(Long id);
    List<ProductStock> selectAll();
    int updateByPrimaryKey(ProductStock record);
	PageResult queryPage(QueryObject qo);

    ProductStock selectByproductStockId(Long productStockId);
}

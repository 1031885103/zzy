package com._520it.crm.mapper;

import com._520it.crm.domain.ProductStock;
import com._520it.crm.domain.ProductStock1;
import com._520it.crm.query.ProductStockQueryObject;

import java.util.List;

public interface ProductStockMapper1 {
    int deleteByPrimaryKey(Long id);

    int insert(ProductStock1 record);

    ProductStock1 selectByPrimaryKey(Long id);

    List<ProductStock1> selectAll();

    int updateByPrimaryKey(ProductStock1 record);

    Long queryPageCount(ProductStockQueryObject qo);

    List<ProductStock1> queryPageData(ProductStockQueryObject qo);

    ProductStock1 selectProductId(Long productId);

    void updateByOrderProduct(ProductStock1 ps);

    Long queryForStockCount(ProductStockQueryObject qo);

    List<ProductStock> queryForStockData(ProductStockQueryObject qo);
}
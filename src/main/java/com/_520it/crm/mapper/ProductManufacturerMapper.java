package com._520it.crm.mapper;

import com._520it.crm.domain.ProductManufacturer;
import com._520it.crm.query.ProductManufacturerQueryObject;

import java.util.List;

public interface ProductManufacturerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductManufacturer record);

    ProductManufacturer selectByPrimaryKey(Long id);

    List<ProductManufacturer> selectAll();

    int updateByPrimaryKey(ProductManufacturer record);

    long queryCount(ProductManufacturerQueryObject qo);

    List<ProductManufacturer> queryData(ProductManufacturerQueryObject qo);
}
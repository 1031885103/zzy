package com._520it.crm.mapper;

import com._520it.crm.domain.ProductBrand;
import com._520it.crm.query.ProductBrandQueryObject;

import java.util.List;

public interface ProductBrandMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductBrand record);

    ProductBrand selectByPrimaryKey(Long id);

    List<ProductBrand> selectAll();

    int updateByPrimaryKey(ProductBrand record);

    long queryCount(ProductBrandQueryObject qo);

    List<ProductBrand> queryData(ProductBrandQueryObject qo);
}
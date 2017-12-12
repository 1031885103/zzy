package com._520it.crm.mapper;

import com._520it.crm.domain.ProductElement;
import com._520it.crm.query.ProductElementQueryObject;

import java.util.List;

public interface ProductElementMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductElement record);

    ProductElement selectByPrimaryKey(Long id);

    List<ProductElement> selectAll();

    int updateByPrimaryKey(ProductElement record);

    long queryCount(ProductElementQueryObject qo);

    List<ProductElement> queryData(ProductElementQueryObject qo);
}
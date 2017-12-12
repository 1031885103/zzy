package com._520it.crm.mapper;

import com._520it.crm.domain.ProductType;
import com._520it.crm.query.ProductTypeQueryObject;


import java.util.List;

public interface ProductTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductType record);

    ProductType selectByPrimaryKey(Long id);

    List<ProductType> selectAll();

    int updateByPrimaryKey(ProductType record);


    List<ProductType> queryData(ProductTypeQueryObject qo);

    long queryCount(ProductTypeQueryObject qo);
}
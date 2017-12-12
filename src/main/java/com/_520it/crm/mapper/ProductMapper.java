package com._520it.crm.mapper;

import com._520it.crm.domain.Product;

import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Product record);

    Product selectByPrimaryKey(Long id);

    Product selectByProductName(String name);


    List<Product> selectAll();

    int updateByPrimaryKey(Product record);


    List<Product> queryProductByProductSn(String productSn);
    //List<Product> queryProductByProductSn();

    List<Product> queryProductByProductName(String productName);

    List<Product> queryProduct();

}
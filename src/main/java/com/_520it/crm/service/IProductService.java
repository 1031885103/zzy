package com._520it.crm.service;

import com._520it.crm.domain.Product;


import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

import java.util.List;

public interface IProductService {

    void deleteByPrimaryKey(Long id);

    void insert(Product record);

    Product selectByPrimaryKey(Long id);

    List<Product> selectAll();

    List<Product> queryProductByProductSn(String productSn);
    //List<Product>  queryProductByProductSn();

    List<Product> queryProductByProductName(String productName);

    List<Product> queryProduct();


    Product selectByName(String name);


    int updateByPrimaryKey(Product record);

    PageResult queryPage(QueryObject qo);


}

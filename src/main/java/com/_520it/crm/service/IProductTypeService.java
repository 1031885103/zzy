package com._520it.crm.service;

import com._520it.crm.domain.ProductType;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.ProductTypeQueryObject;

import java.util.List;

public interface IProductTypeService {
    
    void save(ProductType p);
    
    void update(ProductType p);
    
    void delete(Long id);
    
    ProductType get(Long id);
    
    List<ProductType> list();

    PageResult queryPage(ProductTypeQueryObject qo);
}

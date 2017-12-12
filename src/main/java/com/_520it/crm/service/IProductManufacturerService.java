package com._520it.crm.service;

import com._520it.crm.domain.ProductManufacturer;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.ProductManufacturerQueryObject;

import java.util.List;

public interface IProductManufacturerService {
    
    void save(ProductManufacturer p);
    
    void update(ProductManufacturer p);
    
    void delete(Long id);
    
    ProductManufacturer get(Long id);
    
    List<ProductManufacturer> list();

    PageResult queryPage(ProductManufacturerQueryObject qo);
}

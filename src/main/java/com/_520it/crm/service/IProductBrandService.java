package com._520it.crm.service;

import com._520it.crm.domain.ProductBrand;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.ProductBrandQueryObject;

import java.util.List;

public interface IProductBrandService {
    
    void save(ProductBrand p);
    
    void update(ProductBrand p);
    
    void delete(Long id);
    
    ProductBrand get(Long id);
    
    List<ProductBrand> list();

    PageResult queryPage(ProductBrandQueryObject qo);
}

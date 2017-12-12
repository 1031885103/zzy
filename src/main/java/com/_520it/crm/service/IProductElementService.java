package com._520it.crm.service;

import com._520it.crm.domain.ProductElement;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.ProductElementQueryObject;

import java.util.List;

public interface IProductElementService {
    
    void save(ProductElement p);
    
    void update(ProductElement p);
    
    void delete(Long id);

    ProductElement get(Long id);
    
    List<ProductElement> list();

    PageResult queryPage(ProductElementQueryObject qo);
}

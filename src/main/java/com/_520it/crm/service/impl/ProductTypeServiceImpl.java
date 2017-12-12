package com._520it.crm.service.impl;

import com._520it.crm.domain.ProductType;
import com._520it.crm.mapper.ProductTypeMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.ProductTypeQueryObject;
import com._520it.crm.service.IProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProductTypeServiceImpl implements IProductTypeService {
    @Autowired
    private ProductTypeMapper productTypeMapper;
    @Override
    public void save(ProductType p) {
        if(p.getDisplay()==0){
            p.setDisplay(ProductType.START_YES);
        }else{
            p.setDisplay(ProductType.START_NO);
        }
        productTypeMapper.insert(p);
    }

    @Override
    public void update(ProductType p) {
        productTypeMapper.updateByPrimaryKey(p);
    }

    @Override
    public void delete(Long id) {
        productTypeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ProductType get(Long id) {
        return productTypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ProductType> list() {
        return productTypeMapper.selectAll();
    }

    @Override
    public PageResult queryPage(ProductTypeQueryObject qo) {
       long count = productTypeMapper.queryCount(qo);
       if(count<=0L){

           return new PageResult(0L, Collections.EMPTY_LIST);
           }
       List<ProductType> result = productTypeMapper.queryData(qo);
       return new PageResult(count,result);

    }
}

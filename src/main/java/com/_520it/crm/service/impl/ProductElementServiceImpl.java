package com._520it.crm.service.impl;

import com._520it.crm.domain.ProductElement;
import com._520it.crm.mapper.ProductElementMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.ProductElementQueryObject;
import com._520it.crm.service.IProductElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProductElementServiceImpl implements IProductElementService{
    @Autowired
    private ProductElementMapper elementMapper;
    @Override
    public void save(ProductElement p) {
        if (p.getDisplay() == 0) {
            p.setDisplay(ProductElement.START_YES);
        } else {
            p.setDisplay(ProductElement.START_NO);
        }
        elementMapper.insert(p);
    }

    @Override
    public void update(ProductElement p) {
        elementMapper.updateByPrimaryKey(p);
    }

    @Override
    public void delete(Long id) {
        elementMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ProductElement get(Long id) {
        return elementMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ProductElement> list() {
        return elementMapper.selectAll();
    }

    @Override
    public PageResult queryPage(ProductElementQueryObject qo) {
        long count = elementMapper.queryCount(qo);
        if(count<=0L){

            return new PageResult(0L, Collections.EMPTY_LIST);
            }
        List<ProductElement> result = elementMapper.queryData(qo);
        return new PageResult(count,result);
    }
}

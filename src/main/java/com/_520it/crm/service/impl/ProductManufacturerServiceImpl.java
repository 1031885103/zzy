package com._520it.crm.service.impl;

import com._520it.crm.domain.ProductManufacturer;
import com._520it.crm.mapper.ProductManufacturerMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.ProductManufacturerQueryObject;
import com._520it.crm.service.IProductManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProductManufacturerServiceImpl implements IProductManufacturerService {
    @Autowired
    private ProductManufacturerMapper productManufacturerMapper;

    @Override
    public void save(ProductManufacturer p) {
        if (p.getDisplay() == 0) {
            p.setDisplay(ProductManufacturer.START_YES);
        } else {
            p.setDisplay(ProductManufacturer.START_NO);
        }
        productManufacturerMapper.insert(p);
    }

    @Override
    public void update(ProductManufacturer p) {
        productManufacturerMapper.updateByPrimaryKey(p);
    }

    @Override
    public void delete(Long id) {
        productManufacturerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ProductManufacturer get(Long id) {
        return productManufacturerMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ProductManufacturer> list() {
        return productManufacturerMapper.selectAll();
    }

    @Override
    public PageResult queryPage(ProductManufacturerQueryObject qo) {
        long count = productManufacturerMapper.queryCount(qo);
        if (count <= 0L) {
            return new PageResult(0L, Collections.EMPTY_LIST);
        }
        List<ProductManufacturer> result = productManufacturerMapper.queryData(qo);
        return new PageResult(count, result);
    }
}

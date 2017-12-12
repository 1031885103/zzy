package com._520it.crm.service.impl;

import com._520it.crm.domain.ProductStock;
import com._520it.crm.domain.ProductStock1;
import com._520it.crm.mapper.ProductStockMapper1;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.ProductStockQueryObject;
import com._520it.crm.service.IProductStockService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProductStockServiceImpl1 implements IProductStockService1 {
    @Autowired
    private ProductStockMapper1 productStockMapper;

    public void deleteByPrimaryKey(Long id) {
        productStockMapper.deleteByPrimaryKey(id);
    }

    public void insert(ProductStock1 record) {
        productStockMapper.insert(record);
    }

    public ProductStock1 selectByPrimaryKey(Long id) {
        return productStockMapper.selectByPrimaryKey(id);
    }

    public List<ProductStock1> selectAll() {
        return productStockMapper.selectAll();
    }

    public void updateByPrimaryKey(ProductStock1 record) {
        productStockMapper.updateByPrimaryKey(record);

    }

    public PageResult queryPage(ProductStockQueryObject qo) {
        Long total = productStockMapper.queryPageCount(qo);
        if (total <= 0) {
            return new PageResult(total, Collections.EMPTY_LIST);
        }
        List<ProductStock1> rows = productStockMapper.queryPageData(qo);
        return new PageResult(total, rows);
    }

    public PageResult queryForStockWarning(ProductStockQueryObject qo) {
        Long total = productStockMapper.queryForStockCount(qo);
        if (total <= 0) {
            return new PageResult(total, Collections.EMPTY_LIST);
        }
        List<ProductStock> rows = productStockMapper.queryForStockData(qo);
        return new PageResult(total, rows);
    }
}

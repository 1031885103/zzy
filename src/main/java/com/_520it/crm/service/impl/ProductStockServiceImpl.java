package com._520it.crm.service.impl;

import com._520it.crm.domain.ProductStock;
import com._520it.crm.mapper.ProductStockMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.IProductStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProductStockServiceImpl implements IProductStockService {
    @Autowired
    private ProductStockMapper productStockMapper;


    public ProductStock selectByproductStockId(Long productStockId) {
        return productStockMapper.selectByproductStockId(productStockId);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return productStockMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ProductStock record) {
        return productStockMapper.insert(record);
    }

    @Override
    public ProductStock selectByPrimaryKey(Long id) {
        return productStockMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ProductStock> selectAll() {

        return productStockMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(ProductStock record) {
        return productStockMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult queryPage(QueryObject qo) {
        Long count = productStockMapper.queryPageCount(qo);
        if (count <= 0) {
            return new PageResult(0L, Collections.EMPTY_LIST);
        }
        List<ProductStock> result = productStockMapper.queryPageDataResult(qo);
        PageResult pageResult = new PageResult(count, result);
        return pageResult;
    }


}

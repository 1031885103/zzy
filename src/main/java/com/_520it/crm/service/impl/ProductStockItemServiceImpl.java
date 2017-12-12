package com._520it.crm.service.impl;

import com._520it.crm.domain.ProductStockItem;
import com._520it.crm.mapper.ProductStockItemMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.ProductStockItemQueryObject;
import com._520it.crm.service.IProductStockItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProductStockItemServiceImpl implements IProductStockItemService {
    @Autowired
    private ProductStockItemMapper productStockItemMapper;

    public void deleteByPrimaryKey(Long id) {
        productStockItemMapper.deleteByPrimaryKey(id);
    }

    public void insert(ProductStockItem record) {
        productStockItemMapper.insert(record);
    }

    public ProductStockItem selectByPrimaryKey(Long id) {
        return productStockItemMapper.selectByPrimaryKey(id);
    }

    public List<ProductStockItem> selectAll() {
        return productStockItemMapper.selectAll();
    }

    public void updateByPrimaryKey(ProductStockItem record) {
        productStockItemMapper.updateByPrimaryKey(record);

    }

    public PageResult queryPage(ProductStockItemQueryObject qo) {
        Long total = productStockItemMapper.queryPageCount(qo);
        if (total <= 0) {
            return new PageResult(total, Collections.EMPTY_LIST);
        }
        List<ProductStockItem> rows = productStockItemMapper.queryPageData(qo);
        return new PageResult(total, rows);
    }

    public PageResult queryByProductSn(ProductStockItemQueryObject qo) {
        Long total = productStockItemMapper.queryPageSnCount(qo);
        if (total <= 0) {
            return new PageResult(total, Collections.EMPTY_LIST);
        }
        List<ProductStockItem> rows = productStockItemMapper.queryPageSnData(qo);
        return new PageResult(total, rows);
    }
}

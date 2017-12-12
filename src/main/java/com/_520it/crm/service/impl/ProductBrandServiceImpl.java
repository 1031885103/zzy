package com._520it.crm.service.impl;

import com._520it.crm.domain.ProductBrand;
import com._520it.crm.mapper.ProductBrandMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.ProductBrandQueryObject;
import com._520it.crm.service.IProductBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProductBrandServiceImpl implements IProductBrandService {
    @Autowired
    private ProductBrandMapper productBrandMapper;

    @Override
    public void save(ProductBrand p) {
        if (p.getDisplay() == 0) {
            p.setDisplay(ProductBrand.START_YES);
        } else {
            p.setDisplay(ProductBrand.START_NO);
        }
        productBrandMapper.insert(p);
    }

    @Override
    public void update(ProductBrand p) {
        productBrandMapper.updateByPrimaryKey(p);
    }

    @Override
    public void delete(Long id) {
        productBrandMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ProductBrand get(Long id) {
        return productBrandMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ProductBrand> list() {
        return productBrandMapper.selectAll();
    }

    @Override
    public PageResult queryPage(ProductBrandQueryObject qo) {
        long count = productBrandMapper.queryCount(qo);
        if (count <= 0L) {
            return new PageResult(0L, Collections.EMPTY_LIST);
        }
        List<ProductBrand> result = productBrandMapper.queryData(qo);
        return new PageResult(count, result);
    }
}

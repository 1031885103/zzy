package com._520it.crm.service.impl;

import com._520it.crm.domain.Product;
import com._520it.crm.mapper.ProductMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;
    @Override
    public void deleteByPrimaryKey(Long id) {
        productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(Product record) {
        productMapper.insert(record);
    }


    public Product selectByPrimaryKey(Long id) {
        return productMapper.selectByPrimaryKey(id);
    }


    public List<Product> selectAll() {
        return productMapper.selectAll();
    }


    public List<Product> queryProductByProductSn(String productSn) {
        return productMapper.queryProductByProductSn(productSn);
    }

    /* public List<Product> queryProductByProductSn( ) {
         return productMapper.queryProductByProductSn();
     }*/
    public List<Product> queryProductByProductName(String productName) {
        return productMapper.queryProductByProductName(productName);
    }


    public List<Product> queryProduct() {
        return productMapper.queryProduct();
    }


    public int updateByPrimaryKey(Product record) {
        int count = productMapper.updateByPrimaryKey(record);

        return count;
    }

    @Override
    public PageResult queryPage(QueryObject qo) {
        return null;
    }


    public Product selectByName(String name) {
        return productMapper.selectByProductName(name);
    }

}

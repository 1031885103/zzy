package com._520it.crm.web.controller;

import com._520it.crm.domain.Product;
import com._520it.crm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("product")
public class ProductController {
    @Autowired
    private IProductService productService;
   /* @RequestMapping("queryProductByProductSn")
    @ResponseBody
    public Product queryProductByProductSn(String productSn) {
        if (productSn != null && !"".equals(productSn.trim())) {
            Product product = productService.queryProductByProductSn(productSn);
            return product;
        }
        return null;
    }

    @RequestMapping("queryProductByProductName")
    @ResponseBody
    public Product queryProductByProductName(String productName) {
        if (productName != null && !"".equals(productName.trim())) {
            Product product = productService.queryProductByProductName(productName);
            return product;
        }
        return null;
    }*/
    @RequestMapping("queryProductByProductName")
    @ResponseBody
    public Product queryProductByProductName(String productName) {
        if (productName != null && !"".equals(productName.trim())) {
            List<Product> products = productService.queryProductByProductName(productName);
            return products.get(0);
        }
        return null;
    }
    @RequestMapping("queryProductByProductSn")
    @ResponseBody
    public Product queryProductByProductSn(String productSn) {
        if (productSn != null && !"".equals(productSn.trim())) {
            List<Product> products = productService.queryProductByProductSn(productSn);
            return products.get(0);
        }
        return null;
    }
    @RequestMapping("queryProduct")
    @ResponseBody
    public List<Product> queryProduct() {
        List<Product> products = productService.queryProduct();
        return products;
    }
}

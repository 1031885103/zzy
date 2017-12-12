package com._520it.crm.web.controller;

import com._520it.crm.page.PageResult;
import com._520it.crm.query.ProductStockQueryObject;
import com._520it.crm.service.IProductStockService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("productStock1")
public class ProductStockController1 {
    @Autowired
    private IProductStockService1 productStockService;
    @RequestMapping("")
    public String index(){
        return "productStock";
    }


    @RequestMapping("list")
    @ResponseBody
    public PageResult list(ProductStockQueryObject qo){
        PageResult pageResult = productStockService.queryPage(qo);
        return pageResult;
    }

    @RequestMapping("queryForStockWarning")
    @ResponseBody
    public PageResult queryForStockWarningqu(ProductStockQueryObject qo){
        PageResult pageResult = productStockService.queryForStockWarning(qo);
        return pageResult;
    }
}

package com._520it.crm.web.controller;

import com._520it.crm.page.PageResult;
import com._520it.crm.query.ProductStockItemQueryObject;
import com._520it.crm.service.IProductStockItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("productStockItem")
public class ProductStockItemController {
    @Autowired
    private IProductStockItemService productStockItemService;
    @RequestMapping("")
    public String index(){
        return "productStockItem";
    }

    @RequestMapping("list")
    @ResponseBody
    public PageResult list(ProductStockItemQueryObject qo){
        PageResult pageResult = productStockItemService.queryPage(qo);
        return pageResult;
    }
    @RequestMapping("queryByProductSn")
    @ResponseBody
    public PageResult queryByProductSn(ProductStockItemQueryObject qo){
        PageResult pageResult = productStockItemService.queryByProductSn(qo);
        return pageResult;
    }
}

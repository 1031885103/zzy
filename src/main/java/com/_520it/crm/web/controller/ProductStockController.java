package com._520it.crm.web.controller;

import com._520it.crm.page.PageResult;
import com._520it.crm.query.CashQueryObject;
import com._520it.crm.service.IProductService;
import com._520it.crm.service.IProductStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/productStock")
public class ProductStockController {
    @Autowired
    IProductStockService productStockService;
    @Autowired
    IProductService productService;

    @RequestMapping("/list")
    @ResponseBody
    public PageResult list(CashQueryObject qo, Model model) {
        PageResult pageResult = null;
        if ((qo.getKeyword() != null && !"".equals(qo.getKeyword().trim()))) {
            pageResult = productStockService.queryPage(qo);
        }else if((qo.getProductName() != null && !"".equals(qo.getProductName().trim())) ){
            pageResult = productStockService.queryPage(qo);
        }

        return pageResult;
    }


}

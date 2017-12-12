package com._520it.crm.web.controller;

import com._520it.crm.page.PageResult;
import com._520it.crm.query.OrderStockItemQueryObject;
import com._520it.crm.service.IOrderStockItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("orderStockItem")
public class OrderStockItemController {
    @Autowired
    private IOrderStockItemService orderStockItemService;

    @RequestMapping("")
    public String index() {
        return "orderStockItem";
    }
    @RequestMapping("list")
    @ResponseBody
    public PageResult list(OrderStockItemQueryObject qo){
        PageResult pageResult = orderStockItemService.queryPage(qo);
        return pageResult;
    }
}

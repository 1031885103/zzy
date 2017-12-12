package com._520it.crm.web.controller;

import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/cash")
public class CashController {
    @RequestMapping("")
    public String index() {
        return "cash/list";
    }

    @RequestMapping("/new")
    public String net() {
        return "cash/net";
    }

    @RequestMapping("/list")
    @ResponseBody
    public PageResult list(QueryObject qo) {
        //PageResult result = cashService.queryPage(qo);
        PageResult result = null;
        return result;
    }
}

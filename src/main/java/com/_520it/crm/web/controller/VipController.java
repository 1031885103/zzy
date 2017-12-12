package com._520it.crm.web.controller;

import com._520it.crm.domain.Vip;
import com._520it.crm.domain.VipLevel;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.VipQueryObject;
import com._520it.crm.service.IVipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/vip")
public class VipController {

    @Autowired
    private IVipService vipService;


    @RequestMapping("")
    public String index() {
        return "vip";
    }

}

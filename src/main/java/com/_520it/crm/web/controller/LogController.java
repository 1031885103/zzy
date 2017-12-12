package com._520it.crm.web.controller;

import com._520it.crm.page.PageResult;
import com._520it.crm.query.LogQueryObject;
import com._520it.crm.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("log")
public class LogController {
    @Autowired
    private ILogService logService;

    @RequestMapping("")
    public String index(){
        return "log";
    }

    @RequestMapping("list")
    @ResponseBody
    public PageResult list(LogQueryObject qo){
        PageResult result = logService.queryPage(qo);
        return result;
    }
}

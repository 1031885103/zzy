package com._520it.crm.web.controller;

import com._520it.crm.domain.Money;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.MoneyQueryObject;
import com._520it.crm.service.IMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("money")
public class MoneyController {
    @Autowired
    private IMoneyService moneyService;
    @RequestMapping("")
    public String index(){
        return "money";
    }

    @RequestMapping("list")
    @ResponseBody
    public PageResult list(MoneyQueryObject qo){
        PageResult result = moneyService.queryPage(qo);
        return result;
    }

    @RequestMapping("save")
    @ResponseBody
    public AjaxResult save(Money money) {
        AjaxResult result = null;
        try {
            moneyService.save(money);
            result = new AjaxResult("保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult(false, "保存失败" + e.getMessage());
        }
        return result;
    }

    @RequestMapping("delete")
    @ResponseBody
    public AjaxResult delete(Long id) {
        AjaxResult result = null;
        try {
            moneyService.delete(id);
            result = new AjaxResult("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult(false, e.getMessage());
        }
        return result;
    }

    @RequestMapping("update")
    @ResponseBody
    public AjaxResult update(Money money) {
        AjaxResult result = null;
        try {
            moneyService.update(money);
            result = new AjaxResult("更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult(false, e.getMessage());
        }
        return result;
    }

    @RequestMapping("/selectListForProductForm")
    @ResponseBody
    public List<Money> selectListForProductForm(){
        List<Money> result = moneyService.list();
        return result;
    }
}

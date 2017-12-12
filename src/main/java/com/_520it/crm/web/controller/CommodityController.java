package com._520it.crm.web.controller;

import com._520it.crm.domain.Commodity;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.CommodityQueryObject;
import com._520it.crm.service.ICommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("commodity")
public class CommodityController {
    @Autowired
    private ICommodityService commodityService;

    @RequestMapping("")
    public String index(){
        return "commodity";
    }

    @RequestMapping("list")
    @ResponseBody
    public PageResult list(CommodityQueryObject qo){
        PageResult result = commodityService.queryPage(qo);
        return result;
    }

    @RequestMapping("save")
    @ResponseBody
    public AjaxResult save(Commodity p) {
        AjaxResult result = null;
        try {
            commodityService.save(p);
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
            commodityService.delete(id);
            result = new AjaxResult("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult(false, e.getMessage());
        }
        return result;
    }

    @RequestMapping("update")
    @ResponseBody
    public AjaxResult update(Commodity p) {
        AjaxResult result = null;
        try {
            commodityService.update(p);
            result = new AjaxResult("更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult(false, e.getMessage());
        }
        return result;
    }
}

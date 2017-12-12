package com._520it.crm.web.controller;

import com._520it.crm.domain.ProductElement;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.ProductElementQueryObject;
import com._520it.crm.service.IProductElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("productElement")
public class ProductElementController {
    @Autowired
    private IProductElementService elementService;

    @RequestMapping("")
    public String index() {
        return "productElement";
    }

    @RequestMapping("list")
    @ResponseBody
    public PageResult list(ProductElementQueryObject qo) {
        PageResult result = elementService.queryPage(qo);
        return result;
    }
    @RequestMapping("save")
    @ResponseBody
    public AjaxResult save(ProductElement pe) {
        AjaxResult result = null;
        try {
            elementService.save(pe);
            result = new AjaxResult("保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult(false, e.getMessage());
        }
        return result;
    }

    @RequestMapping("update")
    @ResponseBody
    public AjaxResult update(ProductElement pe) {
        AjaxResult result = null;
        try {
            elementService.update(pe);
            result = new AjaxResult("更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult(false, e.getMessage());
        }
        return result;
    }

    @RequestMapping("delete")
    @ResponseBody
    public AjaxResult delete(Long id) {
        AjaxResult result = null;
        try {
            elementService.delete(id);
            result = new AjaxResult("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult(false, e.getMessage());
        }
        return result;
    }

    @RequestMapping("/selectListForProductForm")
    @ResponseBody
    public List<ProductElement> selectListForProductForm(){
        List<ProductElement> result = elementService.list();
        return result;
    }
}

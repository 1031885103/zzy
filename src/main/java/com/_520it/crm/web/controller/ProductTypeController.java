package com._520it.crm.web.controller;

import com._520it.crm.domain.ProductType;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.ProductTypeQueryObject;
import com._520it.crm.service.IProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("productType")
public class ProductTypeController {
    @Autowired
    private IProductTypeService productTypeService;
    
    @RequestMapping("")
    public String index(){
        return "productType";
    }

    @RequestMapping("list")
    @ResponseBody
    public PageResult list(ProductTypeQueryObject qo) {
        PageResult result = productTypeService.queryPage(qo);
        return result;
    }

    @RequestMapping("save")
    @ResponseBody
    public AjaxResult save(ProductType s) {
        AjaxResult result = null;
        try {
            productTypeService.save(s);
            System.out.println(s.toString());

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
            productTypeService.delete(id);
            result = new AjaxResult("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult(false, e.getMessage());
        }
        return result;
    }

    @RequestMapping("update")
    @ResponseBody
    public AjaxResult update(ProductType s) {
        AjaxResult result = null;
        try {
            productTypeService.update(s);
            result = new AjaxResult("更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult(false, e.getMessage());
        }
        return result;
    }

    @RequestMapping("/selectListForProductForm")
    @ResponseBody
    public List<ProductType> selectListForProductForm(){
        List<ProductType> result = productTypeService.list();
        return result;
    }
}

package com._520it.crm.web.controller;

import com._520it.crm.domain.ProductManufacturer;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.ProductManufacturerQueryObject;
import com._520it.crm.service.IProductManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("productManufacturer")
public class ProductManufacturerController {
    @Autowired
    private IProductManufacturerService manufacturerService;
    
    @RequestMapping("")
    public String index(){
        return "productManufacturer";
    }

    @RequestMapping("list")
    @ResponseBody
    public PageResult list(ProductManufacturerQueryObject qo) {
        PageResult result = manufacturerService.queryPage(qo);
        return result;
    }

    @RequestMapping("save")
    @ResponseBody
    public AjaxResult save(ProductManufacturer pm) {
        AjaxResult result = null;
        try {
            manufacturerService.save(pm);

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
            manufacturerService.delete(id);
            result = new AjaxResult("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult(false, e.getMessage());
        }
        return result;
    }

    @RequestMapping("update")
    @ResponseBody
    public AjaxResult update(ProductManufacturer pm) {
        AjaxResult result = null;
        try {
            manufacturerService.update(pm);
            result = new AjaxResult("更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult(false, e.getMessage());
        }
        return result;
    }

    @RequestMapping("/selectListForProductForm")
    @ResponseBody
    public List<ProductManufacturer> selectListForProductForm(){
        List<ProductManufacturer> result = manufacturerService.list();
        return result;
    }
}

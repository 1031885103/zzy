package com._520it.crm.web.controller;

import com._520it.crm.domain.ProductBrand;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.ProductBrandQueryObject;
import com._520it.crm.service.IProductBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("productBrand")
public class ProductBrandController {
    @Autowired
    private IProductBrandService brandService;
    @RequestMapping("")
    public String index(){
        return "productBrand";
    }

    @RequestMapping("list")
    @ResponseBody
    public PageResult list(ProductBrandQueryObject qo){
        PageResult result = brandService.queryPage(qo);
        return result;
    }

    @RequestMapping("save")
    @ResponseBody
    public AjaxResult save(ProductBrand pb) {
        AjaxResult result = null;
        try {
            brandService.save(pb);
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
            brandService.delete(id);
            result = new AjaxResult("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult(false, e.getMessage());
        }
        return result;
    }

    @RequestMapping("update")
    @ResponseBody
    public AjaxResult update(ProductBrand pb) {
        AjaxResult result = null;
        try {
            brandService.update(pb);
            result = new AjaxResult("更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult(false, e.getMessage());
        }
        return result;
    }

    @RequestMapping("/selectListForProductForm")
    @ResponseBody
    public List<ProductBrand> selectListForProductForm(){
        List<ProductBrand> result = brandService.list();
        return result;
    }
}

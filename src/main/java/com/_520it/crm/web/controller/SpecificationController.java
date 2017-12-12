package com._520it.crm.web.controller;

import com._520it.crm.domain.Specification;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.SpecificationQueryObject;
import com._520it.crm.service.ISpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("specification")
public class SpecificationController {
    @Autowired
    private ISpecificationService specificationService;

    @RequestMapping("")
    public String index() {
        return "specification";
    }

    @RequestMapping("list")
    @ResponseBody
    public PageResult list(SpecificationQueryObject qo) {
        PageResult result = specificationService.queryPage(qo);
        return result;
    }

    @RequestMapping("save")
    @ResponseBody
    public AjaxResult save(Specification s) {
        AjaxResult result = null;
        try {
            specificationService.save(s);
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
            specificationService.delete(id);
            result = new AjaxResult("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult(false, e.getMessage());
        }
        return result;
    }

    @RequestMapping("update")
    @ResponseBody
    public AjaxResult update(Specification s) {
        AjaxResult result = null;
        try {
            specificationService.update(s);
            result = new AjaxResult("更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult(false, e.getMessage());
        }
        return result;
    }
}

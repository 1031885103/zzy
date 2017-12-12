package com._520it.crm.web.controller;

import com._520it.crm.domain.Vacate;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.VacateQueryObject;
import com._520it.crm.service.IVacateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("vacate")
public class VacateController {
    @Autowired
    private IVacateService vacateService;
    @RequestMapping("")
    public String index(){
        return "vacate";
    }

    @RequestMapping("list")
    @ResponseBody
    public PageResult list(VacateQueryObject qo){
        PageResult result = vacateService.queryPage(qo);
        return result;
    }

    @RequestMapping("save")
    @ResponseBody
    public AjaxResult save(Vacate vacate) {
        AjaxResult result = null;
        try {
            vacateService.save(vacate);
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
            vacateService.delete(id);
            result = new AjaxResult("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult(false, e.getMessage());
        }
        return result;
    }

    @RequestMapping("update")
    @ResponseBody
    public AjaxResult update(Vacate vacate) {
        AjaxResult result = null;
        try {
            vacateService.update(vacate);
            result = new AjaxResult("更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult(false, e.getMessage());
        }
        return result;
    }

    @RequestMapping("/selectListForProductForm")
    @ResponseBody
    public List<Vacate> selectListForProductForm(){
        List<Vacate> result = vacateService.list();
        return result;
    }
}

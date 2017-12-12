package com._520it.crm.web.controller;

import com._520it.crm.domain.Subbranch;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.SubbranchQueryObject;
import com._520it.crm.service.ISubbranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("subbranch")
public class SubbranchController {
    @Autowired
    private ISubbranchService subbranchService;

    @RequestMapping("")
    public String index(){
        return "subbranch";
    }

    @RequestMapping("list")
    @ResponseBody
    public PageResult list(SubbranchQueryObject qo){
        PageResult result = subbranchService.queryPage(qo);
        return result;
    }

    @RequestMapping("save")
    @ResponseBody
    public AjaxResult save(Subbranch s) {
        AjaxResult result = null;
        try {
            subbranchService.save(s);
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
            subbranchService.delete(id);
            result = new AjaxResult("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult(false, e.getMessage());
        }
        return result;
    }

    @RequestMapping("update")
    @ResponseBody
    public AjaxResult update(Subbranch s) {
        AjaxResult result = null;
        try {
            subbranchService.update(s);
            result = new AjaxResult("更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult(false, e.getMessage());
        }
        return result;
    }



    @RequestMapping("selectListForSubbranchForm")
    @ResponseBody
    public List<Subbranch> selectListForSubbranchForm() {
        List<Subbranch> subbranches = subbranchService.list();
        return subbranches;
    }



}

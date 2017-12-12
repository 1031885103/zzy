package com._520it.crm.web.controller;

import com._520it.crm.domain.PetBreed;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.PetBreedQueryObject;
import com._520it.crm.service.IPetBreedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("petBreed")
public class PetBreedController {
    @Autowired
    private IPetBreedService breedService;
    @RequestMapping("")
    public String index(){
        return "petBreed";
    }

    @RequestMapping("list")
    @ResponseBody
    public PageResult list(PetBreedQueryObject qo){
        PageResult result = breedService.queryPage(qo);
        return result;
    }

    @RequestMapping("save")
    @ResponseBody
    public AjaxResult save(PetBreed pb) {
        AjaxResult result = null;
        try {
            breedService.save(pb);
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
            breedService.delete(id);
            result = new AjaxResult("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult(false, e.getMessage());
        }
        return result;
    }

    @RequestMapping("update")
    @ResponseBody
    public AjaxResult update(PetBreed pb) {
        AjaxResult result = null;
        try {
            breedService.update(pb);
            result = new AjaxResult("更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult(false, e.getMessage());
        }
        return result;
    }
}

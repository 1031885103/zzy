package com._520it.crm.web.controller;

import com._520it.crm.domain.PetCategory;
import com._520it.crm.service.IPetCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/petCategory")
public class PetCategoryController {

    @Autowired
    private IPetCategoryService petCategoryService;


    //查询出宠物类型
    @RequestMapping("/selectPetType")
    @ResponseBody
    public List<PetCategory> selectPetType(){
        return petCategoryService.selectPetType();
    }

    //查询出宠物品种
    @RequestMapping("/selectPetKind")
    @ResponseBody
    public List<PetCategory> selectPetKind(Model model){

        model.addAttribute("newId",petCategoryService.selectPetKind());
        return petCategoryService.selectPetKind();
    }

//======================
    //待会删除掉
    @RequestMapping("/queryType")
    @ResponseBody
    public Long queryType(Long id){
        return petCategoryService.petCategoryService(id);
    }

}



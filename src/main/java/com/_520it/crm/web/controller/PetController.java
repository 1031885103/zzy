package com._520it.crm.web.controller;

import com._520it.crm.domain.*;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.PetQueryObject;
import com._520it.crm.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private IPetService petService;

    @Autowired
    private IVipService1 vipService;

    @Autowired
    private IVipLevelService1 vipLevelService;

    @Autowired
    private IPetCategoryService petCategoryService;

    @Autowired
    private IVipCardService1 vipCardService1;


    @RequestMapping("")
    public String index() {
        return "pet";
    }


    @RequestMapping("/list")
    @ResponseBody
    public PageResult list(PetQueryObject qo) {
        PageResult query = null;
        //会员列表数据
        try {
            query = petService.query(qo);
            System.out.println(query.toString());

            return query;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return query;
    }


    @RequestMapping("/save")
    @ResponseBody
    public AjaxResult input(Vip1 vip, Pet pet, PetCategory petCategory,VipCard1 vipCard1,Long vipId) {

        AjaxResult result = null;
        try {
            int rows = petCategoryService.selectPetHasType(petCategory.getId(), petCategory.getParentId());


            //添加宠物(vipId 已经有了,不需要变,还是原来的,但是需要重新设置进去)
            if (rows != 0 && vipId	!= null) {
                pet.setPetCategory(petCategory);
                //通过 vipid 查询出会员对象,在把这个对象设置给 pet
                Vip1 oldVip = vipService.selectByPrimaryKey(vipId);
                pet.setVips(oldVip);
                petService.insert(pet);
            } else {
                //添加会员
                vipService.insert(vip);
                vipCard1.setVip(vip);
                vipCardService1.insert(vipCard1);
                pet.setVipCard(vipCard1);
                pet.setVips(vip);//设置对象,就会设置会员id (查看Mapper.xml)
                pet.setPetCategory(petCategory);
                petService.insert(pet);
            }
            result = new AjaxResult(true, "保存成功");
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "保存失败" + e.getMessage());
        }

    }
}

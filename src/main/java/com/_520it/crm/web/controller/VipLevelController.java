package com._520it.crm.web.controller;

import com._520it.crm.domain.VipLevel;
import com._520it.crm.domain.VipLevel1;
import com._520it.crm.service.IVipLevelService;
import com._520it.crm.service.IVipLevelService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/vipLevel")
public class VipLevelController {

    @Autowired
    private IVipLevelService1 vipLevelService;

    @RequestMapping("/selectListVipLevel")
    @ResponseBody
    public List<VipLevel1> selectListVipLevel() {
        return vipLevelService.selectAll();
    }


    @RequestMapping("/queryLevelCount")
    @ResponseBody
    public VipLevel1 queryLevelCount(Long selectId) {
        return vipLevelService.queryLevelCount(selectId);
    }

}

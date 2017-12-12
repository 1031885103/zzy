package com._520it.crm.web.controller;

import com._520it.crm.domain.Vip;
import com._520it.crm.domain.VipCard;
import com._520it.crm.domain.VipCard1;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.service.IVipCardService;
import com._520it.crm.service.IVipCardService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

@Controller
@RequestMapping("/vipCard1")
public class VipCardController {

    @Autowired
    private IVipCardService1 vipCardService;


    @RequestMapping("/queryCardMsgByVipId")
    @ResponseBody
    public VipCard1 queryCardMsgByVipId(Long vipId) {
        return vipCardService.queryCardMsgByVipId(vipId);
    }

    @RequestMapping("/update")
    @ResponseBody
    public AjaxResult save(VipCard1 vipCard, Long vipId) {
        AjaxResult result = null;

        try {

            //根据会员id  更新充值卡的充值金额
            //vipId 获取到旧的金额
            //获取到新的金额  , 合并
            //设置给会员卡对象
            //更新

            vipCardService.selectVipMoney(vipId);//old
            BigDecimal cardMoney1 = vipCard.getCardMoney();
            BigDecimal cardMoney = vipCard.getCardMoney();
            cardMoney = cardMoney.add(cardMoney1);
            vipCard.setCardMoney(cardMoney);
            //更新
            vipCardService.updateByVipId(cardMoney, vipId);
            result = new AjaxResult(true, "充值成功");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "充值失败" + e.getMessage());
        }
    }
}

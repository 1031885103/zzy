package com._520it.crm.web.controller;


import com._520it.crm.domain.*;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.CashRecordQueryObject;
import com._520it.crm.service.IProductStockService;
import com._520it.crm.service.ISaleBillService;
import com._520it.crm.service.ISubbranchService;
import com._520it.crm.service.IVipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/saleBill")
public class SaleBillController {
    @Autowired
    private ISaleBillService saleBillService;
    @Autowired
    private ISubbranchService subBranchService;
    @Autowired
    private IVipService vipService;
    @Autowired
    private IProductStockService productStockService;

    @RequestMapping("/save")
    @ResponseBody
    public AjaxResult save(SaleBillItem saleBillItem, Long vipCard) {
        AjaxResult result = null;
        try {
            Vip vip = vipService.queryByVipCard(vipCard);
            if (vip != null) {
                saleBillService.saveVipItem(saleBillItem, vip);
            } else {

                saleBillService.save(saleBillItem);
            }
            result = new AjaxResult(true, "保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("保存失败,请联系管理员！");
        }
        return result;
    }


    @RequestMapping("/list")
    @ResponseBody
    public PageResult list(CashRecordQueryObject qo, Model model) {
        PageResult pageResult = null;

        pageResult = saleBillService.queryPage(qo);
        return pageResult;
    }


    @RequestMapping("/queryStoreBill")
    @ResponseBody
    public PageResult queryStoreBill(CashRecordQueryObject qo) {
        PageResult pageResult = null;

        pageResult = saleBillService.queryStoreBill(qo);
        return pageResult;
    }


    @RequestMapping("/selectListForRecordForm")
    @ResponseBody
    public List<Subbranch> selectListForRecordForm() {
        List<Subbranch> result = subBranchService.list();
        return result;
    }

    @RequestMapping("/queryVip")
    @ResponseBody
    public Vip queryVip(Long tel) {
        Vip vip = vipService.selectByPrimaryKey(tel);

        return vip;
    }


    @RequestMapping("/QueryProductStock")
    @ResponseBody
    public ProductStock QueryProductStock(Long productStockId) {
        ProductStock productStock = productStockService.selectByproductStockId(productStockId);

        return productStock;
    }


    @RequestMapping("/storeBill")
    @ResponseBody
    public AjaxResult storeBill(SaleBillItem saleBillItem, Long vipCard) {
        AjaxResult result = null;
        try {
            Vip vip = vipService.queryByVipCard(vipCard);
            if (vip != null) {
                saleBillService.storeVipBill(saleBillItem, vip);
            } else {
                saleBillService.save(saleBillItem);
            }
            result = new AjaxResult(true, "挂单成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("挂单失败");
        }
        return result;
    }
}


















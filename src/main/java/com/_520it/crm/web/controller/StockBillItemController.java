package com._520it.crm.web.controller;

import com._520it.crm.domain.StockBillItem;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.StockBillItemQueryObject;
import com._520it.crm.service.IStockBillItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("stockBillItem")
public class StockBillItemController {
    @Autowired
    private IStockBillItemService stockBillItemService;

    @RequestMapping("")
    public String index() {
        return "stockBillItem";
    }

    @RequestMapping("list")
    @ResponseBody
    public PageResult list(StockBillItemQueryObject qo) {
        PageResult pageResult = stockBillItemService.queryPage(qo);
        return pageResult;
    }

    @RequestMapping("addStock")
    @ResponseBody
    public AjaxResult addStock(StockBillItem item) {
        AjaxResult ajaxResult = null;
        try {
            stockBillItemService.insert(item);
            ajaxResult = new AjaxResult(true, "新增成功");
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult = new AjaxResult("新增失败,请联系管理员！");
        }
        return ajaxResult;
    }

    //入库
    @RequestMapping("instock")
    @ResponseBody
    public AjaxResult instock(Long[] ids) {
        AjaxResult ajaxResult = null;
        try {
            stockBillItemService.instock(ids);
            ajaxResult = new AjaxResult(true, "入库成功");
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult = new AjaxResult("入库失败,请联系管理员！");
        }
        return ajaxResult;
    }


    @RequestMapping("outstock")
    @ResponseBody
    public AjaxResult outstock(@RequestParam(value="ids[]",required=false)Long[] ids, Long subId) {
        AjaxResult ajaxResult = null;
        try {
            stockBillItemService.outstock(ids, subId);
            ajaxResult = new AjaxResult(true, "出库成功");
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult = new AjaxResult(e.getMessage());
        }
        return ajaxResult;
    }

    @RequestMapping("delete")
    @ResponseBody
    public AjaxResult delete(Long id) {
        AjaxResult ajaxResult = null;
        try {
            stockBillItemService.deleteByPrimaryKey(id);
            ajaxResult = new AjaxResult(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult = new AjaxResult("删除失败,请联系管理员！");
        }
        return ajaxResult;
    }
}

package com._520it.crm.web.controller;

import com._520it.crm.page.PageResult;
import com._520it.crm.query.SaleBillQueryObject;
import com._520it.crm.query.SaleChartQueryObject;
import com._520it.crm.query.SaleDataQueryObject;
import com._520it.crm.service.ISaleBillService1;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.*;

/**
 * 汇总报表控制器
 */
@Controller
@RequestMapping("/saleBill1")
public class SaleBillController1 {

    @Autowired
    private ISaleBillService1 saleBillService;

    @RequestMapping("")
    public String index() {
        return "/charts/saleBill";
    }

    //  -----------------------------返回视图需要的数据---------------------------------------------------------------

    // 获取销售的柱形图和折线图
    @RequestMapping("/chartByBar")
    public String chartByBar(@ModelAttribute("qo") SaleChartQueryObject qo, Model model) {
        // 查询到的结果
        List<Map<String, Object>> list = saleBillService.queryBarSaleChart(qo);
        // 保存groupType,横坐标
        List<Object> groupTypeList = new ArrayList<>();
        // 保存销售额,纵坐标
        List<Object> totalSaleAmountList = new ArrayList<>();
        // 保存成本,纵坐标
        List<Object> totalCostAmountList = new ArrayList<>();
        // 保存利润,纵坐标
        List<Object> profits = new ArrayList<>();

        for (Map<String, Object> map : list) {
            groupTypeList.add(map.get("chartType"));
            totalSaleAmountList.add(map.get("totalSaleAmount"));
            totalCostAmountList.add(map.get("totalCostAmount"));
            profits.add(map.get("profit"));
        }

        // 需要作为json数据进行传递
        model.addAttribute("groupTypeList", JSON.toJSONString(groupTypeList));
        model.addAttribute("totalSaleAmountList", JSON.toJSONString(totalSaleAmountList));
        model.addAttribute("totalCostAmountList", JSON.toJSONString(totalCostAmountList));
        model.addAttribute("profits", JSON.toJSONString(profits));
        // 页面按照什么分组
        model.addAttribute("groupBy", qo.getBarTitle());

        return "/charts/priceTotal";
    }

    // 获取销售饼图的数据
    @RequestMapping("/chartByPie")
    public String chartByPie(SaleChartQueryObject qo, Model model) {

        // 查询到的结果
        List<Map<String, Object>> list = saleBillService.queryPieSaleChart(qo);
        // 保存groupType,图中的分类
        List<Object> groupTypeList = new ArrayList<>();
        // 保存分类名称和份额
        List<Map<String, Object>> resultList = new ArrayList<>();

        // 获取最大的份额,用于漏斗图
        BigDecimal max = BigDecimal.ZERO;

        if (qo.getState() == 0) {   //按照销售额
            // 保存分类和其份额
            for (Map<String, Object> row : list) {

                // 添加分类
                groupTypeList.add(row.get("groupType"));

                // 分别将每条数据的分类和份额,添加进一个map保存
                Map<String, Object> map = new HashMap<>();
                resultList.add(map);

                map.put("name", row.get("groupType"));
                BigDecimal totalAmount = (BigDecimal) row.get("totalAmount");
                map.put("value", totalAmount);
                if (totalAmount.compareTo(max) > 0) {
                    max = totalAmount;
                }
            }
        } else {    // 按照销售数量
            // 保存分类和其份额
            for (Map<String, Object> row : list) {

                // 添加分类
                groupTypeList.add(row.get("groupType"));

                // 分别将每条数据的分类和份额,添加进一个map保存
                Map<String, Object> map = new HashMap<>();
                resultList.add(map);

                map.put("name", row.get("groupType"));
                BigDecimal totalNumber = (BigDecimal) row.get("totalNumber");
                map.put("value", totalNumber);
                if (totalNumber.compareTo(max) > 0) {
                    max = totalNumber;
                }
            }
        }

        // 回显查询信息
        model.addAttribute("qo", qo);
        // 需要作为json数据进行传递
        model.addAttribute("max", max);
        model.addAttribute("groupTypeList", JSON.toJSONString(groupTypeList));
        model.addAttribute("resultList", JSON.toJSONString(resultList));
        // 页面按照什么分组
        model.addAttribute("groupBy", qo.getPieTitle());
        //System.err.println(SaleChartQueryObject.groupByMap.get(qo.getGroupBy()) + "__" +qo.getGroupBy());
        return "/charts/classSales";
    }

    // 展示商品销售数据
    @RequestMapping("/SaleData")
    @ResponseBody
    public PageResult querySaleDataResult(SaleDataQueryObject qo) {

        PageResult result = saleBillService.querySaleDataResult(qo);
        System.err.println(JSON.toJSONString(result));
        return result;
    }



    // 展示收银列表
    @RequestMapping("/saleBillList")
    @ResponseBody
    public PageResult queryBillResult(SaleBillQueryObject qo) {
        //System.err.println(qo);
        PageResult pageResult = saleBillService.queryBillResult(qo);
        return pageResult;
    }

    // 展示收银明细
    @RequestMapping("saleBillItemList")
    @ResponseBody
    public PageResult querySaleBillItemResult(Long saleBillId, String salemanName) {
        if (saleBillId == null) {
            return new PageResult(0L, Collections.emptyList());
        }

        PageResult result = saleBillService.queryBillItemResult(saleBillId, salemanName);
        //System.err.println(result);
        return result;
    }

    //  -----------------------------返回页面视图---------------------------------------------------------------

    // 数据报表  出账,入账,盈利的图标报表
    @RequestMapping("/priceTotal")
    public String priceTotal() {

        return "/charts/priceTotal";
    }

    // 分类销售比例, 饼图(不同项目收入的占比)
    @RequestMapping("classSales")
    public String classSales() {
        return "/charts/classSales";
    }

    // 收银记录   列表
    @RequestMapping("cashierTotal")
    public String cashierTotal() {
        return "/charts/cashierTotal";
    }

    // 商品销售记录 列表
    @RequestMapping("goodsSales")
    public String goodsSales() {
        return "/charts/goodsSales";
    }


}


package com._520it.crm.service;

import com._520it.crm.page.PageResult;
import com._520it.crm.query.SaleBillQueryObject;
import com._520it.crm.query.SaleChartQueryObject;
import com._520it.crm.query.SaleDataQueryObject;

import java.util.List;
import java.util.Map;

/**
 * Created by ruosu on 2017/11/10.
 */
public interface ISaleBillService1 {
    PageResult queryBillResult(SaleBillQueryObject qo);

    List<Map<String,Object>> queryPieSaleChart(SaleChartQueryObject qo);

    List<Map<String,Object>> queryBarSaleChart(SaleChartQueryObject qo);

    PageResult queryBillItemResult(Long saleBillId, String salemanName);

    PageResult querySaleDataResult(SaleDataQueryObject qo);
}

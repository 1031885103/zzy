package com._520it.crm.mapper;

import com._520it.crm.domain.SaleBill1;
import com._520it.crm.query.SaleBillQueryObject;
import com._520it.crm.query.SaleChartQueryObject;

import java.util.List;
import java.util.Map;

public interface SaleBillMapper1 {

    SaleBill1 selectByPrimaryKey(Long id);

    List<SaleBill1> selectAll();


    Long queryCount(SaleBillQueryObject qo);

    List queryList(SaleBillQueryObject qo);

    List<Map<String, Object>> queryPieSaleChart(SaleChartQueryObject qo);

    List<Map<String, Object>> queryBarSaleChart(SaleChartQueryObject qo);

}
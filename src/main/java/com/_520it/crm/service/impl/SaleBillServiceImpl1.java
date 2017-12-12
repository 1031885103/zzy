package com._520it.crm.service.impl;

import com._520it.crm.domain.SaleBillItem;
import com._520it.crm.mapper.SaleBillMapper1;
import com._520it.crm.mapper.SaleBillItemMapper1;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.SaleBillQueryObject;
import com._520it.crm.query.SaleChartQueryObject;
import com._520it.crm.query.SaleDataQueryObject;
import com._520it.crm.service.ISaleBillService1;
import com._520it.crm.util.DateUtil;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SaleBillServiceImpl1 implements ISaleBillService1 {

    @Autowired
    private SaleBillMapper1 saleBillMapper;
    @Autowired
    private SaleBillItemMapper1 itemMapper;

    // 查询收银记录
    public PageResult queryBillResult(SaleBillQueryObject qo) {
        Long count = saleBillMapper.queryCount(qo);
        if (count == 0) {
            return new PageResult(0L, Collections.emptyList());
        }
        List list = saleBillMapper.queryList(qo);
        return new PageResult(count, list);
    }

    // 查询饼图的报表数据
    public List<Map<String, Object>> queryPieSaleChart(SaleChartQueryObject qo) {
        //System.err.println(saleBillMapper.queryPieSaleChart(qo));
        return saleBillMapper.queryPieSaleChart(qo);
    }

    // 查询柱状报表数据
    public List<Map<String, Object>> queryBarSaleChart(SaleChartQueryObject qo) {
        List<Map<String, Object>> maps = saleBillMapper.queryBarSaleChart(qo);
        handleBarResultMap(maps, qo);
        return maps;
    }

    @Override
    public PageResult queryBillItemResult(Long saleBillId, String saleManName) {
        Long count = itemMapper.selectCountByBillId(saleBillId);
        if (count == 0) {
            return new PageResult(0L, Collections.emptyList());
        }
        List list = itemMapper.selectByBillId(saleBillId);
        for (Object o : list) {
            SaleBillItem item = (SaleBillItem)o;
            item.setSalemanName(saleManName);
        }

        return new PageResult(count, list);
    }

    @Override
    public PageResult querySaleDataResult(SaleDataQueryObject qo) {
        Long count = itemMapper.queryDataCount(qo);
        if (count == 0) {
            return new PageResult(0L, Collections.emptyList());
        }
        List list = itemMapper.queryDataList(qo);
        return new PageResult(count, list);
    }

    // 处理柱状结果集
    private void handleBarResultMap(List<Map<String, Object>> maps, SaleChartQueryObject qo) {
        if (qo.getChartType() == 0) {       // 周报表
            Date opDate = qo.getOpDate();
            //System.err.println(opDate.toString());
            /*// 获取七天前的日期
            Calendar c = Calendar.getInstance();
            c.setTime(opDate);
            c.add(Calendar.DATE, -6);  // 设置前一周的时间点*/
            List<Integer> resultList = new ArrayList();
            for (int i = 0; i >= -6; i--) {
                Calendar c = Calendar.getInstance();
                c.setTime(opDate);
                c.add(Calendar.DATE, i);  // 设置前一周的时间点
                resultList.add(0, Integer.valueOf(DateUtil.getNoFormaterString(c.getTime())));
            }
            //  Date startDate = c.getTime();
            //  int start = Integer.parseInt(DateUtil.getNoFormaterString(startDate));
            //  int end = Integer.parseInt(DateUtil.getNoFormaterString(opDate));
            // 过滤结果集
            List<Integer> dayList = new ArrayList<>();

            for (int index = 0; index < maps.size(); index++) {
                //System.err.println(map.get("chartType"));
                Map<String, Object> map = maps.get(index);
                int op = Integer.parseInt((String) map.get("chartType"));
                //System.err.println(op + "-" + start + "-" + end);
                if (op > resultList.get(resultList.size() - 1) || op < resultList.get(0)) {   // 移除不在区间内的数据
                    maps.remove(map);
                    index--;
                } else {
                    dayList.add(op);
                }
            }

            // 添加没有数值的结果
            for (Integer day : resultList) {
                if (!dayList.contains(day)) {
                    Map<String, Object> map = new HashedMap();
                    maps.add(map);
                    map.put("chartType", day);
                    map.put("totalCostAmount", 0);
                    map.put("totalSaleAmount", 0);
                    map.put("profit", 0);
                } else {
                    Map<String, Object> map = maps.get(0);
                    maps.remove(0);
                    maps.add(map);
                }
            }
            /*for (int i = start; i <= end; i++) {
                if (!dayList.contains(i)) {
                    Map<String, Object> map = new HashedMap();
                    maps.add(map);
                    map.put("chartType", i);
                    map.put("totalCostAmount", 0);
                    map.put("totalSaleAmount", 0);
                    map.put("profit", 0);
                }

            }*/

        } else if (qo.getChartType() == 1) {    // 月报表
            Date opDate = qo.getOpDate();

            // 获取月初和月末的日期
            Date startDate = DateUtil.get1stInMonth(opDate);
            Date endDate = DateUtil.getLastDayInMonth(opDate);
            int start = Integer.parseInt(DateUtil.getNoFormaterString(startDate));
            int end = Integer.parseInt(DateUtil.getNoFormaterString(endDate));
            // 过滤结果集
            List<Integer> dayList = new ArrayList<>();

            for (int index = 0; index < maps.size(); index++) {
                Map<String, Object> map = maps.get(index);
                int op = Integer.parseInt((String) map.get("chartType"));
                if (op > end || op < start) {   // 移除不在区间内的数据
                    maps.remove(map);
                    index--;
                } else {
                    dayList.add(op);
                }
            }

            // 添加没有数值的结果
            for (int i = start; i <= end; i++) {
                if (!dayList.contains(i)) {
                    Map<String, Object> map = new HashedMap();
                    maps.add(map);
                    map.put("chartType", i);
                    map.put("totalCostAmount", 0);
                    map.put("totalSaleAmount", 0);
                    map.put("profit", 0);
                } else {
                    Map<String, Object> map = maps.get(0);
                    maps.remove(0);
                    maps.add(map);
                }
            }

        } else {                            // 年报表

            Date opDate = qo.getOpDate();

            // 获取年初和和年末的日期
            Date startDate = DateUtil.get1stInYear(opDate);
            Date endDate = DateUtil.getLastDayInYear(opDate);
            int start = Integer.parseInt(DateUtil.getNoFormaterString(startDate)) / 100;
            int end = Integer.parseInt(DateUtil.getNoFormaterString(endDate)) / 100;
            // 过滤结果集
            List<Integer> dayList = new ArrayList<>();

            for (int index = 0; index < maps.size(); index++) {
                Map<String, Object> map = maps.get(index);
                int op = Integer.parseInt((String) map.get("chartType"));
                if (op > end || op < start) {   // 移除不在区间内的数据
                    maps.remove(map);
                    index--;
                } else {
                    dayList.add(op);
                }
            }

            // 添加没有数值的结果
            for (int i = start; i <= end; i++) {
                if (!dayList.contains(i)) {
                    Map<String, Object> map = new HashedMap();
                    maps.add(map);
                    map.put("chartType", i);
                    map.put("totalCostAmount", 0);
                    map.put("totalSaleAmount", 0);
                    map.put("profit", 0);
                } else {
                    Map<String, Object> map = maps.get(0);
                    maps.remove(0);
                    maps.add(map);
                }
            }

        }
        // 处理横坐标
        handleViewDateFormat(qo, maps);
    }

    private void handleViewDateFormat(SaleChartQueryObject qo, List<Map<String, Object>> maps) {

        // 周报表   20161106 ---> 2016-11-06
        switch (qo.getChartType()) {
            case 0: {
                for (Map<String, Object> map : maps) {
                    String string = map.get("chartType").toString();
                    string = string.substring(0, 4) + "-" + string.substring(4, 6) + "-" + string.substring(6);
                    map.put("chartType", string);
                }
                break;
            }
            case 1: {
                for (Map<String, Object> map : maps) {
                    String string = map.get("chartType").toString();
                    string = string.substring(6);
                    string = Integer.parseInt(string) + "";

                    map.put("chartType", string);
                }
                break;
            }
            case 2: {
                for (Map<String, Object> map : maps) {
                    String string = map.get("chartType").toString();
                    string = string.substring(4);
                    string = Integer.parseInt(string) + "";
                    string = string + "月";
                    map.put("chartType", string);
                }
                break;
            }
        }
        /*if (qo.getChartType() == 0) {

            for (Map<String, Object> map : maps) {
                String string = map.get("chartType").toString();
                string = string.substring(0, 4) + "-" + string.substring(4, 6) + "-" + string.substring(6);
                map.put("chartType", string);
            }

        } else if {

            for (Map<String, Object> map : maps) {
                String string = map.get("chartType").toString();
                string = string.substring(6);
                string = Integer.parseInt(string) + "";
                if (qo.getChartType() == 2) {   // 年报表
                    string = string + "月";
                }
                map.put("chartType", string);
            }

        }*/


    }

}

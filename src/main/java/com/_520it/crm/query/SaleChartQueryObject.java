package com._520it.crm.query;

import com._520it.crm.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Setter@Getter
public class SaleChartQueryObject extends QueryObject {

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "yyyy-MM-dd")
    private Date opDate = new Date();          // 页面的时间,默认显示今天
    private int dateType = 0;    // 0为所有, 1,2,3为日 月 年
    private int chartType = 0;   // 0为周报表, 1为月报表, 2为年报表

    private int state = 0;  // 查询销售额还是销售数量,默认为销售额, 1为销售数量
    private String groupBy = "p.category_name";

    public static Map<String, String> groupByMap = new LinkedHashMap<>();
    static {
        groupByMap.put("p.category_name", "商品分类");
        //groupByMap.put("", "销售数量比例");
    }

    public Date getOpDate() {
        return opDate == null ? null : DateUtil.getEndDate(opDate);
    }

    public Date getStartTime() {
        if (dateType == 2) {   // 月
            return DateUtil.get1stInMonth(opDate);
        }

        return DateUtil.get1stInYear(opDate);
    }

    public Date getEndTime() {
        if (dateType == 2) {   // 月
            return DateUtil.getLastDayInMonth(opDate);
        }
        return DateUtil.getLastDayInYear(opDate);
    }

    public String getPieTitle() {

        if (state == 0) {
            return "分类销售额比例";
        }
        return "分类销售数量比例";
    }

    public String getBarTitle() {
        if (chartType == 0) {
            return "近一周内的数据报表";
        } else if (chartType == 1) {
            return "本月每天数据报表";
        }
        return "本年每月数据报表";
    }

    public String getTimeFormat() {
        if (chartType != 2) {
            return "%Y%m%d";
        }
        return "%Y%m";
    }

}

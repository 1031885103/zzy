package com._520it.crm.mapper;

import com._520it.crm.domain.StockBillItem;
import com._520it.crm.query.StockBillItemQueryObject;

import java.util.List;

public interface StockBillItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StockBillItem record);

    StockBillItem selectByPrimaryKey(Long id);

    List<StockBillItem> selectAll();

    int updateByPrimaryKey(StockBillItem record);

    Long queryPageCount(StockBillItemQueryObject qo);

    List<StockBillItem> queryPageDataResult(StockBillItemQueryObject qo);

    void updateState(StockBillItem stockBillItem);
}
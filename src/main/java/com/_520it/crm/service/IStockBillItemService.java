package com._520it.crm.service;

import com._520it.crm.domain.StockBillItem;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.StockBillItemQueryObject;

import java.util.List;

public interface IStockBillItemService {
    void deleteByPrimaryKey(Long id);

    void insert(StockBillItem record);

    StockBillItem selectByPrimaryKey(Long id);

    List<StockBillItem> selectAll();

    void updateByPrimaryKey(StockBillItem record);

    PageResult queryPage(StockBillItemQueryObject qo);

    void instock(Long[] ids);

    void outstock(Long[] ids, Long subId);
}

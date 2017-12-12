package com._520it.crm.service;

import com._520it.crm.domain.OrderStockItem;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.OrderStockItemQueryObject;

import java.util.List;

public interface IOrderStockItemService {
    void deleteByPrimaryKey(Long id);

    void insert(OrderStockItem record);

    OrderStockItem selectByPrimaryKey(Long id);

    List<OrderStockItem> selectAll();

    void updateByPrimaryKey(OrderStockItem record);

    PageResult queryPage(OrderStockItemQueryObject qo);
}

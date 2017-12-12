package com._520it.crm.mapper;

import com._520it.crm.domain.OrderStockItem;
import com._520it.crm.query.OrderStockItemQueryObject;

import java.util.List;

public interface OrderStockItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderStockItem record);

    OrderStockItem selectByPrimaryKey(Long id);

    List<OrderStockItem> selectAll();

    int updateByPrimaryKey(OrderStockItem record);
    List<OrderStockItem> queryPageData(OrderStockItemQueryObject qo);

    Long queryPageCount(OrderStockItemQueryObject qo);
}
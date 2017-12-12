package com._520it.crm.service.impl;

import com._520it.crm.domain.OrderStockItem;
import com._520it.crm.mapper.OrderStockItemMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.OrderStockItemQueryObject;
import com._520it.crm.service.IOrderStockItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class OrderStockItemServiceImpl implements IOrderStockItemService {

    @Autowired
    private OrderStockItemMapper orderStockItemMapper;

    public void deleteByPrimaryKey(Long id) {
        orderStockItemMapper.deleteByPrimaryKey(id);
    }

    public void insert(OrderStockItem record) {
        orderStockItemMapper.insert(record);
    }

    public OrderStockItem selectByPrimaryKey(Long id) {
        return orderStockItemMapper.selectByPrimaryKey(id);
    }

    public List<OrderStockItem> selectAll() {
        return orderStockItemMapper.selectAll();
    }

    public void updateByPrimaryKey(OrderStockItem record) {
        orderStockItemMapper.updateByPrimaryKey(record);
    }

    public PageResult queryPage(OrderStockItemQueryObject qo) {
        Long total = orderStockItemMapper.queryPageCount(qo);
        if (total <= 0) {
            return new PageResult(total, Collections.EMPTY_LIST);
        }
        List<OrderStockItem> rows = orderStockItemMapper.queryPageData(qo);

        return new PageResult(total, rows);
    }
}

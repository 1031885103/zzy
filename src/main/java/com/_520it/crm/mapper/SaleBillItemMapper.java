package com._520it.crm.mapper;

import com._520it.crm.domain.SaleBillItem;

import java.util.List;

public interface SaleBillItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SaleBillItem record);

    SaleBillItem selectByPrimaryKey(Long id);

    List<SaleBillItem> selectAll();

    int updateByPrimaryKey(SaleBillItem record);

    void deleteByBillId(Long id);

    void updateStoreItem(byte b);
    List<SaleBillItem> selectByBillId(Long billId);
}
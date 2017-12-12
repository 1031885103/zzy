package com._520it.crm.mapper;

import com._520it.crm.domain.SaleBill;
import com._520it.crm.query.CashRecordQueryObject;
import com._520it.crm.query.QueryObject;

import java.util.List;

public interface SaleBillMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SaleBill record);

    SaleBill selectByPrimaryKey(Long id);

    List<SaleBill> selectAll();

    int updateByPrimaryKey(SaleBill record);

    void updateState(SaleBill bill);
    Long queryPageCount(QueryObject qo);

    List<SaleBill> queryPageDataResult(QueryObject qo);

    void updateStoreBill(byte b);

    List<SaleBill> queryStoreBill(QueryObject qo);
}
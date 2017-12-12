package com._520it.crm.mapper;

import com._520it.crm.domain.SaleBillItem;
import com._520it.crm.query.SaleDataQueryObject;

import java.util.List;

public interface SaleBillItemMapper1 {

    List<SaleBillItem> selectByBillId(Long billId);

    long selectCountByBillId(Long billId);


    Long queryDataCount(SaleDataQueryObject qo);

    List queryDataList(SaleDataQueryObject qo);
}
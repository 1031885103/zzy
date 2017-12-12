package com._520it.crm.service;

import com._520it.crm.domain.SaleBill;
import com._520it.crm.domain.SaleBillItem;
import com._520it.crm.domain.Vip;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

import java.util.List;

public interface ISaleBillService {
    void save( SaleBillItem saleBillItem);

    void update(SaleBill bill);

    void delete(Long id);

    SaleBill get(Long id);

    List<SaleBill> listAll();

    PageResult query(QueryObject qo);

    void audit(Long id);

    PageResult queryPage(QueryObject qo);

    void saveVipItem(SaleBillItem saleBillItem, Vip vip);

    void storeVipBill(SaleBillItem saleBillItem, Vip vip);

    PageResult queryStoreBill(QueryObject qo);
}

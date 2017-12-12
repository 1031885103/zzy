package com._520it.crm.service.impl;

import com._520it.crm.domain.SaleBill;
import com._520it.crm.mapper.ProductStockMapper;
import com._520it.crm.service.ISaleAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditBillServiceImpl implements ISaleAccountService {
    @Autowired
    private ProductStockMapper productStockMapper;
   


    @Override
    public void auditOutcome(SaleBill bill) {


    }
}

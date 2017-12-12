package com._520it.crm.service.impl;

import com._520it.crm.domain.Supplier;
import com._520it.crm.mapper.SupplierMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.SupplierQueryObject;
import com._520it.crm.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
public class SupplierServiceImpl implements ISupplierService {
    @Autowired
    private SupplierMapper supplierMapper;
    @Override
    public void save(Supplier s) {
        if (s.getDisplay() == 0) {
            s.setDisplay(Supplier.START_YES);
        } else {
            s.setDisplay(Supplier.START_NO);
        }
        supplierMapper.insert(s);
    }

    @Override
    public void update(Supplier s) {
        supplierMapper.updateByPrimaryKey(s);
    }

    @Override
    public void delete(Long id) {
        supplierMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Supplier get(Long id) {
        return supplierMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Supplier> list() {
        return supplierMapper.selectAll();
    }

    @Override
    public PageResult queryPage(SupplierQueryObject qo) {
        long count = supplierMapper.queryCount(qo);
        if(count<=0L){
            return new PageResult(0L, Collections.EMPTY_LIST);
        }
        List<Supplier> result = supplierMapper.queryData(qo);
        return new PageResult(count,result);
    }
}

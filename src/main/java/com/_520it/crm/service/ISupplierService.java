package com._520it.crm.service;

import com._520it.crm.domain.Supplier;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.SupplierQueryObject;

import java.util.List;

public interface ISupplierService {

    void save(Supplier s);

    void update(Supplier s);

    void delete(Long id);

    Supplier get(Long id);

    List<Supplier> list();

    PageResult queryPage(SupplierQueryObject qo);
}

package com._520it.crm.mapper;

import com._520it.crm.domain.Supplier;
import com._520it.crm.query.SupplierQueryObject;

import java.util.List;

public interface SupplierMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Supplier record);

    Supplier selectByPrimaryKey(Long id);

    List<Supplier> selectAll();

    int updateByPrimaryKey(Supplier record);

    List<Supplier> queryData(SupplierQueryObject qo);

    long queryCount(SupplierQueryObject qo);
}
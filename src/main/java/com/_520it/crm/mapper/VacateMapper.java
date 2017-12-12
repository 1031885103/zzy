package com._520it.crm.mapper;

import com._520it.crm.domain.Vacate;
import com._520it.crm.query.VacateQueryObject;

import java.util.List;

public interface VacateMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Vacate record);

    Vacate selectByPrimaryKey(Long id);

    List<Vacate> selectAll();

    int updateByPrimaryKey(Vacate record);

    List<Vacate> queryData(VacateQueryObject qo);

    long queryCount(VacateQueryObject qo);
}
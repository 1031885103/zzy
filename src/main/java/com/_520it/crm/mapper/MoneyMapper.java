package com._520it.crm.mapper;

import com._520it.crm.domain.Money;
import com._520it.crm.query.MoneyQueryObject;

import java.util.List;

public interface MoneyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Money record);

    Money selectByPrimaryKey(Long id);

    List<Money> selectAll();

    int updateByPrimaryKey(Money record);

    long queryCount(MoneyQueryObject qo);

    List<Money> queryData(MoneyQueryObject qo);
}
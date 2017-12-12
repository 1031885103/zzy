package com._520it.crm.mapper;

import com._520it.crm.domain.Subbranch;
import com._520it.crm.query.SubbranchQueryObject;

import java.util.List;

public interface SubbranchMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Subbranch record);

    Subbranch selectByPrimaryKey(Long id);

    List<Subbranch> selectAll();

    int updateByPrimaryKey(Subbranch record);

    long queryCount(SubbranchQueryObject qo);

    List<Subbranch> queryData(SubbranchQueryObject qo);
}
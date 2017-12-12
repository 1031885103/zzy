package com._520it.crm.mapper;

import com._520it.crm.domain.Work;
import com._520it.crm.query.QueryObject;

import java.util.List;

public interface WorkMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Work record);

    Work selectByPrimaryKey(Long id);

    List<Work> selectAll();

    int updateByPrimaryKey(Work record);

    long queryCount(QueryObject qo);

    List<Work> queryData(QueryObject qo);
}
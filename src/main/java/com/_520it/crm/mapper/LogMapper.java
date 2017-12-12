package com._520it.crm.mapper;

import com._520it.crm.domain.Log;
import com._520it.crm.query.LogQueryObject;

import java.util.List;

public interface LogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Log record);

    Log selectByPrimaryKey(Long id);

    List<Log> selectAll();

    int updateByPrimaryKey(Log record);

    List<Log> queryData(LogQueryObject qo);

    long queryCount(LogQueryObject qo);
}
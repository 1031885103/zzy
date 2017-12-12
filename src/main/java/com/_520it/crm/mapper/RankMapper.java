package com._520it.crm.mapper;

import com._520it.crm.domain.Rank;
import com._520it.crm.query.RankQueryObject;

import java.util.List;

public interface RankMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Rank record);

    Rank selectByPrimaryKey(Long id);

    List<Rank> selectAll();

    int updateByPrimaryKey(Rank record);

    long queryCount(RankQueryObject qo);

    List<Rank> queryData(RankQueryObject qo);
}
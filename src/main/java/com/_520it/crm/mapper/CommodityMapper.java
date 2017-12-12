package com._520it.crm.mapper;

import com._520it.crm.domain.Commodity;
import com._520it.crm.query.CommodityQueryObject;

import java.util.List;

public interface CommodityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Commodity record);

    Commodity selectByPrimaryKey(Long id);

    List<Commodity> selectAll();

    int updateByPrimaryKey(Commodity record);

    List<Commodity> queryData(CommodityQueryObject qo);

    long queryCount(CommodityQueryObject qo);
}
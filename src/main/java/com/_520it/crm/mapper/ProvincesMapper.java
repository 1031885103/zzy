package com._520it.crm.mapper;

import com._520it.crm.domain.Provinces;
import java.util.List;

public interface ProvincesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Provinces record);

    Provinces selectByPrimaryKey(Integer id);

    List<Provinces> selectAll();

    int updateByPrimaryKey(Provinces record);
}
package com._520it.crm.mapper;

import com._520it.crm.domain.Cities;
import java.util.List;

public interface CitiesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cities record);

    Cities selectByPrimaryKey(Integer id);

    List<Cities> selectAll();

    int updateByPrimaryKey(Cities record);

    List<Cities> listByProvinceId(Long id);
}
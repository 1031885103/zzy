package com._520it.crm.mapper;

import com._520it.crm.domain.Areas;
import java.util.List;

public interface AreasMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Areas record);

    Areas selectByPrimaryKey(Integer id);

    List<String> listByCityId(Long cityId);

    int updateByPrimaryKey(Areas record);
}
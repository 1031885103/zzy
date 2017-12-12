package com._520it.crm.mapper;

import com._520it.crm.domain.Specification;
import com._520it.crm.query.SpecificationQueryObject;

import java.util.List;

public interface SpecificationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Specification record);

    Specification selectByPrimaryKey(Long id);

    List<Specification> selectAll();

    int updateByPrimaryKey(Specification record);

    Long queryCount(SpecificationQueryObject qo);

    List<Specification> queryData(SpecificationQueryObject qo);
}
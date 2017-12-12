package com._520it.crm.mapper;

import com._520it.crm.domain.Pet;
import com._520it.crm.query.PetQueryObject;
import com._520it.crm.query.QueryObject;
import com._520it.crm.query.VipQueryObject;

import java.util.List;

public interface PetMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Pet record);

    Pet selectByPrimaryKey(Long id);

    List<Pet> selectAll();

    int updateByPrimaryKey(Pet record);

    Long queryForCount(PetQueryObject qo);

    List<?> queryForList(PetQueryObject qo);
}
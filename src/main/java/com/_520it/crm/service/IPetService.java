package com._520it.crm.service;

import com._520it.crm.domain.Pet;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.PetQueryObject;

import java.util.List;

public interface IPetService  {
    int deleteByPrimaryKey(Long id);

    int insert(Pet record);

    Pet selectByPrimaryKey(Long id);

    List<Pet> selectAll();

    int updateByPrimaryKey(Pet record);

    PageResult query(PetQueryObject qo);

}

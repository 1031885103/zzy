package com._520it.crm.service;

import com._520it.crm.domain.PetCategory;

import java.util.List;

public interface IPetCategoryService {
    int deleteByPrimaryKey(Long id);

    int insert(PetCategory record);

    PetCategory selectByPrimaryKey(Long id);

    List<PetCategory> selectAll();

    int updateByPrimaryKey(PetCategory record);

    List<PetCategory> selectPetType();

    List<PetCategory> selectPetKind();

    int selectPetHasType(Long id, Long parentId);

    Long     petCategoryService(Long id);
}

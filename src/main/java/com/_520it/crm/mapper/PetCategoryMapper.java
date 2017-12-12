package com._520it.crm.mapper;

import com._520it.crm.domain.PetCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PetCategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PetCategory record);

    PetCategory selectByPrimaryKey(Long id);

    List<PetCategory> selectAll();

    int updateByPrimaryKey(PetCategory record);

    List<PetCategory> selectPetType();

    List<PetCategory> selectPetKind();

    int selectPetHasType(@Param("id") Long id, @Param("parentId") Long parentId);

    Long petCategoryService(Long id);
}
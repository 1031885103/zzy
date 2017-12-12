package com._520it.crm.service.impl;

import com._520it.crm.domain.PetCategory;
import com._520it.crm.mapper.PetCategoryMapper;
import com._520it.crm.service.IPetCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetCategoryService implements IPetCategoryService {

    @Autowired
    private PetCategoryMapper petCategoryMapper;


    @Override
    public int deleteByPrimaryKey(Long id) {
        return petCategoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(PetCategory record) {
        return petCategoryMapper.insert(record);
    }

    @Override
    public PetCategory selectByPrimaryKey(Long id) {
        return petCategoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<PetCategory> selectAll() {
        return petCategoryMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(PetCategory record) {
        return petCategoryMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<PetCategory> selectPetType() {
        return  petCategoryMapper.selectPetType();
    }

    @Override
    public List<PetCategory> selectPetKind() {
        return petCategoryMapper.selectPetKind();
    }


    //查询有没有 对应的记录
    @Override
    public int selectPetHasType(Long id, Long parentId) {
        return petCategoryMapper.selectPetHasType(id,parentId);
    }

    @Override
    public Long petCategoryService(Long id) {
        return petCategoryMapper.petCategoryService(id);
    }
}

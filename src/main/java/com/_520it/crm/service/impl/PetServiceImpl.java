package com._520it.crm.service.impl;

import com._520it.crm.domain.Pet;
import com._520it.crm.mapper.PetMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.PetQueryObject;
import com._520it.crm.query.VipQueryObject;
import com._520it.crm.service.IPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PetServiceImpl implements IPetService {

    @Autowired
    private PetMapper petMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return petMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Pet record) {
        return petMapper.insert(record);
    }

    @Override
    public Pet selectByPrimaryKey(Long id) {
        return petMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Pet> selectAll() {
        return petMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Pet record) {
        return 0;
    }

    @Override
    public PageResult query(PetQueryObject qo) {
        long rows = petMapper.queryForCount(qo);
        if (rows == 0) {
            return new PageResult(0L, Collections.EMPTY_LIST);
        }

        List<?> result = petMapper.queryForList(qo);
        System.err.println(result);
        return new PageResult(rows, result);
    }
}

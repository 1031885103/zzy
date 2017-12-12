package com._520it.crm.service.impl;

import com._520it.crm.domain.PetBreed;
import com._520it.crm.domain.ProductBrand;
import com._520it.crm.mapper.PetBreedMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.PetBreedQueryObject;
import com._520it.crm.service.IPetBreedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PetBreedServiceImpl implements IPetBreedService {
    @Autowired
    private PetBreedMapper petBreedMapper;

    @Override
    public void save(PetBreed p) {
        if (p.getDisplay() == 0) {
            p.setDisplay(ProductBrand.START_YES);
        } else {
            p.setDisplay(ProductBrand.START_NO);
        }
        petBreedMapper.insert(p);
    }

    @Override
    public void update(PetBreed p) {
        petBreedMapper.updateByPrimaryKey(p);
    }

    @Override
    public void delete(Long id) {
        petBreedMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PetBreed get(Long id) {
        return petBreedMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<PetBreed> list() {
        return petBreedMapper.selectAll();
    }

    @Override
    public PageResult queryPage(PetBreedQueryObject qo) {
        long count = petBreedMapper.queryCount(qo);
        if(count<=0L){
            return new PageResult(0L, Collections.EMPTY_LIST);
        }
        List<PetBreed> result = petBreedMapper.queryData(qo);
        return new PageResult(count,result);
    }
}

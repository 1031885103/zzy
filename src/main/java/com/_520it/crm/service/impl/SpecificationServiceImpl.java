package com._520it.crm.service.impl;

import com._520it.crm.domain.Specification;
import com._520it.crm.mapper.SpecificationMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.SpecificationQueryObject;
import com._520it.crm.service.ISpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SpecificationServiceImpl implements ISpecificationService {
    @Autowired
    private SpecificationMapper specificationMapper;

    @Override
    public void save(Specification s) {
        if(s.getDisplay()==0){
            s.setDisplay(Specification.START_YES);
        }else{
            s.setDisplay(Specification.START_NO);
        }
        specificationMapper.insert(s);
    }

    @Override
    public void update(Specification s) {
        specificationMapper.updateByPrimaryKey(s);
    }

    @Override
    public void delete(Long id) {
        specificationMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Specification get(Long id) {
        return specificationMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Specification> list() {
        return specificationMapper.selectAll();
    }

    @Override
    public PageResult queryPage(SpecificationQueryObject qo) {
        Long count = specificationMapper.queryCount(qo);
        if (count <= 0L) {
            return new PageResult(0L, Collections.EMPTY_LIST);
        }
        List<Specification> result = specificationMapper.queryData(qo);
        return new PageResult(count, result);
    }
}

package com._520it.crm.service.impl;

import com._520it.crm.domain.Subbranch;
import com._520it.crm.mapper.SubbranchMapper;
import com._520it.crm.mapper.SubbranchMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.SubbranchQueryObject;
import com._520it.crm.service.ISubbranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SubbranchServiceImpl implements ISubbranchService {
    @Autowired
    private SubbranchMapper subbranchMapper;
    @Override
    public void save(Subbranch i) {
        
        subbranchMapper.insert(i);
    }

    @Override
    public void update(Subbranch i) {
        subbranchMapper.updateByPrimaryKey(i);
    }

    @Override
    public void delete(Long id) {
        subbranchMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Subbranch get(Long id) {
        return subbranchMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Subbranch> list() {
        return subbranchMapper.selectAll();
    }

    @Override
    public PageResult queryPage(SubbranchQueryObject qo) {
        long count = subbranchMapper.queryCount(qo);
        if(count<=0L){

            return new PageResult(0L, Collections.EMPTY_LIST);
            }
        List<Subbranch> result = subbranchMapper.queryData(qo);
        return new PageResult(count,result);
    }
}

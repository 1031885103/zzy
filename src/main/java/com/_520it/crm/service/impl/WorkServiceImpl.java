package com._520it.crm.service.impl;

import com._520it.crm.domain.Work;
import com._520it.crm.mapper.WorkMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.IWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class WorkServiceImpl implements IWorkService {

    @Autowired
    private WorkMapper workMapper;

    @Override
    public void save(Work w) {
        workMapper.insert(w);
    }

    @Override
    public void update(Work w) {
        workMapper.updateByPrimaryKey(w);
    }

    @Override
    public void delete(Long id) {
        workMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Work get(Long id) {
        return workMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Work> list() {
        return workMapper.selectAll();
    }

    @Override
    public PageResult queryPage(QueryObject qo) {
       long count = workMapper.queryCount(qo);
       if(count<=0L){

           return new PageResult(0L, Collections.EMPTY_LIST);
           }
       List<Work> result = workMapper.queryData(qo);
       return new PageResult(count,result);
    }
}

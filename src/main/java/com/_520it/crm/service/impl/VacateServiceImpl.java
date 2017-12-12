package com._520it.crm.service.impl;

import com._520it.crm.domain.Vacate;
import com._520it.crm.mapper.VacateMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.VacateQueryObject;
import com._520it.crm.service.IVacateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class VacateServiceImpl implements IVacateService {

    @Autowired
    private VacateMapper leaveMapper;

    @Override
    public void save(Vacate l) {
        if(l.getState()==0){
            l.setState(l.START_OK);
        }else{
            l.setState(l.START_BAD);
        }
        if(l.getNormal()==0){
            l.setNormal(l.START_YES);
        }else{
            l.setNormal(l.START_BAD);
        }
        leaveMapper.insert(l);
    }

    @Override
    public void update(Vacate l) {
        leaveMapper.updateByPrimaryKey(l);
    }

    @Override
    public void delete(Long id) {
        leaveMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Vacate get(Long id) {
        return leaveMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Vacate> list() {
        return leaveMapper.selectAll();
    }

    @Override
    public PageResult queryPage(VacateQueryObject qo) {
        long count = leaveMapper.queryCount(qo);
        if(count<=0L){

            return new PageResult(0L, Collections.EMPTY_LIST);
            }
        List<Vacate> result = leaveMapper.queryData(qo);
        return new PageResult(count,result);
    }
}

package com._520it.crm.service.impl;

import com._520it.crm.domain.Rank;
import com._520it.crm.mapper.RankMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.RankQueryObject;
import com._520it.crm.service.IRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RankServiceImpl implements IRankService {
    @Autowired
    private RankMapper vipLevelMapper;

    @Override
    public void save(Rank v) {
        if (v.getEnable() == 0) {
            v.setEnable(Rank.START_YES);
        } else {
            v.setEnable(Rank.START_NO);
        }
        vipLevelMapper.insert(v);

    }

    @Override
    public void update(Rank v) {
        vipLevelMapper.updateByPrimaryKey(v);
    }

    @Override
    public void delete(Long id) {
        vipLevelMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Rank get(Long id) {
        return vipLevelMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Rank> list() {
        return vipLevelMapper.selectAll();
    }

    @Override
    public PageResult queryPage(RankQueryObject qo) {
        long count = vipLevelMapper.queryCount(qo);
        if(count<=0L){

            return new PageResult(0L, Collections.EMPTY_LIST);
            }
        List<Rank> result = vipLevelMapper.queryData(qo);
        return new PageResult(count,result);
    }
}

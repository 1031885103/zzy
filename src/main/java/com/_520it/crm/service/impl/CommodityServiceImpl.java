package com._520it.crm.service.impl;

import com._520it.crm.domain.Commodity;
import com._520it.crm.mapper.CommodityMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.CommodityQueryObject;
import com._520it.crm.service.ICommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CommodityServiceImpl implements ICommodityService {
    @Autowired
    private CommodityMapper commodityMapper;

    @Override
    public void save(Commodity p) {
        if (p.getDisplay() == 0) {
            p.setDisplay(Commodity.START_YES);
        } else {
            p.setDisplay(Commodity.START_NO);
        }
        commodityMapper.insert(p);
    }

    @Override
    public void delete(Long id) {
        commodityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Commodity p) {
        commodityMapper.updateByPrimaryKey(p);
    }

    @Override
    public Commodity get(Long id) {
        return commodityMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Commodity> list() {
        return commodityMapper.selectAll();
    }

    @Override
    public PageResult queryPage(CommodityQueryObject qo) {
        long count = commodityMapper.queryCount(qo);
        if (count <= 0L) {

            return new PageResult(0L, Collections.EMPTY_LIST);
        }
        List<Commodity> result = commodityMapper.queryData(qo);
        return new PageResult(count, result);
    }
}

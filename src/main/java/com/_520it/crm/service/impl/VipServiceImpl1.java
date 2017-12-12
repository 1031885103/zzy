
package com._520it.crm.service.impl;

import com._520it.crm.domain.Vip;
import com._520it.crm.domain.Vip1;
import com._520it.crm.domain.VipLevel;
import com._520it.crm.mapper.VipMapper;
import com._520it.crm.mapper.VipMapper1;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.query.VipQueryObject;
import com._520it.crm.service.IVipService;
import com._520it.crm.service.IVipService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class VipServiceImpl1 implements IVipService1 {

    @Autowired
    private VipMapper1 vipMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return vipMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Vip1 record) {
        return vipMapper.insert(record);
    }

    @Override
    public Vip1 selectByPrimaryKey(Long id) {
        return vipMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Vip1> selectAll() {
        return vipMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Vip1 record) {
        return vipMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult queryPage(QueryObject qo) {
        return null;
    }

    @Override
    public Vip1 queryByVipCard(Long vipCard) {
        return null;
    }


}

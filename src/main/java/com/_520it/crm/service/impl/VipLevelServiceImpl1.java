package com._520it.crm.service.impl;

import com._520it.crm.domain.VipLevel;
import com._520it.crm.domain.VipLevel1;
import com._520it.crm.mapper.VipLevelMapper;
import com._520it.crm.mapper.VipLevelMapper1;
import com._520it.crm.service.IVipLevelService;
import com._520it.crm.service.IVipLevelService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VipLevelServiceImpl1 implements IVipLevelService1 {

    @Autowired
    private VipLevelMapper1 vipLevelMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return vipLevelMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(VipLevel1 record) {
        return vipLevelMapper.insert(record);
    }

    @Override
    public VipLevel1 selectByPrimaryKey(Long id) {
        return vipLevelMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<VipLevel1> selectAll() {
        return vipLevelMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(VipLevel1 record) {
        return vipLevelMapper.updateByPrimaryKey(record);
    }

    @Override
    public VipLevel1 queryLevelCount(Long selectId) {
        return vipLevelMapper.queryLevelCount(selectId);
    }
}

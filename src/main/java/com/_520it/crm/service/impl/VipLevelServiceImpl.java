package com._520it.crm.service.impl;

import com._520it.crm.domain.Vip;
import com._520it.crm.domain.VipLevel;
import com._520it.crm.mapper.VipLevelMapper;
import com._520it.crm.service.IVipLevelService;
import com._520it.crm.service.IVipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VipLevelServiceImpl implements IVipLevelService{

    @Autowired
    private VipLevelMapper vipLevelMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return vipLevelMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(VipLevel record) {
        return vipLevelMapper.insert(record);
    }

    @Override
    public VipLevel selectByPrimaryKey(Long id) {
        return vipLevelMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<VipLevel> selectAll() {
        return vipLevelMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(VipLevel record) {
        return vipLevelMapper.updateByPrimaryKey(record);
    }


}

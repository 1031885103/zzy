package com._520it.crm.service.impl;

import com._520it.crm.domain.Vip;
import com._520it.crm.mapper.VipMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.IVipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VipServiceImpl implements IVipService {
    @Autowired
    private VipMapper vipMapper;


    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(Vip record) {
        return 0;
    }

    @Override
    public Vip selectByPrimaryKey(Long id) {
        return vipMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Vip> selectAll() {
        return null;
    }

    @Override
    public int updateByPrimaryKey(Vip record) {
        return 0;
    }

    @Override
    public PageResult queryPage(QueryObject qo) {
        return null;
    }

    @Override
    public Vip queryByVipCard(Long vipCard) {
        return vipMapper.queryByVipCard(vipCard);
    }
}

package com._520it.crm.service.impl;

import com._520it.crm.domain.VipCard;
import com._520it.crm.domain.VipLevel;
import com._520it.crm.mapper.VipCardMapper;
import com._520it.crm.service.IVipCardService;
import com._520it.crm.service.IVipLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class VipCardServiceImpl implements IVipCardService{

    @Autowired
    private VipCardMapper vipCardMapper;



    @Override
    public int deleteByPrimaryKey(Long id) {
        return vipCardMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(VipCard record) {
        return vipCardMapper.insert(record);
    }

    @Override
    public VipCard selectByPrimaryKey(Long id) {
        return vipCardMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<VipCard> selectAll() {
        return vipCardMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(VipCard record) {
        return vipCardMapper.updateByPrimaryKey(record);
    }

    @Override
    public VipCard queryCardMsgByVipId(Long vipId) {
        return vipCardMapper.queryCardMsgByVipId(vipId);
    }

    @Override
    public VipCard selectVipMoney(Long vipId) {
        return vipCardMapper.selectVipMoney(vipId);
    }

    @Override
    public int updateByVipId(BigDecimal cardMoney, Long vipId) {
        return vipCardMapper.updateByVipId(cardMoney,vipId);
    }



}

package com._520it.crm.service.impl;

import com._520it.crm.domain.VipCard;
import com._520it.crm.domain.VipCard1;
import com._520it.crm.mapper.VipCardMapper1;
import com._520it.crm.service.IVipCardService;
import com._520it.crm.service.IVipCardService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class VipCardServiceImpl1 implements IVipCardService1 {

    @Autowired
    private VipCardMapper1 vipCardMapper;


    @Override
    public int deleteByPrimaryKey(Long id) {
        return vipCardMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(VipCard1 record) {
        return vipCardMapper.insert(record);
    }

    @Override
    public VipCard1 selectByPrimaryKey(Long id) {
        return vipCardMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<VipCard1> selectAll() {
        return vipCardMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(VipCard1 record) {
        return vipCardMapper.updateByPrimaryKey(record);
    }

    @Override
    public VipCard1 queryCardMsgByVipId(Long vipId) {
        return vipCardMapper.queryCardMsgByVipId(vipId);
    }

    @Override
    public VipCard1 selectVipMoney(Long vipId) {
        return vipCardMapper.selectVipMoney(vipId);
    }

    @Override
    public int updateByVipId(BigDecimal cardMoney, Long vipId) {
        return vipCardMapper.updateByVipId(cardMoney, vipId);
    }


}

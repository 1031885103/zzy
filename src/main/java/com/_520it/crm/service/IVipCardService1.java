package com._520it.crm.service;

import com._520it.crm.domain.VipCard;
import com._520it.crm.domain.VipCard1;

import java.math.BigDecimal;
import java.util.List;

public interface IVipCardService1 {
    int deleteByPrimaryKey(Long id);

    int insert(VipCard1 record);

    VipCard1 selectByPrimaryKey(Long id);

    List<VipCard1> selectAll();

    int updateByPrimaryKey(VipCard1 record);

    VipCard1 queryCardMsgByVipId(Long vipId);

    VipCard1 selectVipMoney(Long vipId);

    int updateByVipId(BigDecimal cardMoney, Long vipId);

}

package com._520it.crm.service;

import com._520it.crm.domain.VipCard;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface IVipCardService {
    int deleteByPrimaryKey(Long id);

    int insert(VipCard record);

    VipCard selectByPrimaryKey(Long id);

    List<VipCard> selectAll();

    int updateByPrimaryKey(VipCard record);

    VipCard queryCardMsgByVipId(Long vipId);

    VipCard selectVipMoney(Long vipId);

    int updateByVipId(BigDecimal cardMoney, Long vipId);

}

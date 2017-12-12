package com._520it.crm.mapper;

import com._520it.crm.domain.VipCard;
import com._520it.crm.domain.VipCard1;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface VipCardMapper1 {
    int deleteByPrimaryKey(Long id);

    int insert(VipCard1 record);

    VipCard1 selectByPrimaryKey(Long id);

    List<VipCard1> selectAll();

    int updateByPrimaryKey(VipCard1 record);


    void subMoney(@Param("amount") BigDecimal amount, @Param("vipId") Long id);

    VipCard1 queryCardMsgByVipId(Long vipId);

    VipCard1 selectVipMoney(Long vipId);

    int updateByVipId(@Param("cardMoney") BigDecimal cardMoney3, @Param("vipId") Long vipId);



}
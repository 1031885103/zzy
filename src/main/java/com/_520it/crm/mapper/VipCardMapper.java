package com._520it.crm.mapper;

import com._520it.crm.domain.VipCard;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface VipCardMapper {
    int deleteByPrimaryKey(Long id);

    int insert(VipCard record);

    VipCard selectByPrimaryKey(Long id);

    List<VipCard> selectAll();

    int updateByPrimaryKey(VipCard record);


    void subMoney(@Param("amount") BigDecimal amount,@Param("vipId") Long id);

    VipCard queryCardMsgByVipId(Long vipId);

    VipCard selectVipMoney(Long vipId);

    int updateByVipId(@Param("cardMoney") BigDecimal cardMoney3 , @Param("vipId") Long vipId);



}
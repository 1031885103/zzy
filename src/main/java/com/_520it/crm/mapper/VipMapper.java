package com._520it.crm.mapper;

import com._520it.crm.domain.Vip;
import java.util.List;

public interface VipMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Vip record);

    Vip selectByPrimaryKey(Long id);

    List<Vip> selectAll();

    int updateByPrimaryKey(Vip record);

    Vip queryByVipCard(Long vipCard);
}
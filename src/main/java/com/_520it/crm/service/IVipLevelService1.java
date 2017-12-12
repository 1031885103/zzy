package com._520it.crm.service;

import com._520it.crm.domain.VipLevel;
import com._520it.crm.domain.VipLevel1;

import java.util.List;

public interface IVipLevelService1 {
    int deleteByPrimaryKey(Long id);

    int insert(VipLevel1 record);

    VipLevel1 selectByPrimaryKey(Long id);

    List<VipLevel1> selectAll();

    int updateByPrimaryKey(VipLevel1 record);

    VipLevel1 queryLevelCount(Long selectId);
}

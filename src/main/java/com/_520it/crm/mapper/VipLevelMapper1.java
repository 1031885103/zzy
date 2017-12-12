package com._520it.crm.mapper;

import com._520it.crm.domain.VipLevel;
import com._520it.crm.domain.VipLevel1;

import java.util.List;

public interface VipLevelMapper1 {
    int deleteByPrimaryKey(Long id);

    int insert(VipLevel1 record);

    VipLevel1 selectByPrimaryKey(Long id);

    List<VipLevel1> selectAll();

    int updateByPrimaryKey(VipLevel1 record);

    VipLevel1 queryLevelCount(Long selectId);

}
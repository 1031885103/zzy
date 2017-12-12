package com._520it.crm.service;

import com._520it.crm.domain.VipLevel;

import java.util.List;

public interface IVipLevelService {
    int deleteByPrimaryKey(Long id);

    int insert(VipLevel record);

    VipLevel selectByPrimaryKey(Long id);

    List<VipLevel> selectAll();

    int updateByPrimaryKey(VipLevel record);

}

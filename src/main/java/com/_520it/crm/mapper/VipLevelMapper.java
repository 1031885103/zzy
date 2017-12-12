package com._520it.crm.mapper;

import com._520it.crm.domain.VipLevel;
import java.util.List;

public interface VipLevelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(VipLevel record);

    VipLevel selectByPrimaryKey(Long id);

    List<VipLevel> selectAll();

    int updateByPrimaryKey(VipLevel record);


}
package com._520it.crm.mapper;


import com._520it.crm.domain.Vip;
import com._520it.crm.domain.Vip1;

import java.util.List;

public interface VipMapper1 {
    int deleteByPrimaryKey(Long id);

    int insert(Vip1 record);

    Vip1 selectByPrimaryKey(Long id);

    List<Vip1> selectAll();

    int updateByPrimaryKey(Vip1 record);

}
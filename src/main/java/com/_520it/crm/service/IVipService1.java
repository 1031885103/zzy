package com._520it.crm.service;
import com._520it.crm.domain.Vip;
import com._520it.crm.domain.Vip1;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

import java.util.List;

public interface IVipService1 {
	int deleteByPrimaryKey(Long id);
    int insert(Vip1 record);
    Vip1 selectByPrimaryKey(Long id);
    List<Vip1> selectAll();
    int updateByPrimaryKey(Vip1 record);
	PageResult queryPage(QueryObject qo);


    Vip1 queryByVipCard(Long vipCard);
}

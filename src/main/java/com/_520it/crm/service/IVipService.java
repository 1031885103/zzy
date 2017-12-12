package com._520it.crm.service;
import com._520it.crm.domain.Vip;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

import java.util.List;

public interface IVipService {
	int deleteByPrimaryKey(Long id);
    int insert(Vip record);
    Vip selectByPrimaryKey(Long id);
    List<Vip> selectAll();
    int updateByPrimaryKey(Vip record);
	PageResult queryPage(QueryObject qo);


    Vip queryByVipCard(Long vipCard);
}

package com._520it.crm.service;

import com._520it.crm.domain.Rank;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.RankQueryObject;

import java.util.List;

public interface IRankService {
    
    void save(Rank v);
    
    void update(Rank v);
    
    void delete(Long id);

    Rank get(Long id);
    
    List<Rank> list();

    PageResult queryPage(RankQueryObject qo);
}

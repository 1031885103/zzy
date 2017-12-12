package com._520it.crm.service;

import com._520it.crm.domain.Money;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.MoneyQueryObject;

import java.util.List;

public interface IMoneyService {
    
    void save(Money p);
    
    void update(Money p);
    
    void delete(Long id);
    
    Money get(Long id);
    
    List<Money> list();

    PageResult queryPage(MoneyQueryObject qo);
}

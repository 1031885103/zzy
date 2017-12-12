package com._520it.crm.service;

import com._520it.crm.domain.Subbranch;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.SubbranchQueryObject;

import java.util.List;

public interface ISubbranchService {
    
    void save(Subbranch s);
    
    void update(Subbranch s);
    
    void delete(Long id);

    Subbranch get(Long id);
    
    List<Subbranch> list();

    PageResult queryPage(SubbranchQueryObject qo);
}

package com._520it.crm.service;

import com._520it.crm.domain.Vacate;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.VacateQueryObject;

import java.util.List;

public interface IVacateService {
    
    void save(Vacate p);
    
    void update(Vacate p);
    
    void delete(Long id);
    
    Vacate get(Long id);
    
    List<Vacate> list();

    PageResult queryPage(VacateQueryObject qo);
}

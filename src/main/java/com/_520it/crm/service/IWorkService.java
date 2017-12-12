package com._520it.crm.service;

import com._520it.crm.domain.Work;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

import java.util.List;

public interface IWorkService {

    void save(Work s);

    void update(Work s);

    void delete(Long id);

    Work get(Long id);

    List<Work> list();

    PageResult queryPage(QueryObject qo);
}

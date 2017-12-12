package com._520it.crm.service;

import com._520it.crm.domain.Log;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.LogQueryObject;

import java.util.List;

public interface ILogService {
    void save(Log log);

    List<Log> list();

    PageResult queryPage(LogQueryObject qo);
}

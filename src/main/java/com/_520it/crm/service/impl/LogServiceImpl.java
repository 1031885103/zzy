package com._520it.crm.service.impl;

import com._520it.crm.domain.Log;
import com._520it.crm.mapper.LogMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.LogQueryObject;
import com._520it.crm.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class LogServiceImpl implements ILogService {
    @Autowired
    private LogMapper logMapper;

    @Override
    public void save(Log log) {
        if (log.getDisplay() == 0) {
            log.setDisplay(Log.START_YES);
        } else {
            log.setDisplay(Log.START_NO);
        }
        logMapper.insert(log);
    }

    @Override
    public List<Log> list() {
        return logMapper.selectAll();
    }

    @Override
    public PageResult queryPage(LogQueryObject qo) {
        long count = logMapper.queryCount(qo);
        if(count<=0L){

            return new PageResult(0L, Collections.EMPTY_LIST);
            }
        List<Log> result = logMapper.queryData(qo);
        return new PageResult(count,result);
    }


}

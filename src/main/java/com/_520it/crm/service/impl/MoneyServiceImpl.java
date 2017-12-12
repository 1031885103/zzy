package com._520it.crm.service.impl;

import com._520it.crm.domain.Money;
import com._520it.crm.mapper.MoneyMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.MoneyQueryObject;
import com._520it.crm.service.IMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Service
public class MoneyServiceImpl implements IMoneyService {

    @Autowired
    private MoneyMapper moneyMapper;

    @Override
    public void save(Money money) {
        BigDecimal total = money.getTotal();
        BigDecimal salary = money.getSalary();
        BigDecimal every = money.getEvery();
        BigDecimal job = money.getJob();
        BigDecimal meal = money.getMeal();
        BigDecimal tel = money.getTel();
        BigDecimal proportion =  money.getProportion();
        BigDecimal traffic =  money.getTraffic();
        BigDecimal holiday =  money.getHoliday();
        total = salary.add(every.add(job).add(meal).add(tel).add(proportion).add(traffic)).subtract(holiday);
        money.setTotal(total);
        moneyMapper.insert(money);
    }

    @Override
    public void update(Money money) {
        moneyMapper.updateByPrimaryKey(money);
    }

    @Override
    public void delete(Long id) {
        moneyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Money get(Long id) {
        return moneyMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Money> list() {
        return moneyMapper.selectAll();
    }

    @Override
    public PageResult queryPage(MoneyQueryObject qo) {
        long count = moneyMapper.queryCount(qo);
        if(count<=0L){

            return new PageResult(0L, Collections.EMPTY_LIST);
            }
        List<Money> result = moneyMapper.queryData(qo);
        return new PageResult(count,result);
    }
}

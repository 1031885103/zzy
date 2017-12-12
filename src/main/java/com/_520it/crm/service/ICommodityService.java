package com._520it.crm.service;

import com._520it.crm.domain.Commodity;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.CommodityQueryObject;

import java.util.List;

public interface ICommodityService {

    void save(Commodity p);

    void delete(Long id);

    void update(Commodity p);

    Commodity get(Long id);

    List<Commodity> list();

    PageResult queryPage(CommodityQueryObject qo);
}

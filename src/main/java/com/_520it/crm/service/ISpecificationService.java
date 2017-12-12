package com._520it.crm.service;

import com._520it.crm.domain.Specification;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.SpecificationQueryObject;

import java.util.List;

public interface ISpecificationService {

    void save(Specification s);

    void update(Specification s);

    void delete(Long id);

    Specification get(Long id);

    List<Specification> list();

    PageResult queryPage(SpecificationQueryObject qo);
}

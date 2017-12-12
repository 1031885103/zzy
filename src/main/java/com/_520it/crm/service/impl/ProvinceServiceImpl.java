package com._520it.crm.service.impl;

import com._520it.crm.domain.Provinces;
import com._520it.crm.mapper.ProvincesMapper;
import com._520it.crm.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProvinceServiceImpl implements IProvinceService {

    @Autowired
    private ProvincesMapper provincesMapper;
    @Override
    public List<Provinces> provinceList() {
        return provincesMapper.selectAll();
    }
}

package com._520it.crm.service.impl;

import com._520it.crm.domain.Cities;
import com._520it.crm.mapper.CitiesMapper;
import com._520it.crm.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CityServiceImpl implements ICityService {
    @Autowired
    private CitiesMapper cityMapper;
    @Override
    public List<Cities> listByProvinceId(Long id) {
        return cityMapper.listByProvinceId(id);
    }
}

package com._520it.crm.service.impl;

import com._520it.crm.mapper.AreasMapper;
import com._520it.crm.service.IAreasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AreasService implements IAreasService {
    @Autowired
    private AreasMapper areasMapper;
    @Override
    public List<String> listByCityId(Long cityId) {
        return  areasMapper.listByCityId(cityId);
    }
}

package com._520it.crm.service;

import com._520it.crm.domain.Cities;

import java.util.List;

public interface ICityService {
    List<Cities> listByProvinceId(Long id);
}

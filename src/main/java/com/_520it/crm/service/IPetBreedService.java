package com._520it.crm.service;

import com._520it.crm.domain.PetBreed;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.PetBreedQueryObject;

import java.util.List;

public interface IPetBreedService {
    
    void save(PetBreed p);
    
    void update(PetBreed p);
    
    void delete(Long id);

    PetBreed get(Long id);
    
    List<PetBreed> list();

    PageResult queryPage(PetBreedQueryObject qo);
}

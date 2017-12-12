package com._520it.crm.domain;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class Department extends BaseDomain{
    private String name;
    private boolean state;
}
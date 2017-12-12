package com._520it.crm.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Supplier {
    public static final int START_YES=0;//是
    public static final int START_NO=1;//否

    private Long id;

    private String sn;

    private String name;

    private String contacts;

    private String phone;

    private String product;

    private int cooperation;

    private String advantage;

    private Integer display = START_YES;

}
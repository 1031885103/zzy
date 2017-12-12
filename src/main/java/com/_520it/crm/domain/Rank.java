package com._520it.crm.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Rank {
    public static final int START_YES=0;//是
    public static final int START_NO=1;//否

    private Long id;

    private String name;

    private String servediscount;

    private String productdiscount;

    private Integer enable = START_YES;


}
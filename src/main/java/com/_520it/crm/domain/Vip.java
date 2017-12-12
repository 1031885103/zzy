package com._520it.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Vip {
    private Long id;
    private String sn;
    private String address;
    private String name;
    private Long tel;
    private Integer gender;
    private Date birthday;
    private String remark;
    private VipLevel vipLevel;
    private VipCard vipCard;
}






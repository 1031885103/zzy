package com._520it.crm.query;

//通过宠物名称,会员手机号,备注查询待充值会员

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class PetQueryObject extends QueryObject {

    //宠物名称,手机号码,备注

    private String selectPetName;
    private String selectVipTel;
    private String selectVipRemark;


}

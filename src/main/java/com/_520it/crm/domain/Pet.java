package com._520it.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Setter@Getter@ToString
public class Pet {
    private Long petId;

    private String petName;                                                    //

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date petBirthday;                                                  //

    private Integer petGender;

    private String petRemark;

    private Integer petMaterial;                                                      //

    private String petColour;                                                     //

    private String petTrait;                                                        //

    private Integer petState;  //状态

    private Integer petAncestry;//血统有无                                             //

    private String petRegisterNumber;  //血统登记号                                  //

    private String petRegistryAddress; //血统登记处                                  //



    //宠物 会员(多对一)
    private Vip1 vips;

    //宠物品种(多对一)
    private PetCategory petCategory;

    //会员卡
    private VipCard1 vipCard;

    private VipLevel1 vipLevel;


}
package com._520it.crm.domain;


import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class PetCategory {
    private Long id;

    private String name;

    private String newName;

    private Integer enable; //是否启用

    private Long parentId;  //父菜单id

}
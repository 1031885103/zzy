package com._520it.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Setter@Getter
public class Employee implements Serializable {
	public static final int NORMAL = 0;//正常状态
	public static final int LEAVE = 1;//表示离职装填

    public static final int STATUS_NORMAL = 0;//店铺正常运营
    public static final int STATUS_CLOSE = 1;//店铺关闭
    private Long id;
    private String username;
    private String realname;
    private String password;
    private String tel;
    private String email;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date inputtime;
    private int state;
    private boolean admin;
    private Department dept;
    private List<Role> roles = new ArrayList<Role>();

    private int status;         //店铺状态

    private String address;     //店铺地址

    private String storeMaster; //店铺管理员

    private String storename;   //店铺名称
    private Integer age;        //年龄

    private String gender;      //性别
}
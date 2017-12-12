package com._520it.crm.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Setter@Getter
public class Vacate {
    public static final int START_OK = 0;//正常
    public static final int START_BAD = 1;//不正常

    public static final int START_YES=0;//是
    public static final int START_NO=1;//否

    private Long id;

    private String sn;//工号

    private Employee emp;//员工姓名

    private String type;//请假类型

    private String reason;//请假事由

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date begin;//开始时间

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date end;//结束时间

    private Integer duration;//请假时长

    private String remark;//领导意见

    private Integer normal = START_YES;//是否正常请假

    private Integer state = START_OK;//状态

    private String cancel;//取消原因

}
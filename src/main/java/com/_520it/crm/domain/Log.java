package com._520it.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Log {
    public static final int START_YES=0;//是
    public static final int START_NO=1;//否


    private Long id;

    private Employee opUser;

    private String opIp;

    private String opMac;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date opDate;

    private Integer display = START_YES;

}
package com._520it.crm.query;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class LogQueryObject extends QueryObject{
    private String keyword;//登录用户
    private Date beginTime;//开始时间
    private Date endTime;//结束时间
}

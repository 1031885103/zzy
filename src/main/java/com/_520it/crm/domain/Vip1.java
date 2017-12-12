
package com._520it.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ToString
@Setter
@Getter
public class Vip1 {
    private Long vipId;

    private String vipSn;

    private String vipAddress;

    private String vipName;

    private String vipTel;

    private Integer vipGender;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date vipBirthday;

    private String vipRemark;

    // 会员  --  会员等级 (多对一)
    private VipLevel vipLevel;

}


package com._520it.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Setter@Getter
public class SaleBill1 {
    private Long id;

    private String sn;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date opTime;

    private Byte state;

    private BigDecimal discount;

    private BigDecimal totalAmount;

    private BigDecimal realAmount;

    private Byte payMethod;

    private String remark;

    private Member member;

    private Employee saleman;

    private int totalNumber;


}
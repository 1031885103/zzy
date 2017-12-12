package com._520it.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class SaleBill {
    private Long id;

    private String sn;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date optime;

    private Byte state;

    private BigDecimal discount;

    private BigDecimal totalNumber;

    private BigDecimal totalAmount;

    private BigDecimal realAmount;

    private Byte payMethod;

    private String remark;

    private Long vipId;

    private Long salemanId;
    private Long vipCard;
    private Subbranch subBranch;
    private List<SaleBillItem> items = new ArrayList<>();


}
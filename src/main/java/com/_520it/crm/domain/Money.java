package com._520it.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class Money {
    private Long id;

    private String sn;

    private String name;

    private BigDecimal salary;

    private BigDecimal job;

    private BigDecimal traffic;

    private BigDecimal tel;

    private BigDecimal meal;

    private BigDecimal every;

    private BigDecimal proportion;

    private BigDecimal holiday;

    private BigDecimal total;

}
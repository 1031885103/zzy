package com._520it.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Setter@Getter
public class Work {
    private Long id;

    private String position;//职位

    private BigDecimal job;//岗位津贴

    private BigDecimal traffic;//交通补贴

    private BigDecimal tel;//话费补贴

    private BigDecimal meal;//餐补

    private BigDecimal every;//全勤

    private Double proportion;//提成比例

}
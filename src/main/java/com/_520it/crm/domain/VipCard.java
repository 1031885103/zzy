package com._520it.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class VipCard {
    private Long id;
    private BigDecimal money;
    private BigDecimal balance;
    private BigDecimal totalMoney;
    private Long vipId;
}


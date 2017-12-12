
package com._520it.crm.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Setter
@Getter
@ToString
public class VipLevel1 {

    private Long vleId;

    private String vleName;

    private BigDecimal vleServeDiscount;

    private BigDecimal vleProductDiscount;

    private int vleEnable;

}
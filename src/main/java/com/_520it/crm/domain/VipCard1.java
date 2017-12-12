
package com._520it.crm.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;


@Setter
@Getter
@ToString
public class VipCard1 {
    private Long cardId;

    private BigDecimal cardMoney;

    private BigDecimal cardBalance;

    private BigDecimal cardTotalMoney;


    //会员对象
    private Vip1 vip;


}

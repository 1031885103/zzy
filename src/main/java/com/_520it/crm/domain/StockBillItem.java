package com._520it.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class StockBillItem extends BaseDomain {
    public static final int STATE_NORMS = 0;
    public static final int STATE_QUIT = 1;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date inputTime;//录入时间

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date vdate;//入库时间

    private BigDecimal costPrice;//进货价格

    private BigDecimal amount;//数量

    private BigDecimal totalPrice;//总价格

    private Integer specification;//折零规格

    private String minunit;//最小单位

    private String maxunit;//最小单位
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date bornDate;//生产日期
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date safeDate;//保质期
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date warningDate;//预警日期

    private Integer warningNumber;//预警数量

    private String stockMethod;//出入库方式

    private Integer state = STATE_NORMS;//状态/0表示没有入库,1表示入库

    private Product product;//商品 ,一条明细对应一条商品
}
package com._520it.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OrderStockItem extends BaseDomain {
    public static final int STATE_NORMS = 0;//异常
    public static final int STATE_QUIT = 1;//已经发货
    private String subbranchName;//分店名称
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date vdate;//发货时间

    private Integer state = STATE_QUIT;//发货状态

    private String employeeName;//发货人

    private String remark;//备注

    private Product product;//商品

}
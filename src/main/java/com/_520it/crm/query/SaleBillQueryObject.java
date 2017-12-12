package com._520it.crm.query;

import com._520it.crm.util.DateUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by ruosu on 2017/11/10.
 */
@Setter @Getter @ToString
public class SaleBillQueryObject extends QueryObject {

    private Long VIPNumber;
    //@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;
    //@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;


    public Date getEndTime() {
        if (endTime != null)
        return DateUtil.getEndDate(endTime);

        return endTime;
    }

}

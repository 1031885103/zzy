package com._520it.crm.query;

import com._520it.crm.util.DateUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Setter @Getter
public class SaleDataQueryObject extends QueryObject {

    private String name;
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

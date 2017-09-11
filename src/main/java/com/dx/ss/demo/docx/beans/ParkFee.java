package com.dx.ss.demo.docx.beans;

import com.dx.ss.data.beans.DocumentBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

public class ParkFee extends DocumentBean{

    private Date entryTime;

    private Date exitTime;

    private Boolean isHoliday;

    private Integer holidayRule;

    private BigDecimal total;

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public Date getExitTime() {
        return exitTime;
    }

    public void setExitTime(Date exitTime) {
        this.exitTime = exitTime;
    }

    public Boolean getIsHoliday() {
        return isHoliday;
    }

    public void setIsHoliday(Boolean isHoliday) {
        this.isHoliday = isHoliday;
    }

    public Integer getHolidayRule() {
        return holidayRule;
    }

    public void setHolidayRule(Integer holidayRule) {
        this.holidayRule = holidayRule;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public ArrayList<String> propertiesAssembly() {
        return new ArrayList<String>() {{
            add("entryTime");
            add("exitTime");
            add("isHoliday");
            add("holidayRule");
            add("total");
        }};
    }
}

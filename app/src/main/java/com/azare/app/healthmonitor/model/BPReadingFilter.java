package com.azare.app.healthmonitor.model;

import java.io.Serializable;
import java.util.Date;

public class BPReadingFilter implements Serializable {

    private Date dateStart;
    private Date dateEnd;
    private BPFILTERTYPE filterType;

    public BPReadingFilter(BPFILTERTYPE filterType) {
        this.filterType = filterType;
        this.dateStart = null;
        this.dateEnd = null;
    }

    public BPReadingFilter(BPFILTERTYPE filterType,
                           Date dateStart, Date dateEnd) {
        this.filterType = filterType;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public Date getStartDate() {
        return this.dateStart;
    }

    public Date getEndDate() {
        return this.dateEnd;
    }

    public BPFILTERTYPE getFilterType() {
        return filterType;
    }

    public void setStartDate(Date dateStart) {
        this.dateStart = dateStart;
    }

    public void setEndDate(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public void setFilterType(BPFILTERTYPE bpfiltertype) {
        this.filterType = bpfiltertype;
    }

    public boolean isValid() {
        boolean valid = false;

        if (this.filterType == BPFILTERTYPE.NONE) {
            if (this.dateStart == null || this.dateEnd == null) {
                valid = true;
            }
        }

        if (this.filterType == BPFILTERTYPE.CUSTOM) {
            if (this.dateStart != null || this.dateEnd != null) {
                valid = true;
            }
        }

        if (this.filterType == BPFILTERTYPE.SPECIFIC) {
            if (this.dateStart != null || this.dateEnd == null) {
                valid = true;
            }
        }

        return valid;
    }
}

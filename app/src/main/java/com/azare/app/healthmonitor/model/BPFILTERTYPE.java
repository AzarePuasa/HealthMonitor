package com.azare.app.healthmonitor.model;

public enum BPFILTERTYPE {
    ALL("Show All Reading"),
    MONTH("Show Last 30 Days Reading"),
    WEEK("Show Last 7 Days Reading"),
    CUSTOM("Show Custom Period Reading");

    String statusMsg;

    BPFILTERTYPE(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public String getStatusMsg() {
        return statusMsg;
    }
}

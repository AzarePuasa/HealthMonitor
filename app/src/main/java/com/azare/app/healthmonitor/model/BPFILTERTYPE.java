package com.azare.app.healthmonitor.model;

public enum BPFILTERTYPE {
    ALL("Show All Reading"),
    CUSTOM("Show Custom Period Reading"),
    SPECIFIC("Show Specific Date Reading");

    String statusMsg;

    BPFILTERTYPE(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public String getStatusMsg() {
        return statusMsg;
    }
}

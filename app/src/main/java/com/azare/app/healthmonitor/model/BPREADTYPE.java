package com.azare.app.healthmonitor.model;

import java.util.ArrayList;
import java.util.List;

public enum BPREADTYPE {
    MORNING(7,13),
    AFTERNOON(13,19),
    EVENING(19,24);

    int iStartHour;
    int iEndHour;

    BPREADTYPE(int iStartHour, int iEndHour) {
        this.iStartHour = iStartHour;
        this.iEndHour = iEndHour;
    }

    public int getStartHour() {
        return iStartHour;
    }

    public int getEndHour() {
        return iEndHour;
    }
}

package com.azare.app.healthmonitor.model;

public class AfternoonBPReading extends BPReading {

    public AfternoonBPReading(int iDay, int iMonth, int iYear, int iSystolic,
                              int iDiastolic) {
        super(iDay, iMonth, iYear, iSystolic, iDiastolic, BPREADTYPE.AFTERNOON);
    }
}

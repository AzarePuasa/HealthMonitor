package com.azare.app.healthmonitor.model;

public class MorningBPReading extends BPReading {

    public MorningBPReading(int iDay, int iMonth, int iYear, int iSystolic,
                            int iDiastolic) {

        super(iDay, iMonth, iYear, iSystolic, iDiastolic, BPREADTYPE.MORNING);
    }
}

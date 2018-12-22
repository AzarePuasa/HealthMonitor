package com.azare.app.healthmonitor.model;

public class EveningBPReading extends BPReading {

    public EveningBPReading(int iDay, int iMonth, int iYear, int iSystolic,
                            int iDiastolic) {

        super(iDay, iMonth, iYear, iSystolic, iDiastolic, BPREADTYPE.EVENING);
    }
}

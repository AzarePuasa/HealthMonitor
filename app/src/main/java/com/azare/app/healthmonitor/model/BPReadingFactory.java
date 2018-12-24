package com.azare.app.healthmonitor.model;

public class BPReadingFactory {

    public static BPReading createBPReading(int iDay, int iMonth, int iYear, int iSystolic,
                                            int iDiastolic, BPREADTYPE bpReadType) {

        if (bpReadType == BPREADTYPE.MORNING) {
            return new MorningBPReading(iDay,iMonth,iYear,iSystolic,iDiastolic);
        }
        else if (bpReadType == BPREADTYPE.AFTERNOON) {
            return new AfternoonBPReading(iDay,iMonth,iYear,iSystolic,iDiastolic);
        }
        else if (bpReadType == BPREADTYPE.EVENING) {
            return new EveningBPReading(iDay,iMonth,iYear,iSystolic,iDiastolic);
        }

        return null;
    }
}

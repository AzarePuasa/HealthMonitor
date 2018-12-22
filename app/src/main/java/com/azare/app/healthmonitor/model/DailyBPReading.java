package com.azare.app.healthmonitor.model;

public class DailyBPReading {

    private MorningBPReading morningBP;
    private AfternoonBPReading afternoonBP;
    private EveningBPReading eveningBP;

    public DailyBPReading() {
        morningBP = null;
        afternoonBP = null;
        eveningBP = null;
    }

    public MorningBPReading getMorningBP() {
        return morningBP;
    }

    public AfternoonBPReading getAfternoonBP() {
        return afternoonBP;
    }

    public EveningBPReading getEveningBP() {
        return eveningBP;
    }

    public void setMorningBP(MorningBPReading morningBP) {
        this.morningBP = morningBP;
    }

    public void setAfternoonBP(AfternoonBPReading afternoonBP) {
        this.afternoonBP = afternoonBP;
    }

    public void setEveningBP(EveningBPReading eveningBP) {
        this.eveningBP = eveningBP;
    }
}

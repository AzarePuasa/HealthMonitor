package com.azare.app.healthmonitor;

import com.azare.app.healthmonitor.model.AfternoonBPReading;
import com.azare.app.healthmonitor.model.EveningBPReading;
import com.azare.app.healthmonitor.model.MorningBPReading;


/**
 * A Daily reading keeps record of readings for
 * one day i.e. morning, afternoon and evening.
 */
public class DailyBPReading {

    private String strDate;
    private MorningBPReading morningBP;
    private AfternoonBPReading afternoonBP;
    private EveningBPReading eveningBP;

    public DailyBPReading(String strDate) {

        this.strDate = strDate;
        morningBP = null;
        afternoonBP = null;
        eveningBP = null;
    }

    public String getDate() {
        return strDate;
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

    public String toString() {

        StringBuilder stb = new StringBuilder();

        stb.append("\nDate: ").append(this.strDate);
        stb.append("\nMorning: ").append(morningBP.getSystolic()).append("/").append(morningBP.getDiastolic());
        stb.append("\nAfternoon: ").append(afternoonBP.getSystolic()).append("/").append(afternoonBP.getDiastolic());
        stb.append("\nEvening: ").append(eveningBP.getSystolic()).append("/").append(eveningBP.getDiastolic());

        return stb.toString();
    }
}

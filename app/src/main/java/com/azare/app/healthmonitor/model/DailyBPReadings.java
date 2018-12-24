package com.azare.app.healthmonitor.model;

import java.util.ArrayList;
import java.util.List;

public class DailyBPReadings {

    private List<DailyBPReading> lDailyReadings;

    public DailyBPReadings() {
        lDailyReadings = new ArrayList<DailyBPReading>();
    }

    private boolean createDailyReading(String strDate) {
        if (getDailyBPReading(strDate) == null) {
            return lDailyReadings.add(new DailyBPReading(strDate));
        }
        return false;
    }

    public boolean contains(String strDate) {
        return false;
    }

    private DailyBPReading getDailyBPReading(String strDate) {
        for (DailyBPReading dailyBPReading : lDailyReadings) {
            if(dailyBPReading.getDate() == strDate) {
                return dailyBPReading;
            }
        }

        return null;
    }

    public void addMorningReading(String strDate,
                                  MorningBPReading morningBPReading) throws Exception {
        if (getDailyBPReading(strDate) == null) {
            if (!createDailyReading(strDate)) {
                throw new Exception("Fail To add Morning BP Reading for: " + strDate);
            }
        }

        DailyBPReading dailyBPreading = getDailyBPReading(strDate);
        dailyBPreading.setMorningBP(morningBPReading);

    }

    public void addAfternoonReading(String strDate,
                                    AfternoonBPReading afternoonBPReading) throws Exception {
        if (getDailyBPReading(strDate) == null) {
            if (!createDailyReading(strDate)) {
                throw new Exception("Fail To add Afternoon BP Reading for: " + strDate);
            }
        }

        DailyBPReading dailyBPreading = getDailyBPReading(strDate);
        dailyBPreading.setAfternoonBP(afternoonBPReading);
    }

    public void addEveningReading(String strDate,
                                  EveningBPReading eveningBPReading) throws Exception {
        if (getDailyBPReading(strDate) == null) {
            if (!createDailyReading(strDate)) {
                throw new Exception("Fail To add Evening BP Reading for: " + strDate);
            }
        }

        DailyBPReading dailyBPreading = getDailyBPReading(strDate);
        dailyBPreading.setEveningBP(eveningBPReading);
    }

    public List<DailyBPReading> getDailyReadings() {
        return lDailyReadings;
    }


    /**
     * A Daily reading keeps record of readings for
     * one day i.e. morning, afternoon and evening.
     */
    private class DailyBPReading {

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

    }


}

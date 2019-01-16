package com.azare.app.healthmonitor.model;

import com.azare.app.healthmonitor.DailyBPReading;

import java.util.ArrayList;
import java.util.List;

public class DailyBPReadings {

    private List<DailyBPReading> lDailyReadings;

    public DailyBPReadings() {
        lDailyReadings = new ArrayList<DailyBPReading>();
    }

    public boolean createDailyReading(String strDate) {
        if (getDailyBPReading(strDate) == null) {
            return lDailyReadings.add(new DailyBPReading(strDate));
        }
        return false;
    }

    public boolean contains(String strDate) {
        return false;
    }

    public DailyBPReading getDailyBPReading(String strDate) {
        for (DailyBPReading dailyBPReading : lDailyReadings) {
            if(dailyBPReading.getDate().equals(strDate)) {
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

    public void addDailyBPReading( DailyBPReading dailyBPReading) {
        lDailyReadings.add(dailyBPReading);
    }

    public void clear() {
        this.lDailyReadings.clear();
    }

    public List<DailyBPReading> getDailyReadings() {
        return lDailyReadings;
    }

    public String toString() {

        StringBuilder stb = new StringBuilder();

        if (lDailyReadings.size() > 0) {
            for (DailyBPReading dailyBPReading : lDailyReadings ) {
                stb.append(dailyBPReading.toString());
            }
        }

        return stb.toString();
    }
}

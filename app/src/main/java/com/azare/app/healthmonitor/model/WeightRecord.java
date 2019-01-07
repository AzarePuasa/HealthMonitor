package com.azare.app.healthmonitor.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WeightRecord {

    private int m_iDay;
    private int m_iMonth;
    private int m_iYear;
    private int m_iWeight;
    private long m_Timestamp;

    public WeightRecord(int m_iDay, int m_iMonth, int m_iYear,
                        int m_iWeight, long m_Timestamp) {
        this.m_iDay = m_iDay;
        this.m_iMonth = m_iMonth;
        this.m_iYear = m_iYear;
        this.m_iWeight = m_iWeight;
        this.m_Timestamp = m_Timestamp;
    }

    public int getDay() {
        return m_iDay;
    }

    public void setDay(int m_iDay) {
        this.m_iDay = m_iDay;
    }

    public int getMonth() {
        return m_iMonth;
    }

    public void setMonth(int m_iMonth) {
        this.m_iMonth = m_iMonth;
    }

    public int getM_iYear() {
        return m_iYear;
    }

    public void setYear(int m_iYear) {
        this.m_iYear = m_iYear;
    }

    public int getWeight() {
        return m_iWeight;
    }

    public void setWeight(int m_iWeight) {
        this.m_iWeight = m_iWeight;
    }

    public long getTimestamp() {
        return m_Timestamp;
    }

    public void setTimestamp(long m_Timestamp) {
        this.m_Timestamp = m_Timestamp;
    }

    public String toString() {
        StringBuilder stbBPReading = new StringBuilder();

        stbBPReading.append("\nDate(Day/Mth/Year): ").append(m_iDay)
                .append("/").append(m_iMonth)
                .append("/").append(m_iYear);

        stbBPReading.append("\tWeight: ").append(m_iWeight);

        Date date = new Date(m_Timestamp);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aaa");

        stbBPReading.append("\tDate Created: ").append(sdf.format(date));

        return stbBPReading.toString();
    }
}

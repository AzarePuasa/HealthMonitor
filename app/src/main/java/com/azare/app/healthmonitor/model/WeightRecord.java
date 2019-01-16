package com.azare.app.healthmonitor.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WeightRecord implements Serializable {

    private int m_iDay;
    private int m_iMonth;
    private int m_iYear;
    private double m_iWeight;
    private long m_Timestamp;

    public WeightRecord(int m_iDay, int m_iMonth, int m_iYear,
                        double m_iWeight, long m_Timestamp) {
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

    public int getYear() {
        return m_iYear;
    }

    public void setYear(int m_iYear) {
        this.m_iYear = m_iYear;
    }

    public double getWeight() {
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
        StringBuilder stbWeightRecord = new StringBuilder();

        stbWeightRecord.append("\nDate(Day/Mth/Year): ").append(m_iDay)
                .append("/").append(m_iMonth)
                .append("/").append(m_iYear);

        stbWeightRecord.append("\tWeight: ").append(m_iWeight);

        Date date = new Date(m_Timestamp);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aaa");

        stbWeightRecord.append("\tDate Created: ").append(sdf.format(date));

        return stbWeightRecord.toString();
    }
}

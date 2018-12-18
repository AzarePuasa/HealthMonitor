package com.azare.app.healthmonitor.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BPReading {
    private int m_iDay;
    private int m_iMonth;
    private int m_iYear;
    private int m_iSystolic;
    private int m_iDiastolic;
    private BPREADTYPE m_bpReadType;
    private long m_Timestamp;

    public BPReading() {

    }

    public BPReading(int iDay, int iMonth, int iYear, int iSystolic,
                     int iDiastolic, BPREADTYPE bpReadType) {
        this.m_iDay = iDay;
        this.m_iMonth = iMonth;
        this.m_iYear = iYear;
        this.m_iSystolic = iSystolic;
        this.m_iDiastolic = iDiastolic;
        this.m_bpReadType = bpReadType;

        //integer timestamp.
        this.m_Timestamp = System.currentTimeMillis();
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

    public int getSystolic() {

        return m_iSystolic;
    }

    public void setSystolic(int m_iSystolic) {

        this.m_iSystolic = m_iSystolic;
    }

    public int getDiastolic() {

        return m_iDiastolic;
    }

    public void setDiastolic(int m_iDiastolic) {

        this.m_iDiastolic = m_iDiastolic;
    }

    public BPREADTYPE getReadType() {

        return m_bpReadType;
    }

    public void setReadType(BPREADTYPE m_bpReadType) {

        this.m_bpReadType = m_bpReadType;
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

        stbBPReading.append("\tDiastolic: ").append(m_iDiastolic);
        stbBPReading.append("\tSystolic: ").append(m_iSystolic);
        stbBPReading.append("\tReading Type: ").append(m_bpReadType);

        Date date = new Date(m_Timestamp);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aaa");

        stbBPReading.append("\tDate Created: ").append(sdf.format(date));

        return stbBPReading.toString();
    }
}

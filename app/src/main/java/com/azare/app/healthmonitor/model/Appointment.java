package com.azare.app.healthmonitor.model;

import com.azare.app.healthmonitor.utils.HMUtils;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Appointment implements Serializable {

    private int m_iDay;
    private int m_iMonth;
    private int m_iYear;
    private int m_iHour;
    private int m_iMinutes;
    private String m_strLocation;
    private String m_strPurpose;
    private long m_Timestamp;

    public Appointment(int iDay, int iMonth, int iYear, int iHour, int iMinutes) {
        this.m_iDay = iDay;
        this.m_iMonth = iMonth;
        this.m_iYear = iYear;
        this.m_iHour = iHour;
        this.m_iMinutes = iMinutes;
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

    public int getHour() {
        return m_iHour;
    }

    public void setHour(int m_iHour) {
        this.m_iHour = m_iHour;
    }

    public int getMinutes() {
        return m_iMinutes;
    }

    public void setMinutes(int m_iMinutes) {
        this.m_iMinutes = m_iMinutes;
    }

    public String getLocation() {
        return m_strLocation;
    }

    public void setLocation(String m_strLocation) {
        this.m_strLocation = m_strLocation;
    }

    public String getPurpose() {
        return m_strPurpose;
    }

    public void setPurpose(String m_strPurpose) {
        this.m_strPurpose = m_strPurpose;
    }

    public long getTimestamp() {
        return m_Timestamp;
    }

    public String getApptDateTimeString() {
        return this.m_iDay + "/" + this.m_iMonth + "/" + this.m_iYear +
                " " + m_iHour + ":" + m_iMinutes;
    }

    public Date getApptDateTime() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(HMUtils.DATE_TIME_FORMAT_NO_SEC);

        return sdf.parse(getApptDateTimeString());
    }

    public void setTimestamp(long m_Timestamp) {
        this.m_Timestamp = m_Timestamp;
    }
}

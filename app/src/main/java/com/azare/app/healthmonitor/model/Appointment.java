package com.azare.app.healthmonitor.model;

import com.azare.app.healthmonitor.utils.HMUtils;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Appointment implements Serializable {

    private long appt_id;
    private int m_iDay;
    private int m_iMonth;
    private int m_iYear;
    private String m_strHour;
    private String m_strMinutes;
    private String m_strLocation;
    private String m_strPurpose;
    private long m_Timestamp;

    public Appointment(int iDay, int iMonth, int iYear, String strHour, String strMinutes) {
        this.appt_id = -1;
        this.m_iDay = iDay;
        this.m_iMonth = iMonth;
        this.m_iYear = iYear;
        this.m_strHour = strHour;
        this.m_strMinutes = strMinutes;
    }

    public long getId() {
        return this.appt_id;
    }

    public void setId(long appt_id) {
        this.appt_id = appt_id;
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

    public String getHour() {
        return m_strHour;
    }

    public void setHour(String strHour) {
        this.m_strHour = strHour;
    }

    public String getMinutes() {
        return m_strMinutes;
    }

    public void setMinutes(String strMinutes) {
        this.m_strMinutes = strMinutes;
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
                " " + m_strHour + ":" + m_strMinutes;
    }

    public Date getApptDateTime() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(HMUtils.DATE_TIME_FORMAT_NO_SEC);

        return sdf.parse(getApptDateTimeString());
    }

    public void setTimestamp(long m_Timestamp) {
        this.m_Timestamp = m_Timestamp;
    }

    public String toString() {
        StringBuilder stbAppointment = new StringBuilder();

        stbAppointment.append("\nDate/Time: ").append(getApptDateTimeString());

        stbAppointment.append("\tPurpose: ").append(m_strPurpose);
        stbAppointment.append("\tLocation: ").append(m_strLocation);

        Date date = new Date(m_Timestamp);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");

        stbAppointment.append("\tDate Created: ").append(sdf.format(date));

        return stbAppointment.toString();
    }
}

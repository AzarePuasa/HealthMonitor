package com.azare.app.healthmonitor;

import java.sql.Timestamp;

public class BPReading {
    private int m_iSystolic;
    private int m_iDiastolic;
    private BPREADTYPE m_bpReadType;
    private Timestamp m_Timestamp;


    public BPReading(int iSystolic, int iDiastolic, BPREADTYPE bpReadType, Timestamp timestamp) {
        this.m_iSystolic = iSystolic;
        this.m_iDiastolic = iDiastolic;
        this.m_bpReadType = bpReadType;
        this.m_Timestamp = timestamp;
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

    public Timestamp getTimestamp() {
        return m_Timestamp;
    }

    public void setTimestamp(Timestamp m_Timestamp) {
        this.m_Timestamp = m_Timestamp;
    }

    public String toString() {
        StringBuilder stbBPReading = new StringBuilder();

        stbBPReading.append("\nDiastolic: ").append(m_iDiastolic);
        stbBPReading.append("\tSystolic: ").append(m_iSystolic);
        stbBPReading.append("\tReading Type: ").append(m_bpReadType);
        stbBPReading.append("\tTime Stamp: ").append(m_Timestamp);

        return stbBPReading.toString();
    }
}

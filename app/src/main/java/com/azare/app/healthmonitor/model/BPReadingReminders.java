package com.azare.app.healthmonitor.model;

public class BPReminders {

    private BPReminder m_Morning;
    private BPReminder m_Afternoon;
    private BPReminder m_Evening;

    public BPReminders() {

        m_Morning = new BPReminder(BPREADTYPE.MORNING,7);
        m_Afternoon = new BPReminder(BPREADTYPE.AFTERNOON,13);
        m_Evening = new BPReminder(BPREADTYPE.EVENING,19);
    }

    public int getReminder(BPREADTYPE bpreadtype) {

        if (bpreadtype == BPREADTYPE.MORNING) {
            return m_Morning.getReminder();
        } else if (bpreadtype == BPREADTYPE.AFTERNOON) {
            return m_Afternoon.getReminder();
        } else if (bpreadtype == BPREADTYPE.EVENING) {
            return m_Evening.getReminder();
        } else {
            return -1;
        }
    }

    public void setReminder(BPREADTYPE bpreadtype, int iReminder) {
        if (bpreadtype == BPREADTYPE.MORNING) {
            m_Morning.setReminder(iReminder);
        } else if (bpreadtype == BPREADTYPE.AFTERNOON) {
            m_Afternoon.setReminder(iReminder);
        } else if (bpreadtype == BPREADTYPE.EVENING) {
            m_Evening.setReminder(iReminder);
        }
    }

    
    class BPReminder {
        private BPREADTYPE m_BPReadType;
        private int m_iReminder;

        public BPReminder(BPREADTYPE bpreadtype, int iReminder) {
            m_BPReadType = bpreadtype;
            m_iReminder = iReminder;
        }

        public BPREADTYPE getReadType() {
            return m_BPReadType;
        }

        public int getReminder() {
            return m_iReminder;
        }

        public void setType(BPREADTYPE bpreadtype) {
            m_BPReadType = bpreadtype;
        }

        public void setReminder(int iReminder) {
            m_iReminder = iReminder;

        }
    }


}

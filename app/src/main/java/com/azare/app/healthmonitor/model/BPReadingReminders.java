package com.azare.app.healthmonitor.model;

public class BPReadingReminders {

    private BPReadingReminder m_Morning;
    private BPReadingReminder m_Afternoon;
    private BPReadingReminder m_Evening;

    public static final String[] MORNINGREMINDERS =
            new String[]{"7:00","8:00","9:00","10:00","11:00","12:00"};

    public static final String[] AFTERNOONREMINDER =
            new String[]{"13:00","14:00","15:00","16:00","17:00","18:00"};

    public static final String[] EVENINGREMINDER =
            new String[]{"19:00","20:00","21:00","22:00","23:00"};

    public BPReadingReminders() {

        m_Morning = new BPReadingReminder(BPREADTYPE.MORNING,"-1");
        m_Afternoon = new BPReadingReminder(BPREADTYPE.AFTERNOON,"-1");
        m_Evening = new BPReadingReminder(BPREADTYPE.EVENING,"-1");
    }

    public String getReminder(BPREADTYPE bpreadtype) {
        if (bpreadtype == BPREADTYPE.MORNING) {
            return m_Morning.getReminder();
        } else if (bpreadtype == BPREADTYPE.AFTERNOON) {
            return m_Afternoon.getReminder();
        } else if (bpreadtype == BPREADTYPE.EVENING) {
            return m_Evening.getReminder();
        } else {
            return "-1";
        }
    }

    public void setReminder(BPREADTYPE bpreadtype, String strReminder) {
        if (bpreadtype == BPREADTYPE.MORNING) {
            m_Morning.setReminder(strReminder);
        } else if (bpreadtype == BPREADTYPE.AFTERNOON) {
            m_Afternoon.setReminder(strReminder);
        } else if (bpreadtype == BPREADTYPE.EVENING) {
            m_Evening.setReminder(strReminder);
        }
    }

    public String toString() {
        StringBuilder stb = new StringBuilder();

        stb.append(m_Morning);
        stb.append("\n").append(m_Afternoon);
        stb.append("\n").append(m_Evening);

        return stb.toString();
    }

    class BPReadingReminder {
        private BPREADTYPE m_BPReadType;
        private String m_strReminder;

        public BPReadingReminder(BPREADTYPE bpreadtype, String strReminder) {
            m_BPReadType = bpreadtype;
            m_strReminder = strReminder;
        }

        public BPREADTYPE getReadType() {
            return m_BPReadType;
        }

        public String getReminder() {
            return m_strReminder;
        }

        public void setType(BPREADTYPE bpreadtype) {
            m_BPReadType = bpreadtype;
        }

        public void setReminder(String strReminder) {
            m_strReminder = strReminder;

        }

        public String toString() {
            StringBuilder stb = new StringBuilder();

            stb.append("Type: ").append(m_BPReadType.name());
            stb.append("\tReminder Time: ").append(m_strReminder);

            return stb.toString();
        }
    }
}

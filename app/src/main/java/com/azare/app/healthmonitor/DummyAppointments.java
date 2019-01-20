package com.azare.app.healthmonitor;

import com.azare.app.healthmonitor.model.Appointment;
import com.azare.app.healthmonitor.utils.HMUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DummyAppointments implements IDummyReading {

    private List<Appointment> lAppointments_completed;
    private List<Appointment> lAppointments_upcoming;

    public static String[] dummyApptHours = new String[]{"09:30","10:00", "11:00","13:30","14:30","15:30"};;

    public static String[] dummyPurpose = new String[]{"Routine Checkup", "Glucose Level Review", "Eye Checkup",
            "Blood Pressure Review", "Specialist Doctor Appointment", "Collect Medicine Supplies"};;

    enum APPTLOCATIONS {
        SGH("Singapore General Hospital"),
        NUH("National University Hospital"),
        NTFH("Ng Teng Fong Hospital"),
        KTPH("Khoo Teck Phuat Hospital"),
        JRP("Jurong Polyclinic"),
        BBP("Bukit Batok Polyclinic");

        private String full;

        APPTLOCATIONS(String full) {
            this.full = full;
        }
    }

    public DummyAppointments () {
        lAppointments_completed = new ArrayList<Appointment>();
        lAppointments_upcoming = new ArrayList<Appointment>();
    }

    public List<Appointment> getCompletedAppts() {
        return lAppointments_completed;
    }

    public List<Appointment> getUpcomingAppts() {
        return lAppointments_upcoming;
    }

    @Override
    public void generateDummyReading() {

        //generate 3 appointment that is before today i.e. Completed Appointment.


        int[] iDaysBefore = new int[3];

        iDaysBefore[0] = HMUtils.getRandomizedInt(1,10);
        iDaysBefore[1] = HMUtils.getRandomizedInt(11,20);
        iDaysBefore[2] = HMUtils.getRandomizedInt(21,30);

        //generate 3 appointment that is after today i.e. Upcoming Appointment.
        lAppointments_completed = generateDummyAppointments(iDaysBefore, true);

        //generate 2 appointment that is after today i.e. Upcoming Appointment.
        int[] iDaysAfter = new int[2];
        iDaysAfter[0] = HMUtils.getRandomizedInt(1,10);
        iDaysAfter[1] = HMUtils.getRandomizedInt(11,20);

        lAppointments_upcoming = generateDummyAppointments(iDaysAfter, false);
    }

    private List<Appointment> generateDummyAppointments(int[] iDaysArr, boolean isNegative) {
        List<Appointment> lAppointments = new ArrayList<Appointment>();

        Date dtToday = HMUtils.getDateToday();
        Calendar cal = Calendar.getInstance();

        for (int daysoffset : iDaysArr) {

            if (isNegative) {
                daysoffset *= -1;
            }
            cal.setTime(dtToday);

            cal.add(Calendar.DATE, daysoffset);

            int[] iDate = HMUtils.getCurrentDateArr(cal.getTime());

            String strDummyTime = generateRandomTime(HMUtils.getRandomizedInt(0,5));
            String strDummyLocation = generateRamdomLocation(HMUtils.getRandomizedInt(0,5));
            String strDummyPurpose = generatePurpose(HMUtils.getRandomizedInt(0,5));

            String strHour = strDummyTime.split(":")[0];
            String strMinutes = strDummyTime.split(":")[1];

            Appointment appointment = new Appointment(iDate[0],iDate[1],iDate[2],strHour,strMinutes);

            appointment.setPurpose(strDummyPurpose);
            appointment.setLocation(strDummyLocation);

            //generate dummy time stamp to be use for all. Current date - 60 day.
            cal.setTime(dtToday);

            cal.add(Calendar.DATE, -60);

            Long timestamp = cal.getTimeInMillis();

            appointment.setTimestamp(timestamp);

            lAppointments.add(appointment);
        }

        return lAppointments;
    }

    private String generateRandomTime(int random) {
        if (random < dummyApptHours.length) {
            return dummyApptHours[random];
        }

        return dummyApptHours[0];
    }

    private String generateRamdomLocation(int random) {
        APPTLOCATIONS[] locations = APPTLOCATIONS.values();

        if (random < locations.length) {
            return locations[random].name();
        }

        return locations[0].name();
    }

    private String generatePurpose(int random) {
        if (random < dummyPurpose.length) {
            return dummyPurpose[random];
        }
        return dummyPurpose[0];
    }
}

package com.azare.app.healthmonitor.utils;

import android.util.Log;

import com.azare.app.healthmonitor.model.BPREADTYPE;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class HMUtils {

    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public static final String DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";
    public static final String DATE_TIME_FORMAT_NO_SEC = "dd/MM/yyyy HH:mm";
    public static final String LOGTAG = "Health Monitor";
    public static final String DATE_SEPERATOR = "/";

    public static final String[] MONTHS = {"Jan", "Feb", "Mar", "Apr", "May",
    "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    /*
    Generate a list containing a hourly timestamp for the given
    date and period.
     */
    public static long[] generateHourlyTS(Date date, BPREADTYPE readType) {

        //TODO: generate random timestamp for morning Read Type.

        //https://www.sanfoundry.com/java-program-generate-date-between-given-range/

        //generate an array of 20 random long values that represents
        //the time within the Morning period.

        //Generate a random number of between 1 to 20.

        //return the value for the array in that position.
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, readType.getStartHour());

        Log.i("Health Monitor", readType.name().toLowerCase() + " Start Time: " + cal.getTime());

        int iCount = readType.getEndHour()-readType.getStartHour();
        long[] millis = new long[iCount];

        Date dtmilli = cal.getTime();
        long startMilli = dtmilli.getTime();
        long currentmilli = 0;

        for (int i=0; i < iCount; i++){
            long currentMilli = startMilli + currentmilli;
            millis[i] = currentMilli;
            currentmilli += 3600000;
        }

        return millis;
    }

    public static int getRandomizedInt(int iMin, int iMax) {
//        Log.i("Health Monitor", "Min: " + iMin);
//        Log.i("Health Monitor", "Max: " + iMax);

        Random rand = new Random();

        int iRand = (int)rand.nextInt((iMax - iMin) + 1) + iMin;

        return iRand;
    }

    public static String getCurrentDate(Date date) {

        SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");

        String strDate = dateformat.format(date);

        return strDate;
    }

    public static int[] getCurrentDateArr(Date date) {

        SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");

        String strDate = dateformat.format(date);

        int[] iDate = new int[3];

        iDate[0] = Integer.parseInt(strDate.split("/")[0]);
        iDate[1] = Integer.parseInt(strDate.split("/")[1]);
        iDate[2] = Integer.parseInt(strDate.split("/")[2]);

        return iDate;
    }

    public static String getCurrentTime(Date date) {

        SimpleDateFormat dateformat = new SimpleDateFormat("HH:mm:ss aa");


        String strTime = dateformat.format(date);

        return strTime;
    }

    public static Date strToDate(String strDate, SimpleDateFormat sdf) {

        Date date = new Date();

        try {
            date = sdf.parse(strDate);
        } catch (ParseException pex) {
            Log.e(HMUtils.LOGTAG, "Fail to parse date");
        }

        return date;
    }

    public static Date getDateToday() {
        return new Date(System.currentTimeMillis());
    }
}

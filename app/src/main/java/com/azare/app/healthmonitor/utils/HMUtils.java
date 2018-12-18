package com.azare.app.healthmonitor.utils;

import android.util.Log;

import com.azare.app.healthmonitor.model.BPREADTYPE;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class HMUtils {

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
}
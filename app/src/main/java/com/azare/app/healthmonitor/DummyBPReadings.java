package com.azare.app.healthmonitor;

import android.util.Log;

import com.azare.app.healthmonitor.model.BPREADTYPE;
import com.azare.app.healthmonitor.model.BPReading;
import com.azare.app.healthmonitor.utils.HMUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DummyBPReadings implements IDummyReading {

    private String strStartDate;
    private String strEndDate;
    private List<BPReading> lDummyReadings;

    public DummyBPReadings(String strStartDate, String strEndDate) {
        this.strStartDate = strStartDate;
        this.strEndDate = strEndDate;

        lDummyReadings = new ArrayList<BPReading>();
    }

    /*
    Generate Dummy BP Readings for a specified start and end date.
    */
    public void generateDummyReading() {

        try {

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            Log.i("Health Monitor", "Start Date: " + sdf.parse(strStartDate));
            Log.i("Health Monitor", "End Date: " + sdf.parse(strEndDate));

            Calendar calStart = Calendar.getInstance();
            Calendar calEnd = Calendar.getInstance();

            calStart.setTime(sdf.parse(strStartDate));
            calEnd.setTime(sdf.parse(strEndDate));

            int daysBetween = calEnd.get(Calendar.DAY_OF_YEAR) - calStart.get(Calendar.DAY_OF_YEAR);

            Log.i("Health Monitor", "Days Between: " + daysBetween);

            //TODO:loop through all days within the period
            for (Date date = calStart.getTime(); calStart.before(calEnd);
                 calStart.add(Calendar.DATE, 1), date = calStart.getTime()) {


                String strCurrentDate = sdf.format(date);
                String[] strArrDate = strCurrentDate.split("/");

                int iDay = Integer.parseInt(strArrDate[0]);
                int iMth = Integer.parseInt(strArrDate[1]);
                int iYear = Integer.parseInt(strArrDate[2]);

                Log.i("Health Monitor", "Day: " + iDay + "\tMth: " + iMth + "\tYear: " + iYear);

                for (BPREADTYPE type : BPREADTYPE.values()) {

                    Log.i("Health Monitor", "Read Type: " + type.name());
                    long[] periodTS = HMUtils.generateHourlyTS(calStart.getTime(),type);

                    int iRandomTS = HMUtils.getRandomizedInt(0,4);

//                Log.i("Health Monitor", "Random TS Selected: " + iRandomTS);

                    long selectedTS = periodTS[iRandomTS];

                    Log.i("Health Monitor", "BP Reading Taken on: " + new Date(selectedTS));

                    int[] iNormalBP = generateNormalBP();

                    int iSystolic = iNormalBP[0];
                    int iDiastolic = iNormalBP[1];

                    BPReading bpReading = new BPReading();

                    bpReading.setDay(iDay);
                    bpReading.setMonth(iMth);
                    bpReading.setYear(iYear);
                    bpReading.setReadType(type);
                    bpReading.setTimestamp(selectedTS);
                    bpReading.setSystolic(iSystolic);
                    bpReading.setDiastolic(iDiastolic);

                    //Log.i("Health Monitor", "New BP Reading: " + bpReading.toString());

                    lDummyReadings.add(bpReading);
                }

            }

        } catch (Exception e) {
            Log.i("Health Monitor", "Exception occur while generating Dummy BP Reading.");
        }



        //TODO: For each day, generate dummy date for each of the Reading Type.
        //TODO: Randomize values: systolic, diastolic, timestamp

    }

    public static int[] generateNormalBP() {

        int[] iNormalBP = new int[2];

        int iSystolic = HMUtils.getRandomizedInt(110,119);

        iNormalBP[0] = iSystolic;

        int iDiastolic = HMUtils.getRandomizedInt(70,79);

        iNormalBP[1] = iDiastolic;

        return iNormalBP;
    }

    public List<BPReading> getDummyReadings() {
        return lDummyReadings;
    }
}

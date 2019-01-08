package com.azare.app.healthmonitor;

import android.util.Log;

import com.azare.app.healthmonitor.model.WeightRecord;
import com.azare.app.healthmonitor.utils.HMUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DummyWeightRecords implements IDummyReading {

    List<WeightRecord> lDummyWeights;
    HashMap<String, Integer> mapDateWeight;

    public DummyWeightRecords() {
        this.lDummyWeights = new ArrayList<WeightRecord>();
        mapDateWeight = new HashMap<String, Integer>();

        // Dummy Weight Records map.
        // One reading a month from july 2018 to Dec 2018.

        // Create a map of Date/Time string and weight,
        // one entry for each month.
        // The time must be between 10pm and 8am.
        mapDateWeight.put("10/07/2018 11:05:22", 77);
        mapDateWeight.put("12/08/2018 14:30:01", 77);
        mapDateWeight.put("15/09/2018 15:10:30", 78);
        mapDateWeight.put("13/10/2018 17:00:20", 78);
        mapDateWeight.put("14/11/2018 09:30:50", 79);
        mapDateWeight.put("16/12/2018 10:15:00", 78);
    }

    @Override
    public void generateDummyReading() {
        //for each item in map,
        // Get Day, Month and Year of Date for Weight Record
        // Convert to Date/Time to timestamp.
        //create WeightRecord using the generated timestamp.
        //add to list.

        SimpleDateFormat sdf = new SimpleDateFormat(HMUtils.DATE_TIME_FORMAT);

        try {
            for (Map.Entry<String, Integer> entry : mapDateWeight.entrySet()) {

                Date date = sdf.parse(entry.getKey());

                Calendar cal = Calendar.getInstance();

                cal.setTime(date);

                int day = cal.get(Calendar.DATE);

                int month = cal.get(Calendar.MONTH) + 1;

                int year = cal.get(Calendar.YEAR);

                long timestamp = date.getTime();

                lDummyWeights.add(new WeightRecord(day,month,year,entry.getValue(),timestamp));
            }
        } catch (ParseException pexp) {
            Log.i(HMUtils.LOGTAG, "Fail to parse date string");
        }
    }

    public List<WeightRecord> getDummyReadings() {
        return lDummyWeights;
    }
}

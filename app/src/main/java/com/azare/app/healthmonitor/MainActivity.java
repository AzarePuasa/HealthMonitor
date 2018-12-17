package com.azare.app.healthmonitor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.azare.app.healthmonitor.model.BPReading;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnPopulateDB;
    Button btnClearDB;
    Button btnShowBPReading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPopulateDB = (Button) findViewById(R.id.btnPopulateDB);
        btnClearDB = (Button) findViewById(R.id.btnClearDB);
        btnShowBPReading = (Button) findViewById(R.id.btnShowBPReading);


        //event listener for button.
        btnPopulateDB.setOnClickListener(btnPopulateDBClicked);

        btnClearDB.setOnClickListener(btnClearDBClicked);

        btnShowBPReading.setOnClickListener(btnShowBPReadingClicked);
    }

    /*
    Populate BP Readings Table with dummy data.
     */
    private View.OnClickListener btnPopulateDBClicked = new View.OnClickListener(){

        @Override
        public void onClick(View v) {

            String strStartDate = "01/11/2018";
            String strEndDate = "30/11/2018";
            try {
                List<BPReading> lDummyReadings = generateDummyBPReadings(strStartDate,strEndDate);

                if (lDummyReadings.size() > 0) {
                    populateBPReadingTbl(lDummyReadings);
                }
            } catch (Exception pse)
            {
                Log.e("Health Monitor"
                        , "Error Populating BP Readings Table" + pse.getMessage());
            }

           Toast.makeText(getApplicationContext()
                   ,"This button is to populate dummy rows the Table",
                   Toast.LENGTH_LONG).show();

        }
    };

    /*
    Delete all entries in BP Readings Table.
     */
    private View.OnClickListener btnClearDBClicked = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext()
                    ,"This button is to clear existings rows from the Table",
                    Toast.LENGTH_LONG).show();
        }
    };


    /*
    Create intent and launch List BP Readings Activity.
     */
    private View.OnClickListener btnShowBPReadingClicked = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            Intent lvBPReadingIntent = new Intent(MainActivity.this, ListBPReading.class);
            startActivity(lvBPReadingIntent);
        }
    };

    /*
    Generate Dummy BP Readings for a specified start and end date.
     */
    private List<BPReading> generateDummyBPReadings(String strStartDate, String strEndDate) throws ParseException {
        List<BPReading> lDummyReadings = new ArrayList<BPReading>();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Log.i("Health Monitor", "Start Date: " + sdf.parse(strStartDate));
        Log.i("Health Monitor", "End Date: " + sdf.parse(strEndDate));

        Calendar calStart = Calendar.getInstance();
        Calendar calEnd = Calendar.getInstance();

        calStart.setTime(sdf.parse(strStartDate));
        calEnd.setTime(sdf.parse(strEndDate));

        int daysBetween = calEnd.get(Calendar.DAY_OF_YEAR) - calStart.get(Calendar.DAY_OF_YEAR);

        Log.i("Health Monitor", "Days Between: " + daysBetween);

        long[] morningTS = generateRandomTimeStamp(calStart.getTime(),7, 13);

        for (long mrn : morningTS) {
            Date dtTimeStamp = new Date(mrn);
            Log.i("Health Monitor", "Millis: : " + mrn + "\tDate: " + dtTimeStamp);
        }

        long[] afternoonTS = generateRandomTimeStamp(calStart.getTime(), 13,19);

        for (long aftn : afternoonTS) {
            Date dtTimeStamp = new Date(aftn);
            Log.i("Health Monitor", "Millis: : " + aftn + "\tDate: " + dtTimeStamp);
        }

        long[] eveningTS = generateRandomTimeStamp(calStart.getTime(),19,24);

        for (long evn : eveningTS) {
            Date dtTimeStamp = new Date(evn);
            Log.i("Health Monitor", "Millis: : " + evn + "\tDate: " + dtTimeStamp);
        }


        //TODO:loop through all days within the period
        //TODO: For each day, generate dummy date for each of the Reading Type.
        //TODO: Randomize values: systolic, diastolic, timestamp

        return lDummyReadings;
    }


    /*
    Populate table for a specified start and end date.
    */
    private void populateBPReadingTbl(List<BPReading> lBPReadings) {

        //TODO: get db instance and call insert.

    }

    private long[] generateRandomTimeStamp(Date date, int iStart, int iEnd) {

        //TODO: generate random timestamp for morning Read Type.

        //https://www.sanfoundry.com/java-program-generate-date-between-given-range/

        //generate an array of 20 random long values that represents
        //the time within the Morning period.

        //Generate a random number of between 1 to 20.

        //return the value for the array in that position.
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, iStart);

        Log.i("Health Monitor", "Morning Start Time: " + cal.getTime());

        long[] millis = new long[iEnd-iStart];

        Date dtmilli = cal.getTime();
        long startMilli = dtmilli.getTime();
        long currentmilli = 0;

        int iCount = iEnd - iStart;

        for (int i=0; i < iCount; i++){
            long currentMilli = startMilli + currentmilli;
            millis[i] = currentMilli;
            currentmilli += 3600000;
        }

        return millis;
    }
}

package com.azare.app.healthmonitor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.azare.app.healthmonitor.model.BPREADTYPE;
import com.azare.app.healthmonitor.model.BPReading;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btnPopulateDB;
    Button btnClearDB;
    Button btnShowBPReading;
    Button btnGetNormalBP;

    TextView tvSystolic;
    TextView tvDiastolic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPopulateDB = (Button) findViewById(R.id.btnPopulateDB);
        btnClearDB = (Button) findViewById(R.id.btnClearDB);
        btnShowBPReading = (Button) findViewById(R.id.btnShowBPReading);
        btnGetNormalBP = (Button) findViewById(R.id.btnNormalBP);

        tvSystolic = (TextView) findViewById(R.id.tvMin);
        tvDiastolic = (TextView) findViewById(R.id.tvMax);

        //event listener for button.
        btnPopulateDB.setOnClickListener(btnPopulateDBClicked);

        btnClearDB.setOnClickListener(btnClearDBClicked);

        btnShowBPReading.setOnClickListener(btnShowBPReadingClicked);

        btnGetNormalBP.setOnClickListener(btnNormalBPClicked);
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

    private View.OnClickListener btnNormalBPClicked = new View.OnClickListener(){

        @Override
        public void onClick(View v) {

            int[] iNormalBP = generateNormalBP();

            String strSystolic = Integer.toString(iNormalBP[0]);
            String strDiastolic = Integer.toString(iNormalBP[1]);

            tvSystolic.setText(strSystolic);
            tvDiastolic.setText(strDiastolic);
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

        //TODO:loop through all days within the period
        for (Date date = calStart.getTime(); calStart.before(calEnd);
             calStart.add(Calendar.DATE, 1), date = calStart.getTime()) {

            BPReading bpReading = new BPReading();
            String strCurrentDate = sdf.format(date);
            String[] strArrDate = strCurrentDate.split("/");

            int iDay = Integer.parseInt(strArrDate[0]);
            int iMth = Integer.parseInt(strArrDate[1]);
            int iYear = Integer.parseInt(strArrDate[2]);

            Log.i("Health Monitor", "Day: " + iDay + "\tMth: " + iMth + "\tYear: " + iYear);

            for (BPREADTYPE type : BPREADTYPE.values()) {

                Log.i("Health Monitor", "Read Type: " + type.name());
                long[] periodTS = generateHourlyTS(calStart.getTime(),type);

                int iRandomTS = getRandomizedInt(0,4);

//                Log.i("Health Monitor", "Random TS Selected: " + iRandomTS);

                long selectedTS = periodTS[iRandomTS];

                Log.i("Health Monitor", "BP Reading Taken on: " + new Date(selectedTS));

                int[] iNormalBP = generateNormalBP();

                int iSystolic = iNormalBP[0];
                int iDiastolic = iNormalBP[1];

                bpReading.setDay(iDay);
                bpReading.setMonth(iMth);
                bpReading.setYear(iYear);
                bpReading.setReadType(type);
                bpReading.setTimestamp(selectedTS);
                bpReading.setSystolic(iSystolic);
                bpReading.setDiastolic(iDiastolic);

                //Log.i("Health Monitor", bpReading.toString());

                lDummyReadings.add(bpReading);
            }

        }

        //TODO: For each day, generate dummy date for each of the Reading Type.
        //TODO: Randomize values: systolic, diastolic, timestamp

        return lDummyReadings;
    }


    /*
    Populate table for a specified start and end date.
    */
    private void populateBPReadingTbl(List<BPReading> lBPReadings) {

        //TODO: get db instance and call insert.
        Log.i("Health Monitor", "Reading Count: " + lBPReadings.size());

        for(BPReading reading: lBPReadings) {
            Log.i("Health Monitor", reading.toString());
        }


    }

    /*
    Generate a list containing a hourly timestamp for the given
    date and period.
     */
    private long[] generateHourlyTS(Date date, BPREADTYPE readType) {

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

    private int[] generateNormalBP() {

        int[] iNormalBP = new int[2];

        int iSystolic = getRandomizedInt(110,119);

        iNormalBP[0] = iSystolic;

        int iDiastolic = getRandomizedInt(70,79);

        iNormalBP[1] = iDiastolic;

        return iNormalBP;
    }


    private int getRandomizedInt(int iMin, int iMax) {
//        Log.i("Health Monitor", "Min: " + iMin);
//        Log.i("Health Monitor", "Max: " + iMax);

        Random rand = new Random();

        int iRand = (int)rand.nextInt((iMax - iMin) + 1) + iMin;

        return iRand;
    }
}

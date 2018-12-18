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

import com.amitshekhar.DebugDB;
import com.azare.app.healthmonitor.db.DAOBPReading;
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

    DAOBPReading daobpReading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        daobpReading = new DAOBPReading(this);

        Log.i("Health Monitor", DebugDB.getAddressLog());

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
                DummyBPReadings dummyBPs = new DummyBPReadings(strStartDate, strEndDate);

                dummyBPs.generateDummyReading();

                if (dummyBPs.getDummyReadings().size() > 0) {
                    populateBPReadingTbl(dummyBPs.getDummyReadings());
                }
                else {
                    throw new Exception("Not able to generate Dummy BP Reading.");
                }
            } catch (Exception exp)
            {
                Log.e("Health Monitor"
                        , "Error Populating BP Readings Table" + exp.getMessage());
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
            int iCleared = daobpReading.delete(BPREADTYPE.AFTERNOON);
            Toast.makeText(getApplicationContext()
                    ,"Cleared" + iCleared + " rows.",
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

            int[] iNormalBP = DummyBPReadings.generateNormalBP();

            String strSystolic = Integer.toString(iNormalBP[0]);
            String strDiastolic = Integer.toString(iNormalBP[1]);

            tvSystolic.setText(strSystolic);
            tvDiastolic.setText(strDiastolic);
        }
    };

    /*
    Populate table for a specified start and end date.
    */
    private void populateBPReadingTbl(List<BPReading> lBPReadings) {
        Log.i("Health Monitor", "Reading Count: " + lBPReadings.size());

        for(BPReading reading: lBPReadings) {
            //TODO: get db instance and call insert.
            Log.i("Health Monitor", reading.toString());

            daobpReading.insert(reading);
        }
    }
}
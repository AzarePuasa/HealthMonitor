package com.azare.app.healthmonitor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.azare.app.healthmonitor.db.DAOBPReading;
import com.azare.app.healthmonitor.model.DailyBPReadings;

/**
 * This activity will list the Blood Pressure readings.
 */
public class ListBPReading extends AppCompatActivity {

    Button btnGetAllBPReading;

    DAOBPReading daobpReading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bpreading);

        daobpReading = new DAOBPReading(this);

        btnGetAllBPReading = (Button) findViewById(R.id.btnGetAllReading);

        btnGetAllBPReading.setOnClickListener(btnGetAllReadingClicked);

        //query table for readings.

        //set adapter for list.
    }

    private View.OnClickListener btnGetAllReadingClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            //fetch the BP readings from db
            DailyBPReadings dailyBPReadings = daobpReading.listAll();

            Log.i("Health Monitor", dailyBPReadings.toString());

        }
    };
}

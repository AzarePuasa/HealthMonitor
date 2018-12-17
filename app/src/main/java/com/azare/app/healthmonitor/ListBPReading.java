package com.azare.app.healthmonitor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

/**
 * This activity will list the Blood Pressure readings.
 */
public class ListBPReading extends AppCompatActivity {

    ListView lvBPReading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bpreading);

        lvBPReading = (ListView) findViewById(R.id.lvBPReadings);

        //query table for readings.

        //set adapter for list.
    }
}

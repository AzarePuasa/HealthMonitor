package com.azare.app.healthmonitor;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.azare.app.healthmonitor.db.DAOBPReading;
import com.azare.app.healthmonitor.model.DailyBPReadings;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * This activity will list the Blood Pressure readings.
 */
public class ListBPReading extends AppCompatActivity  {

    DAOBPReading daobpReading;

    RecyclerView recyclerView;
    BPReadingAdapter bpReadingAdapter;

    Button btnGetAllBPReading;
    Button btnAddBPReading;
    Button btnFilterBPReading;

    TextView tvFilterStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bpreading);

        recyclerView = (RecyclerView) findViewById(R.id.allBPReading);
        btnGetAllBPReading = (Button) findViewById(R.id.btnGetAllReading);
        btnAddBPReading = (Button)findViewById(R.id.btnAddBPReading);
        btnFilterBPReading = (Button) findViewById(R.id.btnFilterBPReading);
        tvFilterStatus = (TextView) findViewById(R.id.tvFilterStatus);

        daobpReading = new DAOBPReading(this);

        //query table for readings.
        DailyBPReadings dailyBPReadings = daobpReading.listAll();

        //set adapter for list.
        bpReadingAdapter = new BPReadingAdapter(dailyBPReadings);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(bpReadingAdapter);

        btnGetAllBPReading.setOnClickListener(btnGetAllReadingClicked);

        btnAddBPReading.setOnClickListener(btnAddBPReadingClicked);

        btnFilterBPReading.setOnClickListener(btnFilterBPReadingClicked);

        tvFilterStatus.setText("Show All Reading");
    }

    private View.OnClickListener btnGetAllReadingClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            //fetch the BP readings from db
            DailyBPReadings dailyBPReadings = daobpReading.listAll();

            Log.i("Health Monitor", dailyBPReadings.toString());

        }
    };

    private View.OnClickListener btnAddBPReadingClicked = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent bpAddIntent = new Intent(getApplicationContext(), AddBPReading.class);
            startActivity(bpAddIntent);
        }
    };

    private View.OnClickListener btnFilterBPReadingClicked = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent bpFilterIntent = new Intent(getApplicationContext(), FilterBPActivity.class);
            startActivity(bpFilterIntent);

        }
    };



}

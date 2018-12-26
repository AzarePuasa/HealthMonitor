package com.azare.app.healthmonitor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

    RecyclerView recyclerView;
    BPReadingAdapter bpReadingAdapter;

    Button btnGetAllBPReading;
    DAOBPReading daobpReading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bpreading);

        daobpReading = new DAOBPReading(this);
        //query table for readings.
        DailyBPReadings dailyBPReadings = daobpReading.listAll();

        recyclerView = (RecyclerView) findViewById(R.id.allBPReading);

        //set adapter for list.
        bpReadingAdapter = new BPReadingAdapter(dailyBPReadings);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(bpReadingAdapter);

        btnGetAllBPReading = (Button) findViewById(R.id.btnGetAllReading);

        btnGetAllBPReading.setOnClickListener(btnGetAllReadingClicked);

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

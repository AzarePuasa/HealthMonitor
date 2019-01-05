package com.azare.app.healthmonitor;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.azare.app.healthmonitor.db.DAOBPReading;
import com.azare.app.healthmonitor.model.BPFILTERTYPE;
import com.azare.app.healthmonitor.model.BPReadingFilter;
import com.azare.app.healthmonitor.model.DailyBPReadings;

/**
 * This activity will list the Blood Pressure readings.
 */
public class ListBPReading extends AppCompatActivity  {

    private final int REQUEST_FILTER_CODE = 20;

    DAOBPReading daobpReading;
    BPFILTERTYPE currentfiltertype;

    RecyclerView recyclerView;
    BPReadingAdapter bpReadingAdapter;

    Button btnGetAllBPReading;
    Button btnAddBPReading;
    Button btnFilterBPReading;

    TextView tvFilterStatus;

    DailyBPReadings dailyBPReadings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bpreading);

        currentfiltertype = BPFILTERTYPE.ALL;

        recyclerView = (RecyclerView) findViewById(R.id.allBPReading);
        btnGetAllBPReading = (Button) findViewById(R.id.btnGetAllReading);
        btnAddBPReading = (Button)findViewById(R.id.btnAddBPReading);
        btnFilterBPReading = (Button) findViewById(R.id.btnFilterBPReading);
        tvFilterStatus = (TextView) findViewById(R.id.tvFilterStatus);

        daobpReading = new DAOBPReading(this);

        //query table for readings.
        dailyBPReadings = daobpReading.listAll();

        //set adapter for list.
        bpReadingAdapter = new BPReadingAdapter(dailyBPReadings);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(bpReadingAdapter);

        btnGetAllBPReading.setOnClickListener(btnGetAllReadingClicked);

        btnAddBPReading.setOnClickListener(btnAddBPReadingClicked);

        btnFilterBPReading.setOnClickListener(btnFilterBPReadingClicked);

        tvFilterStatus.setText(currentfiltertype.getStatusMsg());
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

            startActivityForResult(bpFilterIntent, REQUEST_FILTER_CODE);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // REQUEST_CODE is defined above
        if (resultCode == RESULT_OK && requestCode == REQUEST_FILTER_CODE) {
            // Extract name value from result extras
            BPReadingFilter readingFilter = (BPReadingFilter) data.getSerializableExtra("filter");

            tvFilterStatus.setText(readingFilter.getFilterType().getStatusMsg());

            // Toast the name to display temporarily on screen
            Toast.makeText(this, readingFilter.getFilterType().getStatusMsg(), Toast.LENGTH_SHORT).show();

            currentfiltertype =readingFilter.getFilterType();


            if (readingFilter.getFilterType() == BPFILTERTYPE.ALL) {
                //clear list
                //query table for readings.
                //update list in adapter.
                //call notifyDataSetChanged()

                bpReadingAdapter.clear();
                dailyBPReadings = daobpReading.listAll();
                bpReadingAdapter.setDailyBPReadings(dailyBPReadings);
                bpReadingAdapter.notifyDataSetChanged();

            } else if (readingFilter.getFilterType() == BPFILTERTYPE.MONTH) {
                //clear list
                //query table for readings.
                //update list in adapter.
                //call notifyDataSetChanged()

                bpReadingAdapter.clear();
                dailyBPReadings = daobpReading
                        .listDailyReadings(readingFilter.getStartDate(), readingFilter.getEndDate());
                bpReadingAdapter.setDailyBPReadings(dailyBPReadings);
                bpReadingAdapter.notifyDataSetChanged();

            } else if (readingFilter.getFilterType() == BPFILTERTYPE.WEEK ) {
                //clear list
                //query table for readings.
                //update list in adapter.
                //call notifyDataSetChanged()

                bpReadingAdapter.clear();
                dailyBPReadings = daobpReading
                        .listDailyReadings(readingFilter.getStartDate(), readingFilter.getEndDate());
                bpReadingAdapter.setDailyBPReadings(dailyBPReadings);
                bpReadingAdapter.notifyDataSetChanged();

            } else if (readingFilter.getFilterType() == BPFILTERTYPE.CUSTOM ) {

                bpReadingAdapter.clear();

                //query table for readings.
                dailyBPReadings = daobpReading
                        .listDailyReadings(readingFilter.getStartDate(), readingFilter.getEndDate());

                bpReadingAdapter.setDailyBPReadings(dailyBPReadings);

                bpReadingAdapter.notifyDataSetChanged();
            } else {
                //TODO: write to log.
                Log.i("Health Monitor", "Error Processing filter");

            }

        }
    }
}

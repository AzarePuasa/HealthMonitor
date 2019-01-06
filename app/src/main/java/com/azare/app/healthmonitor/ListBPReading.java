package com.azare.app.healthmonitor;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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

    TextView tvFilterStatus;

    DailyBPReadings dailyBPReadings;

    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bpreading);

        // Get ActionBar
        actionBar = getSupportActionBar();

        // Set below attributes to add logo in ActionBar.
        actionBar.setDisplayOptions( ActionBar.DISPLAY_SHOW_HOME
                | ActionBar.DISPLAY_SHOW_TITLE);

        actionBar.setTitle("Health Monitor");

        currentfiltertype = BPFILTERTYPE.ALL;

        recyclerView = (RecyclerView) findViewById(R.id.allBPReading);

        tvFilterStatus = (TextView) findViewById(R.id.tvFilterStatus);

        daobpReading = new DAOBPReading(this);

        //query table for readings.
        dailyBPReadings = daobpReading.listAll();

        //set adapter for list.
        bpReadingAdapter = new BPReadingAdapter(dailyBPReadings);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(bpReadingAdapter);

        tvFilterStatus.setText(currentfiltertype.getStatusMsg());
    }

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

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_listbpreading, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {
        case R.id.addBPItem:
            Intent bpAddIntent = new Intent(getApplicationContext(), AddBPReading.class);
            startActivity(bpAddIntent);

            return(true);
        case R.id.filterBPItem:
            Intent bpFilterIntent = new Intent(getApplicationContext(), FilterBPActivity.class);

            startActivityForResult(bpFilterIntent, REQUEST_FILTER_CODE);

            return(true);
        case R.id.printToConsole:
            DailyBPReadings dailyBPReadings = daobpReading.listAll();

            Log.i("Health Monitor", dailyBPReadings.toString());

            return(true);
    }
        return(super.onOptionsItemSelected(item));
    }
}

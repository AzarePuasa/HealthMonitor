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
import com.azare.app.healthmonitor.model.AfternoonBPReading;
import com.azare.app.healthmonitor.model.BPFILTERTYPE;
import com.azare.app.healthmonitor.model.BPREADTYPE;
import com.azare.app.healthmonitor.model.BPReading;
import com.azare.app.healthmonitor.model.BPReadingFilter;
import com.azare.app.healthmonitor.model.DailyBPReadings;
import com.azare.app.healthmonitor.model.EveningBPReading;
import com.azare.app.healthmonitor.model.MorningBPReading;
import com.azare.app.healthmonitor.utils.HMUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This activity will list the Blood Pressure readings.
 */
public class ListBPReading extends AppCompatActivity  {

    private final int REQUEST_FILTER_CODE = 20;
    private final int REQUEST_NEW_BP_CODE = 30;

    DAOBPReading daobpReading;
    BPReadingFilter bpReadingFilter;

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

        //default No filter i.e. show all reading.
        bpReadingFilter = new BPReadingFilter(BPFILTERTYPE.NONE);

        recyclerView = (RecyclerView) findViewById(R.id.allBPReading);

        tvFilterStatus = (TextView) findViewById(R.id.tvFilterStatus);

        daobpReading = new DAOBPReading(this);

        //query table for readings.
        dailyBPReadings = daobpReading.listAll();

        //set adapter for list.
        bpReadingAdapter = new BPReadingAdapter(dailyBPReadings);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(bpReadingAdapter);

        tvFilterStatus.setText(bpReadingFilter.getFilterType().getStatusMsg());
    }

    private boolean addTodaysReading(String strDateToday, BPReading bpReading) {
        if (bpReading.getReadType() == BPREADTYPE.MORNING) {
            try {
                MorningBPReading morningBPReading = new MorningBPReading(
                        bpReading.getDay(), bpReading.getMonth(), bpReading.getYear(),
                        bpReading.getSystolic(), bpReading.getDiastolic());

                dailyBPReadings.addMorningReading(strDateToday, morningBPReading);
                return true;
            } catch (Exception e) {
                Log.e(HMUtils.LOGTAG, "Fail to Add Morning Reading.");
            }
        } else if (bpReading.getReadType() == BPREADTYPE.AFTERNOON) {
            try {
                AfternoonBPReading afternoonBPReading =
                        new AfternoonBPReading(bpReading.getDay(), bpReading.getMonth(),
                                bpReading.getYear(), bpReading.getSystolic(), bpReading.getDiastolic());

                dailyBPReadings.addAfternoonReading(strDateToday, afternoonBPReading);

                return true;
            } catch (Exception e) {
                Log.e(HMUtils.LOGTAG, "Fail to Add Morning Reading.");
            }
        } else if (bpReading.getReadType() == BPREADTYPE.EVENING) {
            try {
                EveningBPReading eveningBPReading = new EveningBPReading(bpReading.getDay(), bpReading.getMonth(),
                        bpReading.getYear(), bpReading.getSystolic(), bpReading.getDiastolic());

                dailyBPReadings.addEveningReading(strDateToday, eveningBPReading);
                return true;
            } catch (Exception e) {
                Log.e(HMUtils.LOGTAG, "Fail to Add Morning Reading.");
            }
        } else {
            Log.e(HMUtils.LOGTAG, "Fail to Add BP Reading.");
        }

        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // REQUEST_CODE is defined above

        //Add New BP
        if (resultCode == RESULT_OK && requestCode == REQUEST_NEW_BP_CODE) {
            // write new BPReading to db table.
            // - get date and type of new BP Reading.

            Date dtToday = new Date(System.currentTimeMillis());
            BPReading newBPReading = (BPReading) data.getSerializableExtra("new");

            String strNewBPDate = newBPReading.getReadingDate();

            Date dtBPReading = HMUtils.strToDate(strNewBPDate, new SimpleDateFormat(HMUtils.DATE_FORMAT));

            BPREADTYPE newBPReadtype = newBPReading.getReadType();

            // check if similar reading exist in table (same date and type).
            // if not exist, write the new BP Reading to db table.
            // and then update display if necessary.
            if (!daobpReading.exist(strNewBPDate,newBPReadtype)) {
                //reading does not exist. write to db table.
                if ( daobpReading.insert(newBPReading) ) {
                    //successfully add new reading.

                    //update display if necessary.
                    if (bpReadingFilter.getFilterType() == BPFILTERTYPE.NONE) {

                        // current filter is none,

                        // - clear list
                        bpReadingAdapter.clear();

                        // - query table for readings. get all readings again.
                        dailyBPReadings = daobpReading.listAll();

                        // - update list in adapter.
                        bpReadingAdapter.setDailyBPReadings(dailyBPReadings);

                        // - call notifyDataSetChanged()
                        bpReadingAdapter.notifyDataSetChanged();

                    } else if (bpReadingFilter.getFilterType() == BPFILTERTYPE.CUSTOM) {
                        // current filter is Custom.

                        Date dtStart = bpReadingFilter.getStartDate();
                        Date dtEnd = bpReadingFilter.getEndDate();

                        if ((dtBPReading.compareTo(dtStart) > 0 || dtBPReading.compareTo(dtStart) == 0 )
                            && (dtBPReading.compareTo(dtEnd) < 0 || dtBPReading.compareTo(dtEnd) == 0 )) {
                            // new BP Reading date is within the custom period

                            // clear list
                            bpReadingAdapter.clear();

                            // query table for readings.
                            dailyBPReadings = daobpReading
                                    .listDailyReadings(dtStart, dtEnd);

                            // update list in adapter.
                            bpReadingAdapter.setDailyBPReadings(dailyBPReadings);

                            // call notifyDataSetChanged()
                            bpReadingAdapter.notifyDataSetChanged();
                        }
                    } else if (bpReadingFilter.getFilterType() == BPFILTERTYPE.SPECIFIC) {
                        // currentfilter is Specified,
                        if (bpReadingFilter.getStartDate().compareTo(dtBPReading) == 0) {
                            // new BP Reading date is same as selected date.
                            // clear list
                            bpReadingAdapter.clear();

                            // query table for readings.
                            DailyBPReading dailyBPReading = daobpReading.getDailyReading(strNewBPDate);
                            dailyBPReadings.addDailyBPReading(dailyBPReading);

                            // update list in adapter.
                            bpReadingAdapter.setDailyBPReadings(dailyBPReadings);

                            // call notifyDataSetChanged()
                            bpReadingAdapter.notifyDataSetChanged();
                        }
                    }
                    else {
                        //TODO: write to log.
                        Log.i(HMUtils.LOGTAG, "Error Processing filter");
                    }
                }
            } else {
                //reading exist. Toast reading already exist.
                Toast.makeText(getApplicationContext(), "Reading already exist",
                        Toast.LENGTH_LONG).show();
            }
        }

        //Filter BP
        if (resultCode == RESULT_OK && requestCode == REQUEST_FILTER_CODE) {
            // Extract name value from result extras
            BPReadingFilter readingFilter = (BPReadingFilter) data.getSerializableExtra("filter");

            tvFilterStatus.setText(readingFilter.getFilterType().getStatusMsg());

            // Toast the name to display temporarily on screen
            Toast.makeText(this, readingFilter.getFilterType().getStatusMsg(), Toast.LENGTH_SHORT).show();

            bpReadingFilter.setFilterType(readingFilter.getFilterType());

            if (readingFilter.getFilterType() == BPFILTERTYPE.NONE) {
                //clear list
                //query table for readings.
                //update list in adapter.
                //call notifyDataSetChanged()

                bpReadingAdapter.clear();
                dailyBPReadings = daobpReading.listAll();
                bpReadingAdapter.setDailyBPReadings(dailyBPReadings);
                bpReadingAdapter.notifyDataSetChanged();
            } else if (readingFilter.getFilterType() == BPFILTERTYPE.CUSTOM ) {

                bpReadingAdapter.clear();

                //query table for readings.
                dailyBPReadings = daobpReading
                        .listDailyReadings(readingFilter.getStartDate(), readingFilter.getEndDate());

                bpReadingAdapter.setDailyBPReadings(dailyBPReadings);

                bpReadingAdapter.notifyDataSetChanged();
            } else if (readingFilter.getFilterType() == BPFILTERTYPE.SPECIFIC ) {
                //clear list
                bpReadingAdapter.clear();
                //query table for readings.
                SimpleDateFormat sdf = new SimpleDateFormat(HMUtils.DATE_FORMAT);
                String specificDate = sdf.format(readingFilter.getStartDate());

                DailyBPReading dailyBPReading = daobpReading.getDailyReading(specificDate);
                dailyBPReadings.addDailyBPReading(dailyBPReading);

                //update list in adapter.
                bpReadingAdapter.setDailyBPReadings(dailyBPReadings);

                //call notifyDataSetChanged()
                bpReadingAdapter.notifyDataSetChanged();
            } else {
                //TODO: write to log.
                Log.i(HMUtils.LOGTAG, "Error Processing filter");
            }
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_listbpreading, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addBPItem:
                Intent bpAddIntent = new Intent(getApplicationContext(), AddBPReading.class);
                startActivityForResult(bpAddIntent,REQUEST_NEW_BP_CODE);

                return (true);
            case R.id.filterBPItem:
                Intent bpFilterIntent = new Intent(getApplicationContext(), FilterBPActivity.class);
                startActivityForResult(bpFilterIntent, REQUEST_FILTER_CODE);

                return (true);
            case R.id.bpreminder:
                Intent bpReminderIntent = new Intent(getApplicationContext(), BPReminderActivity.class);

                startActivity(bpReminderIntent);

                return (true);
            case R.id.printToConsole:
                DailyBPReadings dailyBPReadings = daobpReading.listAll();

                Log.i("Health Monitor", dailyBPReadings.toString());

                return (true);



        }
        return (super.onOptionsItemSelected(item));
    }
}

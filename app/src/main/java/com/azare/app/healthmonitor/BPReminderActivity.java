package com.azare.app.healthmonitor;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.azare.app.healthmonitor.model.BPREADTYPE;
import com.azare.app.healthmonitor.model.BPReadingReminders;
import com.azare.app.healthmonitor.utils.HMUtils;

public class BPReminderActivity extends AppCompatActivity {

    public static final String PREFFILE = "BPReminder";

    //3 spinner
    Spinner spinnerMorRem;
    Spinner spinnerAftRem;
    Spinner spinnerEveRem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bpreminder);

        //spinner
        spinnerMorRem = (Spinner) findViewById(R.id.spinnerMorRem);
        spinnerAftRem = (Spinner) findViewById(R.id.spinnerAftRem);
        spinnerEveRem = (Spinner) findViewById(R.id.spinnerEveRem);


        //populate spinner.
        ArrayAdapter<String> aaMorningSpinner = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, BPReadingReminders.MORNINGREMINDERS);

        spinnerMorRem.setAdapter(aaMorningSpinner);

        ArrayAdapter<String> aaAfternoonSpinner = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, BPReadingReminders.AFTERNOONREMINDER);

        spinnerAftRem.setAdapter(aaAfternoonSpinner);

        ArrayAdapter<String> aaEveningSpinner = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, BPReadingReminders.EVENINGREMINDER);

        spinnerEveRem.setAdapter(aaEveningSpinner);

        //set spinner value from pref
        BPReadingReminders savedReminders = readPrefs();

        //set spinner with saved value in pref. if exist.
        int pos = 0;
        for (String strReminder : BPReadingReminders.MORNINGREMINDERS) {

            if ( strReminder.
                    equals(savedReminders.getReminder(BPREADTYPE.MORNING))) {
                break;
            }
            pos++;
        }

        spinnerMorRem.setSelection(pos);

        pos = 0;
        for (String strReminder : BPReadingReminders.AFTERNOONREMINDER) {

            if ( strReminder.
                    equals(savedReminders.getReminder(BPREADTYPE.AFTERNOON))) {
                break;
            }
            pos++;
        }

        spinnerAftRem.setSelection(pos);

        pos = 0;
        for (String strReminder : BPReadingReminders.EVENINGREMINDER) {

            if ( strReminder.
                    equals(savedReminders.getReminder(BPREADTYPE.EVENING))) {
                break;
            }
            pos++;
        }

        spinnerEveRem.setSelection(pos);
    }

    private BPReadingReminders readPrefs() {

        BPReadingReminders bpReadingReminders = new BPReadingReminders();
        //initialize spinner from shared preferences.
        SharedPreferences pref = this.getSharedPreferences(PREFFILE,MODE_PRIVATE);

        //get preferences and write to BPReadingReminder.

        //get morning reminder. set to -1 if none.
        String strMorningReminder = pref.getString(BPREADTYPE.MORNING.name(),"-1");

        int iMorningReminder = Integer.parseInt(strMorningReminder.split(":")[0]);
        Log.i(HMUtils.LOGTAG, "Morning: " + iMorningReminder);

        if (iMorningReminder >= BPREADTYPE.MORNING.getStartHour()
                && iMorningReminder < BPREADTYPE.MORNING.getEndHour() ) {
            bpReadingReminders.setReminder(BPREADTYPE.MORNING,strMorningReminder);
        } else {
            bpReadingReminders.setReminder(BPREADTYPE.MORNING, BPReadingReminders.MORNINGREMINDERS[0]);

        }

        //get afternoon reminder
        String strAfternoonReminder = pref.getString(BPREADTYPE.AFTERNOON.name(),"-1");

        int iAfternoonReminder = Integer.parseInt(strAfternoonReminder.split(":")[0]);
        Log.i(HMUtils.LOGTAG, "Afternoon: " + iAfternoonReminder);

        if (iAfternoonReminder >= BPREADTYPE.AFTERNOON.getStartHour()
                && iAfternoonReminder < BPREADTYPE.AFTERNOON.getEndHour() ) {
            bpReadingReminders.setReminder(BPREADTYPE.AFTERNOON,strAfternoonReminder);
        } else {
            bpReadingReminders.setReminder(BPREADTYPE.AFTERNOON, BPReadingReminders.AFTERNOONREMINDER[0]);
        }

        //get evening reminder
        String strEveningReminder = pref.getString(BPREADTYPE.EVENING.name(),"-1");
        int iEveningReminder = Integer.parseInt(strEveningReminder.split(":")[0]);
        Log.i(HMUtils.LOGTAG, "Evening: " + iEveningReminder);

        if (iEveningReminder >= BPREADTYPE.EVENING.getStartHour()
                && iEveningReminder < BPREADTYPE.EVENING.getEndHour() ) {
            bpReadingReminders.setReminder(BPREADTYPE.EVENING,strEveningReminder);
        } else {
            bpReadingReminders.setReminder(BPREADTYPE.EVENING, BPReadingReminders.EVENINGREMINDER[0]);
        }

        Log.i(HMUtils.LOGTAG, bpReadingReminders.toString() );

        return bpReadingReminders;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bpreminder, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.saveBPReminder:

                // get values from spinners.
                String strMorReminder = spinnerMorRem.getSelectedItem().toString();
                String strAftReminder = spinnerAftRem.getSelectedItem().toString();
                String srtEveReminder = spinnerEveRem.getSelectedItem().toString();

                SharedPreferences pref = this.getSharedPreferences(PREFFILE,MODE_PRIVATE);

                SharedPreferences.Editor writer = pref.edit();
                writer.clear();

                writer.putString(BPREADTYPE.MORNING.name(),strMorReminder);
                writer.putString(BPREADTYPE.AFTERNOON.name(),strAftReminder);
                writer.putString(BPREADTYPE.EVENING.name(),srtEveReminder);

                // writer to shared preferences.
                writer.commit();
                // and create alarm manager.
                finish();

                break;
        }

        return true;
    }

}

package com.azare.app.healthmonitor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

public class BPReminder extends AppCompatActivity {

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

        //initialize spinner from shared preferences.

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

                // write to shared preferences
                // and create alarm manager.
                break;
        }

        return true;
    }

}

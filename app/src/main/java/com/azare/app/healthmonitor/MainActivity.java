package com.azare.app.healthmonitor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.amitshekhar.DebugDB;
import com.azare.app.healthmonitor.utils.HMUtils;

public class MainActivity extends AppCompatActivity {

    Button btnShowBPReading;
    Button btnShowWeightRecord;

    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get ActionBar
        actionBar = getSupportActionBar();

        // Set below attributes to add logo in ActionBar.
        actionBar.setDisplayOptions( ActionBar.DISPLAY_SHOW_HOME
                | ActionBar.DISPLAY_SHOW_TITLE);

        actionBar.setTitle(getTitle());

        Log.i(HMUtils.LOGTAG, DebugDB.getAddressLog());

        btnShowBPReading = (Button) findViewById(R.id.btnShowBPReading);

        btnShowBPReading.setOnClickListener(btnShowBPReadingClicked);

        btnShowWeightRecord = (Button) findViewById(R.id.btnShowWeightRecords);

        btnShowWeightRecord.setOnClickListener(btnShowWeightRecordClicked);
    }

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

    /*
    Create intent and launch List BP Readings Activity.
     */
    private View.OnClickListener btnShowWeightRecordClicked = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            Intent lvWeightsIntent = new Intent(MainActivity.this, ListWeightRecord.class);
            startActivity(lvWeightsIntent);
        }
    };

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.dummyBP:
                Intent dummyBPIntent = new Intent(MainActivity.this, DummyBPActivity.class);
                startActivity(dummyBPIntent);

                return (true);

            case R.id.dummyWeight:

        }
        return (super.onOptionsItemSelected(item));
    }
}
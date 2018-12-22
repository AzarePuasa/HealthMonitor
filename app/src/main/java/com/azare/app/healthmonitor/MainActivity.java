package com.azare.app.healthmonitor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.amitshekhar.DebugDB;

public class MainActivity extends AppCompatActivity {

    Button btnShowBPReading;
    Button btnDummyBPReading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("Health Monitor", DebugDB.getAddressLog());

        btnShowBPReading = (Button) findViewById(R.id.btnShowBPReading);
        btnDummyBPReading = (Button) findViewById(R.id.btnDummyBPReading);

        btnShowBPReading.setOnClickListener(btnShowBPReadingClicked);
        btnDummyBPReading.setOnClickListener(btnDummyBPReadingClicked);
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

    private View.OnClickListener btnDummyBPReadingClicked = new View.OnClickListener(){

        @Override
        public void onClick(View v) {

            //TODO: pass start and end date to activity.
            Intent dummyBPIntent = new Intent(MainActivity.this, DummyBPActivity.class);
            startActivity(dummyBPIntent);
        }
    };
}
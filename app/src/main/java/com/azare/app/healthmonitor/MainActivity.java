package com.azare.app.healthmonitor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnPopulateDB;
    Button btnClearDB;
    Button btnShowBPReading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPopulateDB = (Button) findViewById(R.id.btnPopulateDB);
        btnClearDB = (Button) findViewById(R.id.btnClearDB);
        btnShowBPReading = (Button) findViewById(R.id.btnShowBPReading);


        //event listener for button.
        btnPopulateDB.setOnClickListener(btnPopulateDBClicked);

        btnClearDB.setOnClickListener(btnClearDBClicked);

        btnShowBPReading.setOnClickListener(btnShowBPReadingClicked);


    }

    private View.OnClickListener btnPopulateDBClicked = new View.OnClickListener(){

        @Override
        public void onClick(View v) {

        }
    };

    private View.OnClickListener btnClearDBClicked = new View.OnClickListener(){

        @Override
        public void onClick(View v) {

        }
    };

    private View.OnClickListener btnShowBPReadingClicked = new View.OnClickListener(){

        @Override
        public void onClick(View v) {

        }
    };
}

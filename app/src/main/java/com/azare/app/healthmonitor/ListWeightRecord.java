package com.azare.app.healthmonitor;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


/**
 * This activity will list the Weight Records.
 */
public class ListWeightRecord extends AppCompatActivity {

    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_weight_record);

        // Get ActionBar
        actionBar = getSupportActionBar();

        // Set below attributes to add logo in ActionBar.
        actionBar.setDisplayOptions( ActionBar.DISPLAY_SHOW_HOME
                | ActionBar.DISPLAY_SHOW_TITLE);

        actionBar.setTitle(getTitle());
    }
}

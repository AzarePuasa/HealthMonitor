package com.azare.app.healthmonitor;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.azare.app.healthmonitor.db.DAOBWeightRecord;
import com.azare.app.healthmonitor.model.WeightRecord;

import java.util.List;


/**
 * This activity will list the Weight Records.
 */
public class ListWeightRecord extends AppCompatActivity {

    DAOBWeightRecord daobWeightRecord;

    ActionBar actionBar;

    RecyclerView recyclerView;
    WeightRecordAdapter weightRecordAdapter;

    List<WeightRecord> lWeightRecords;

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

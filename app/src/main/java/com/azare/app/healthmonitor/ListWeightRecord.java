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
import android.widget.Toast;

import com.azare.app.healthmonitor.db.DAOBWeightRecord;
import com.azare.app.healthmonitor.model.WeightRecord;
import com.azare.app.healthmonitor.utils.HMUtils;

import java.util.List;


/**
 * This activity will list the Weight Records.
 */
public class ListWeightRecord extends AppCompatActivity {

    private final int REQUEST_NEW_WEIGHT_CODE = 21;

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

        recyclerView = (RecyclerView) findViewById(R.id.allWeightRecord);

        daobWeightRecord = new DAOBWeightRecord(this);

        lWeightRecords = daobWeightRecord.listAll();

        weightRecordAdapter = new WeightRecordAdapter(lWeightRecords);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(weightRecordAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_weightrecord, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addWeight:
                Intent addWeightIntent = new Intent(getApplicationContext(), AddWeightRecord.class);
                startActivityForResult(addWeightIntent, REQUEST_NEW_WEIGHT_CODE);



                return (true);
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (resultCode == RESULT_OK && requestCode == REQUEST_NEW_WEIGHT_CODE) {
            // Extract name value from result extras
            WeightRecord newWeightRecord = (WeightRecord) data.getSerializableExtra("new");

            //if record is successfully inserted into table, refresh the display by:
            // clear the display
            // get all records again

            if(daobWeightRecord.insert(newWeightRecord)) {
                String successMsg = getResources().getString(R.string.successmsg);
                Log.i(HMUtils.LOGTAG, successMsg);

                Toast.makeText(getApplicationContext(), successMsg, Toast.LENGTH_SHORT ).show();

                //clear list
                weightRecordAdapter.clear();

                //query table for readings.
                lWeightRecords = daobWeightRecord.listAll();

                //update list in adapter.
                weightRecordAdapter.setWeightRecords(lWeightRecords);

                //call notifyDataSetChanged()
                weightRecordAdapter.notifyDataSetChanged();
            }
        }

    }
}

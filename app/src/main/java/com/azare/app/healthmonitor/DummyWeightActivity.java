package com.azare.app.healthmonitor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.azare.app.healthmonitor.db.DAOBPReading;
import com.azare.app.healthmonitor.db.DAOBWeightRecord;
import com.azare.app.healthmonitor.utils.HMUtils;

public class DummyWeightActivity extends AppCompatActivity {

    Button btnPopulateDB;
    Button btnClearDB;

    DAOBWeightRecord daoWeightRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy_weight);

        daoWeightRecord = new DAOBWeightRecord(this);

        btnPopulateDB = (Button) findViewById(R.id.btnPopulateDB);
        btnClearDB = (Button) findViewById(R.id.btnClearDB);

        btnPopulateDB.setOnClickListener(btnPopulateDBClicked);

        btnClearDB.setOnClickListener(btnClearDBClicked);
    }

    private View.OnClickListener btnPopulateDBClicked = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            //TODO: Generate Weight Record and write to db.

            try {
                DummyWeightRecords dummyWeightRecords = new DummyWeightRecords();

                dummyWeightRecords.getDummyReadings();

                if (dummyWeightRecords.getDummyReadings().size() > 0) {
                    //call DAO method to write to db.

                } else {
                    throw new Exception("Not able to generate Dummy Weight Record.");
                }
            } catch (Exception exp) {
                Log.e(HMUtils.LOGTAG
                        , "Error Populating BP Readings Table" + exp.getMessage());
            }
        }
    };

    private View.OnClickListener btnClearDBClicked = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            //TODO: Call DAO method to delete Weight Record from db.
        }
    };
}

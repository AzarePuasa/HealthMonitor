package com.azare.app.healthmonitor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.azare.app.healthmonitor.db.DAOBPReading;
import com.azare.app.healthmonitor.model.BPREADTYPE;
import com.azare.app.healthmonitor.model.BPReading;

import java.util.List;

public class DummyBPActivity extends AppCompatActivity {

    Button btnPopulateDB;
    Button btnClearDB;
    TextView tvSystolic;
    TextView tvDiastolic;
    Button btnGetNormalBP;

    DAOBPReading daobpReading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy_bp);

        daobpReading = new DAOBPReading(this);

        btnPopulateDB = (Button) findViewById(R.id.btnPopulateDB);
        btnClearDB = (Button) findViewById(R.id.btnClearDB);
        btnGetNormalBP = (Button) findViewById(R.id.btnNormalBP);

        tvSystolic = (TextView) findViewById(R.id.tvMin);
        tvDiastolic = (TextView) findViewById(R.id.tvMax);

        //event listener for button.
        btnPopulateDB.setOnClickListener(btnPopulateDBClicked);

        btnClearDB.setOnClickListener(btnClearDBClicked);

        btnGetNormalBP.setOnClickListener(btnNormalBPClicked);
    }

    /*
 Populate BP Readings Table with dummy data.
  */
    private View.OnClickListener btnPopulateDBClicked = new View.OnClickListener(){

        @Override
        public void onClick(View v) {

            String strStartDate = "01/11/2018";
            String strEndDate = "30/11/2018";
            try {
                DummyBPReadings dummyBPs = new DummyBPReadings(strStartDate, strEndDate);

                dummyBPs.generateDummyReading();

                if (dummyBPs.getDummyReadings().size() > 0) {
                    populateBPReadingTbl(dummyBPs.getDummyReadings());
                }
                else {
                    throw new Exception("Not able to generate Dummy BP Reading.");
                }
            } catch (Exception exp)
            {
                Log.e("Health Monitor"
                        , "Error Populating BP Readings Table" + exp.getMessage());
            }

            Toast.makeText(getApplicationContext()
                    ,"This button is to populate dummy rows the Table",
                    Toast.LENGTH_LONG).show();
        }
    };

    /*
    Delete all entries in BP Readings Table.
     */
    private View.OnClickListener btnClearDBClicked = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            int iCleared = daobpReading.delete(BPREADTYPE.AFTERNOON);
            Toast.makeText(getApplicationContext()
                    ,"Cleared" + iCleared + " rows.",
                    Toast.LENGTH_LONG).show();
        }
    };

    private View.OnClickListener btnNormalBPClicked = new View.OnClickListener(){

        @Override
        public void onClick(View v) {

            int[] iNormalBP = DummyBPReadings.generateNormalBP();

            String strSystolic = Integer.toString(iNormalBP[0]);
            String strDiastolic = Integer.toString(iNormalBP[1]);

            tvSystolic.setText(strSystolic);
            tvDiastolic.setText(strDiastolic);
        }
    };

    /*
    Populate table for a specified start and end date.
    */
    private void populateBPReadingTbl(List<BPReading> lBPReadings) {
        Log.i("Health Monitor", "Reading Count: " + lBPReadings.size());

        InsertBPReading insertBPReading = new InsertBPReading(getApplicationContext());

        BPReading[] array = lBPReadings.toArray(new BPReading[lBPReadings.size()]);

        insertBPReading.execute(array);
    }
}

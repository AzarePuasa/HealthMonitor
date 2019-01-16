package com.azare.app.healthmonitor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.azare.app.healthmonitor.db.DAOBPReading;
import com.azare.app.healthmonitor.model.BPREADTYPE;
import com.azare.app.healthmonitor.model.BPReading;
import com.azare.app.healthmonitor.model.BPReadingFactory;
import com.azare.app.healthmonitor.utils.HMUtils;

import java.util.Date;

public class AddBPReading extends AppCompatActivity {

    TextView tvReadingDate;
    TextView tvReadingType;

    EditText etSystolic;
    EditText etDiastolic;
    Button btnSubmit;

    int iDay;
    int iMth;
    int iYear;
    BPREADTYPE bpreadtype;

    DAOBPReading daobpReading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bpreading);

        daobpReading = new DAOBPReading(this);

        tvReadingDate = (TextView) findViewById(R.id.tvNewBPDdate);
        tvReadingType = (TextView) findViewById(R.id.tvNewBPType);

        etSystolic = (EditText) findViewById(R.id.etNewSystolic);
        etDiastolic = (EditText) findViewById(R.id.etNewDiastolic);

        btnSubmit = (Button) findViewById(R.id.btnAddBPReading);

        //populate Date and Reading type field.
        //Get current Date and extract Day, Month, Year, Hour and Minute.
        Date date = new Date();
        date.setTime(System.currentTimeMillis());
        String strDate = HMUtils.getCurrentDate(date);
        iDay = Integer.parseInt(strDate.split("/")[0]);
        iMth = Integer.parseInt(strDate.split("/")[1]);
        iYear = Integer.parseInt(strDate.split("/")[2]);

        String strTime = HMUtils.getCurrentTime(date);
        int hour = Integer.parseInt(strTime.split(":")[0]);
        Log.i("Health Monitor", "Hour: " + hour );
        bpreadtype = BPREADTYPE.getCurrentReadType(hour);
        Log.i("Health Monitor", "Read Type: " + bpreadtype.name() );

        //Create date string and set TextView.
        tvReadingDate.setText(strDate);

        tvReadingType.setText(bpreadtype.name());

        btnSubmit.setOnClickListener(btnSubmitClicked);
    }

    private View.OnClickListener btnSubmitClicked = new View.OnClickListener(){

        @Override
        public void onClick(View v) {

            int iSystolic = Integer.parseInt(etSystolic.getText().toString());
            int iDiastolic = Integer.parseInt(etDiastolic.getText().toString());

            //Validate readings are valid. Else prompt error.

            long timestamp = System.currentTimeMillis();

            BPReading bpReading = BPReadingFactory.createBPReading(iDay,iMth,iYear,iSystolic,iDiastolic,bpreadtype);

            //create intent and send
            Intent result = new Intent();

            // Pass relevant data back as a result
            result.putExtra("new", bpReading);

            // Activity finished ok, return the data
            setResult(RESULT_OK, result); // set result code and bundle data for response

            finish(); // closes the activity, pass data to parent
        }
    };
}

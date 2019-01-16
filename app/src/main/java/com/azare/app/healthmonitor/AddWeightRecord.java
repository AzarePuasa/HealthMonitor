package com.azare.app.healthmonitor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.azare.app.healthmonitor.db.DAOBWeightRecord;
import com.azare.app.healthmonitor.model.WeightRecord;
import com.azare.app.healthmonitor.utils.HMUtils;

import java.util.Date;

public class AddWeightRecord extends AppCompatActivity {

    TextView tvWeightDate;
    EditText etNewWeight;
    Button btnSubmitWeight;

    int iDay;
    int iMth;
    int iYear;

    DAOBWeightRecord daobWeightRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_weight_record);

        daobWeightRecord = new DAOBWeightRecord(this);

        tvWeightDate = (TextView) findViewById(R.id.tvWeightDdate);
        etNewWeight = (EditText) findViewById(R.id.etNewWeight);

        btnSubmitWeight = (Button) findViewById(R.id.btnSubmitWeight);

        //auto populate Date field.
        //Get current Date and extract Day, Month, Year, Hour and Minute.
        Date date = new Date();
        date.setTime(System.currentTimeMillis());
        String strDate = HMUtils.getCurrentDate(date);
        iDay = Integer.parseInt(strDate.split("/")[0]);
        iMth = Integer.parseInt(strDate.split("/")[1]);
        iYear = Integer.parseInt(strDate.split("/")[2]);

        tvWeightDate.setText(strDate);

        btnSubmitWeight.setOnClickListener(btnSubmitClicked);
    }

    private View.OnClickListener btnSubmitClicked = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            int iWeight = Integer.parseInt(etNewWeight.getText().toString());

            long timestamp = System.currentTimeMillis();

            WeightRecord newWeightRecord = new WeightRecord(iDay, iMth, iYear, iWeight, timestamp);

            Intent result = new Intent();

            // Pass relevant data back as a result
            result.putExtra("new", newWeightRecord);

            // Activity finished ok, return the data
            setResult(RESULT_OK, result); // set result code and bundle data for response

            finish(); // closes the activity, pass data to parent
        }
    };
}

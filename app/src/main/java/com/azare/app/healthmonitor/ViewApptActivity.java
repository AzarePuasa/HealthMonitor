package com.azare.app.healthmonitor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.azare.app.healthmonitor.db.DAOApptRecord;
import com.azare.app.healthmonitor.model.Appointment;

public class ViewApptActivity extends AppCompatActivity {

    long id;
    Appointment appointment;

    DAOApptRecord daoApptRecord;

    TextView tvApptDate;
    TextView tvApptTime;
    TextView tvApptLoc;
    TextView tvApptPurpose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appt);

        tvApptDate = (TextView) findViewById(R.id.tvApptDate);
        tvApptTime = (TextView) findViewById(R.id.tvApptTime);
        tvApptLoc = (TextView) findViewById(R.id.tvApptLoc);
        tvApptPurpose = (TextView) findViewById(R.id.tvApptPurpose);

        daoApptRecord = new DAOApptRecord(this);

        Bundle extras = getIntent().getExtras();

        id = extras.getLong("apptId");

        appointment = daoApptRecord.getApptById(id);

        tvApptDate.setText(appointment.getDay() + "/" + appointment.getMonth() + "/" + appointment.getYear());

        tvApptTime.setText(appointment.getHour() + ":" + appointment.getMinutes());

        tvApptLoc.setText(appointment.getLocation());

        tvApptPurpose.setText(appointment.getPurpose());

    }
}

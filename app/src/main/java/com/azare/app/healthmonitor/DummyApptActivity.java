package com.azare.app.healthmonitor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.azare.app.healthmonitor.db.DAOApptRecord;
import com.azare.app.healthmonitor.model.Appointment;
import com.azare.app.healthmonitor.utils.HMUtils;

import java.util.ArrayList;
import java.util.List;

public class DummyApptActivity extends AppCompatActivity {

    Button btnPopulateDB;
    Button btnClearDB;

    DAOApptRecord daoApptRecord;

    List<Appointment> lAppointments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy_appt);

        lAppointments = new ArrayList<Appointment>();

        daoApptRecord = new DAOApptRecord(this);

        btnPopulateDB = (Button) findViewById(R.id.btnPopulateDB);
        btnClearDB = (Button) findViewById(R.id.btnClearDB);

        btnPopulateDB.setOnClickListener(btnPopulateDBClicked);

        btnClearDB.setOnClickListener(btnClearDBClicked);
    }

    private View.OnClickListener btnPopulateDBClicked = new View.OnClickListener(){

        @Override
        public void onClick(View v) {

            DummyAppointments dummyAppts = new DummyAppointments();

            dummyAppts.generateDummyReading();

            Log.i(HMUtils.LOGTAG, "Completed Appointments");
            for (Appointment appointment : dummyAppts.getCompletedAppts()) {

                lAppointments.add(appointment);
            }

            Log.i(HMUtils.LOGTAG, "Upcoming Appointments");
            for (Appointment appointment : dummyAppts.getUpcomingAppts()) {
                lAppointments.add(appointment);
            }

            for ( Appointment appointment : lAppointments) {
                daoApptRecord.insert(appointment);
            }

            lAppointments.clear();
        }
    };

    private View.OnClickListener btnClearDBClicked = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            daoApptRecord.delete();
        }
    };
}

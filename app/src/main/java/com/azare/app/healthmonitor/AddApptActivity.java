package com.azare.app.healthmonitor;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.azare.app.healthmonitor.db.DAOApptRecord;
import com.azare.app.healthmonitor.model.Appointment;
import com.azare.app.healthmonitor.model.ApptLocations;
import com.azare.app.healthmonitor.utils.HMUtils;

import java.util.Calendar;

public class AddApptActivity extends AppCompatActivity {

    //button
    Button btnApptDatePicker;
    Button btnApptTimePicker;

    //edit text
    EditText etApptDate;
    EditText etApptTime;

    //spinner
    Spinner spinnerApptLoc;

    //multiline edit text.
    EditText etApptPurpose;

    //Textview
    TextView tvTitle;

    public enum FormMode {
        ADD, EDIT;
    }

    FormMode formMode;

    DAOApptRecord daoApptRecord;

    long m_ApptId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appt);

        daoApptRecord = new DAOApptRecord(this);

        //button
        btnApptDatePicker = (Button) findViewById(R.id.btnApptDatePicker);
        btnApptTimePicker = (Button) findViewById(R.id.btnApptTimePicker);

        //edit text
        etApptDate = (EditText) findViewById(R.id.etApptDate);
        etApptTime = (EditText) findViewById(R.id.etApptTime);

        //spinner
        spinnerApptLoc = (Spinner) findViewById(R.id.spinnerApptLoc);

        //multiline edit text.
        etApptPurpose = (EditText) findViewById(R.id.etApptPurpose);

        tvTitle = (TextView) findViewById(R.id.title);

        btnApptDatePicker.setOnClickListener(btnApptDatePickerClicked);
        btnApptTimePicker.setOnClickListener(btnApptTimePickerClicked);

        //populate spinner.
        ArrayAdapter<ApptLocations> locations = new ArrayAdapter<ApptLocations>(this,
                android.R.layout.simple_list_item_1, ApptLocations.values());

        spinnerApptLoc.setAdapter(locations);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            m_ApptId = extras.getLong("apptId",-1);

            if (m_ApptId > -1) {
                formMode =  FormMode.EDIT;
                populateFields(m_ApptId);
                tvTitle.setText(getResources().getText(R.string.editAppointment));
            }
        } else {
            formMode =  FormMode.ADD;
            tvTitle.setText(getResources().getText(R.string.addAppointment));
        }
    }

    private void populateFields(long id) {
        //get the apptEdit
        Appointment apptEdit = daoApptRecord.getApptById(id);

        etApptDate.setText(apptEdit.getDay() + "/" + apptEdit.getMonth() + "/" + apptEdit.getYear());

        etApptTime.setText(apptEdit.getHour() + ":" + apptEdit.getMinutes());

        int pos = 0;
        for (ApptLocations loc : ApptLocations.values()) {
            if (loc.name().equals(apptEdit.getLocation())) {
                break;
            }
            pos++;
        }

        spinnerApptLoc.setSelection(pos);

        etApptPurpose.setText(apptEdit.getPurpose());
    }

    private View.OnClickListener btnApptDatePickerClicked = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            ApptDatePicker apptDatePicker = new ApptDatePicker();
            apptDatePicker.show(getSupportFragmentManager(), "Select Appointment Date");
        }
    };

    private View.OnClickListener btnApptTimePickerClicked = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            ApptTimePicker apptTimePicker = new ApptTimePicker();
            apptTimePicker.show(getSupportFragmentManager(), "Select Appointment Time");
        }
    };

    public static class ApptDatePicker extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            EditText etApptDate = (EditText) getActivity().findViewById(R.id.etApptDate);
            etApptDate.setText(dayOfMonth + "/" + ++month + "/" + year);
        }
    }

    public static class ApptTimePicker extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState){
            //Use the current time as the default values for the time picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            //Create and return a new instance of TimePickerDialog
            return new TimePickerDialog(getActivity(),this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        @Override
        public void onTimeSet(TimePicker timePicker, int hour, int minute) {

            String strHour = Integer.toString(hour);

            StringBuilder stbMinutes = new StringBuilder();

            if (minute < 10) {
                stbMinutes.append("0").append(minute);
            } else {
                stbMinutes.append(minute);
            }

            EditText etApptTime = (EditText) getActivity().findViewById(R.id.etApptTime);
            etApptTime.setText(hour + ":" + stbMinutes.toString());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_appt, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.saveAppt:

                //create appointment and return back.
                String strDate = etApptDate.getText().toString();
                String strTime = etApptTime.getText().toString();
                String strLocation = spinnerApptLoc.getSelectedItem().toString();
                String strPurpose = etApptPurpose.getText().toString();

                int iDay = Integer.parseInt(strDate.split("/")[0]);
                int iMonth = Integer.parseInt(strDate.split("/")[1]);
                int iYear = Integer.parseInt(strDate.split("/")[2]);
                String strHour = strTime.split(":")[0];
                String strMinutes = strTime.split(":")[1];

                Appointment appointment = new Appointment(iDay, iMonth, iYear, strHour, strMinutes);

                appointment.setLocation(strLocation);

                appointment.setPurpose(strPurpose);

                appointment.setTimestamp(System.currentTimeMillis());

                Log.i(HMUtils.LOGTAG, appointment.toString());

                //Edit to be processed here.

                //Add to be send back.
                if (formMode == FormMode.EDIT) {

                    if (m_ApptId > -1) {
                        //update appointment
                        if(daoApptRecord.update(m_ApptId,appointment)) {
                            Toast.makeText(getApplicationContext(),
                                    "Update Success",Toast.LENGTH_LONG).show();

                            ApptPagerAdapter apptPagerAdapter = ApptPagerAdapter.getInstance();

                            ApptUpcomingFrag frag = (ApptUpcomingFrag) apptPagerAdapter.getItem(1);

                            frag.updateList();

                            finish();
                        } else {
                            //Toast fail.
                            Toast.makeText(getApplicationContext(),
                                    "Update Fail",Toast.LENGTH_LONG).show();
                        }
                    } else {
                        //Toast Error.
                        Toast.makeText(getApplicationContext(),
                                "Invalid id to update",Toast.LENGTH_LONG).show();
                    }

                } else {
                    Intent result = new Intent();

                    // Pass relevant data back as a result
                    result.putExtra("id", m_ApptId); //-1 for new Appt

                    result.putExtra("appt", appointment);

                    // Activity finished ok, return the data
                    setResult(RESULT_OK, result); // set result code and bundle data for response

                    finish(); // closes the activity, pass data to parent

                    return (true);
                }
        }
        return true;
    }

}

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
import android.widget.TimePicker;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appt);

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

        btnApptDatePicker.setOnClickListener(btnApptDatePickerClicked);
        btnApptTimePicker.setOnClickListener(btnApptTimePickerClicked);

        //populate spinner.
        ArrayAdapter<ApptLocations> locations = new ArrayAdapter<ApptLocations>(this,
                android.R.layout.simple_list_item_1, ApptLocations.values());

        spinnerApptLoc.setAdapter(locations);
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

                Appointment appointment = new Appointment(iDay, iMonth, iYear, strHour, strMinutes );

                appointment.setLocation(strLocation);

                appointment.setPurpose(strPurpose);

                appointment.setTimestamp(System.currentTimeMillis());

                Log.i(HMUtils.LOGTAG, appointment.toString());

                Intent result = new Intent();

                // Pass relevant data back as a result
                result.putExtra("new", appointment);

                // Activity finished ok, return the data
                setResult(RESULT_OK, result); // set result code and bundle data for response

                finish(); // closes the activity, pass data to parent

                return (true);
        }
        return true;
    }

}

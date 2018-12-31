package com.azare.app.healthmonitor;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;

public class FilterBPActivity extends AppCompatActivity {

    Button btnStartDatePicker;
    Button btnEndDatePicker;

    EditText etCustomStartDate;
    EditText etCustomEndDate;

    private RadioGroup radioGroupFilter;
    private RadioButton radioBtnMonth;
    private RadioButton radioBtnWeek;
    private RadioButton radioBtnCustom;

    Button btnGetReadings;

    private String LOGTAG = "Health Monitor";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_bp_activity);

        btnStartDatePicker = (Button) findViewById(R.id.btnStartDatePicker);
        btnEndDatePicker = (Button) findViewById(R.id.btnEndDatePicker);

        etCustomStartDate = (EditText) findViewById(R.id.etCustomStartDate);
        etCustomEndDate = (EditText) findViewById(R.id.etCustomEndDate);

        btnStartDatePicker.setOnClickListener(btnStartDatePickerClicked);
        btnEndDatePicker.setOnClickListener(btnEndDatePickerClicked);

        radioGroupFilter = (RadioGroup) findViewById(R.id.radioGroup_filter);
        radioBtnMonth = (RadioButton) findViewById(R.id.filterMth);
        radioBtnWeek = (RadioButton) findViewById(R.id.filterWeek);
        radioBtnCustom = (RadioButton) findViewById(R.id.filterCustom);

        btnGetReadings = (Button) findViewById(R.id.getReadings);

        btnGetReadings.setOnClickListener(btnGetReadingClicked);

        // When radio button "Month" checked change.
        radioBtnMonth.setOnCheckedChangeListener(radioBtnMonthChanged);

        // When radio button "Week" checked change.
       radioBtnWeek.setOnCheckedChangeListener(radioBtnWeekChanged);

        // When radio button "Custom" checked change.
       radioBtnCustom.setOnCheckedChangeListener(radioBtnCustomChanged);
    }

    private View.OnClickListener btnStartDatePickerClicked = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            CustomStartDatePicker startDatePicker = new CustomStartDatePicker();
            startDatePicker.show(getSupportFragmentManager(), "Start Date picker");
        }
    };

    private View.OnClickListener btnEndDatePickerClicked = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            CustomEndDatePicker endDatePicker = new CustomEndDatePicker();
            endDatePicker.show(getSupportFragmentManager(), "End Date picker");

        }
    };

    public static class CustomStartDatePicker extends DialogFragment
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
            EditText etCustomStart = (EditText) getActivity().findViewById(R.id.etCustomStartDate);
            etCustomStart.setText(dayOfMonth + "/" + month + "/" + year);
        }
    }

    public static class CustomEndDatePicker extends DialogFragment
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
            EditText etCustomEnd = (EditText) getActivity().findViewById(R.id.etCustomEndDate);
            etCustomEnd.setText(dayOfMonth + "/" + month + "/" + year);
        }
    }

    private View.OnClickListener btnGetReadingClicked = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            doFilterBPReading();
        }
    };

    // When radio button checked change.
    private void doOnFilterOptionChanged(CompoundButton buttonView, boolean isChecked)  {
        RadioButton radio =(RadioButton) buttonView;

        Log.i(LOGTAG, "RadioButton "+ radio.getText()+" : "+ isChecked);
    }

    private RadioButton.OnCheckedChangeListener radioBtnMonthChanged = new RadioButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            doOnFilterOptionChanged(buttonView,isChecked);
        }
    };

    private RadioButton.OnCheckedChangeListener radioBtnWeekChanged = new RadioButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            doOnFilterOptionChanged(buttonView,isChecked);
        }
    };

    private RadioButton.OnCheckedChangeListener radioBtnCustomChanged = new RadioButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            doOnFilterOptionChanged(buttonView,isChecked);
        }
    };


    // When button "Save" clicked.
    private void doFilterBPReading()  {

        //get which radio button is selected.
        int filter = this.radioGroupFilter.getCheckedRadioButtonId();

        //if custom is selected, need to verify

        RadioButton radioButtonFilter = (RadioButton) this.findViewById(filter);

        String message ="Filter: "+ radioButtonFilter.getText();

        Log.i(LOGTAG, message);

        Toast.makeText(this,message,Toast.LENGTH_LONG).show();

        if(radioButtonFilter.getText().equals(getResources().getString(R.string.filterMonth)))
        {
            //End date: current date

            //Start date: current date - 1 Month
        } else if (radioButtonFilter.getText().equals(getResources().getString(R.string.filterWeek))) {
            //End date: current date

            //Start date: current date - 7 days
        } else if (radioButtonFilter.getText().equals(getResources().getString(R.string.filterCustom))) {
            //End date: text field

            //Start date: text field
        }




    }

}

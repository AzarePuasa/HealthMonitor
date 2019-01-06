package com.azare.app.healthmonitor;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
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

import com.azare.app.healthmonitor.model.BPFILTERTYPE;
import com.azare.app.healthmonitor.model.BPReadingFilter;
import com.azare.app.healthmonitor.utils.HMUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FilterBPActivity extends AppCompatActivity {

    Button btnStartDatePicker;
    Button btnEndDatePicker;

    EditText etCustomStartDate;
    EditText etCustomEndDate;

    private RadioGroup radioGroupFilter;
    private RadioButton radioBtnNone;
    private RadioButton radioBtnMonth;
    private RadioButton radioBtnWeek;
    private RadioButton radioBtnCustom;

    Button btnGetReadings;

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
        radioBtnNone = (RadioButton) findViewById(R.id.filterNone);
        radioBtnMonth = (RadioButton) findViewById(R.id.filterMth);
        radioBtnWeek = (RadioButton) findViewById(R.id.filterWeek);
        radioBtnCustom = (RadioButton) findViewById(R.id.filterCustom);

        btnGetReadings = (Button) findViewById(R.id.getReadings);

        btnGetReadings.setOnClickListener(btnGetReadingClicked);

        // When radio button "None" checked change.
        radioBtnMonth.setOnCheckedChangeListener(radioBtnNoneChanged);

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
            filterResult();
        }
    };

    // When radio button checked change.
    private void doOnFilterOptionChanged(CompoundButton buttonView, boolean isChecked)  {
        RadioButton radio =(RadioButton) buttonView;

        Log.i(HMUtils.LOGTAG, "RadioButton "+ radio.getText()+" : "+ isChecked);
    }

    private RadioButton.OnCheckedChangeListener radioBtnNoneChanged = new RadioButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            doOnFilterOptionChanged(buttonView,isChecked);
        }
    };

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
    private void filterResult()  {

        //get which radio button is selected.
        int filter = this.radioGroupFilter.getCheckedRadioButtonId();

        RadioButton radioButtonFilter = (RadioButton) this.findViewById(filter);

        String message ="Filter: "+ radioButtonFilter.getText();

        Log.i(HMUtils.LOGTAG, message);

        Toast.makeText(this,message,Toast.LENGTH_LONG).show();

        Calendar cal = Calendar.getInstance();

        Date dateToday = cal.getTime();

        if(radioButtonFilter.getText()
                .equals(getResources().getString(R.string.filterMonth)))
        {
            //End date: Today's Date

            //Start date: Today - 1 Month
            cal.add(Calendar.DATE, -30);
            Date date30DaysAgo = cal.getTime();

            sendResult(BPFILTERTYPE.MONTH, date30DaysAgo, dateToday);


        } else if (radioButtonFilter.getText()
                .equals(getResources().getString(R.string.filterWeek))) {
            //End date: Today

            //Start date: Today - 7 days
            cal.add(Calendar.DATE, -7);
            Date date7DaysAgo = cal.getTime();

            sendResult(BPFILTERTYPE.WEEK, date7DaysAgo, dateToday);

        } else if (radioButtonFilter.getText()
                .equals(getResources().getString(R.string.filterCustom))) {
            //End date: text field
            String strStartDate = etCustomStartDate.getText().toString();

            //Start date: text field
            String strEndDate = etCustomEndDate.getText().toString();

            //verify end date is >= start date.
            try
            {
                SimpleDateFormat sdf = new SimpleDateFormat(HMUtils.DATE_FORMAT);

                Date dtStart = sdf.parse(strStartDate);
                Date dtEnd = sdf.parse(strEndDate);

                if ((dtStart.compareTo(dtEnd) < 0) || (dtStart.compareTo(dtEnd) == 0)) {
                    sendResult(BPFILTERTYPE.CUSTOM, dtStart, dtEnd);
                }
            }catch(ParseException pexp) {
                Log.e(HMUtils.LOGTAG,"Unable to parse date string.");

            }

        } else if (radioButtonFilter.getText()
                .equals(getResources().getString(R.string.filterNone))) {
            sendResult(BPFILTERTYPE.ALL, null, null );
        }
        else {
            Log.i(HMUtils.LOGTAG, "Invalid Filter Type");
        }
    }

    private void sendResult(BPFILTERTYPE type, Date dateStart, Date dateEnd) {

        BPReadingFilter readingFilter = new BPReadingFilter(type, dateStart, dateEnd);

        Intent result = new Intent();

        // Pass relevant data back as a result
        result.putExtra("filter", readingFilter);

        // Activity finished ok, return the data
        setResult(RESULT_OK, result); // set result code and bundle data for response
        finish(); // closes the activity, pass data to parent
    }

}

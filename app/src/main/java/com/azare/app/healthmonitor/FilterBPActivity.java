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
import android.widget.LinearLayout;
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
    Button btnSpecificDatePicker;

    EditText etCustomStartDate;
    EditText etCustomEndDate;
    EditText etSpecificDate;

    private RadioGroup radioGroupFilter;
    private RadioButton radioBtnNone;
    private RadioButton radioBtnCustom;
    private RadioButton radioBtnSpecific;

    Button btnGetReadings;

    LinearLayout layoutCustomDate;
    LinearLayout layoutSpecificDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_bp_activity);

        btnStartDatePicker = (Button) findViewById(R.id.btnStartDatePicker);
        btnEndDatePicker = (Button) findViewById(R.id.btnEndDatePicker);
        btnSpecificDatePicker = (Button) findViewById(R.id.btnSpecificDatePicker);

        btnStartDatePicker.setOnClickListener(btnStartDateClicked);
        btnEndDatePicker.setOnClickListener(btnEndDateClicked);
        btnSpecificDatePicker.setOnClickListener(btnSpecificDateClicked);

        etCustomStartDate = (EditText) findViewById(R.id.etCustomStartDate);
        etCustomEndDate = (EditText) findViewById(R.id.etCustomEndDate);
        etSpecificDate = (EditText) findViewById(R.id.etSpecificDate);

        radioGroupFilter = (RadioGroup) findViewById(R.id.radioGroup_filter);
        radioBtnNone = (RadioButton) findViewById(R.id.filterNone);
        radioBtnCustom = (RadioButton) findViewById(R.id.filterCustom);
        radioBtnSpecific = (RadioButton) findViewById(R.id.filterSpecific);

        btnGetReadings = (Button) findViewById(R.id.getReadings);

        btnGetReadings.setOnClickListener(btnGetReadingClicked);

        // When radio button "All Readings" checked change.
        radioBtnNone.setOnCheckedChangeListener(radioBtnNoneChanged);

        // When radio button "Custom" checked change.
       radioBtnCustom.setOnCheckedChangeListener(radioBtnCustomChanged);

        // When radio button "Custom" checked change.
        radioBtnSpecific.setOnCheckedChangeListener(radioBtnSpecificChanged);

        //Set Default check to "All Readings"
        radioGroupFilter.check(R.id.filterNone);

        //Hide button & edit text for custom
        layoutCustomDate = (LinearLayout) findViewById(R.id.LayoutCustomDate);
        layoutCustomDate.setVisibility(View.GONE);

        //Hide button & edit text for specific
        layoutSpecificDate = (LinearLayout) findViewById(R.id.LayoutSpecificDate);
        layoutSpecificDate.setVisibility(View.GONE);
    }

    private View.OnClickListener btnStartDateClicked = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            CustomStartDatePicker startDatePicker = new CustomStartDatePicker();
            startDatePicker.show(getSupportFragmentManager(), "Select Start Date");
        }
    };

    private View.OnClickListener btnEndDateClicked = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            CustomEndDatePicker endDatePicker = new CustomEndDatePicker();
            endDatePicker.show(getSupportFragmentManager(), "Select End Date");
        }
    };

    private View.OnClickListener btnSpecificDateClicked = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            SpecificDatePicker specificDatePicker = new SpecificDatePicker();
            specificDatePicker.show(getSupportFragmentManager(),"Select Specific Date");
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
            etCustomStart.setText(dayOfMonth + "/" + ++month + "/" + year);
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
            etCustomEnd.setText(dayOfMonth + "/" + ++month + "/" + year);
        }
    }

    public static class SpecificDatePicker extends DialogFragment
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
            EditText etSpecificDate = (EditText) getActivity().findViewById(R.id.etSpecificDate);
            etSpecificDate.setText(dayOfMonth + "/" + ++month + "/" + year);
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

    private RadioButton.OnCheckedChangeListener radioBtnCustomChanged = new RadioButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            doOnFilterOptionChanged(buttonView,isChecked);
            if(isChecked) {
                Log.i(HMUtils.LOGTAG, "Show Edit field");
                layoutCustomDate.setVisibility(View.VISIBLE);

            }
            else {
                Log.i(HMUtils.LOGTAG, "Hide Edit field");
                layoutCustomDate.setVisibility(View.GONE);
            }
        }
    };

    private RadioButton.OnCheckedChangeListener radioBtnSpecificChanged = new RadioButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            doOnFilterOptionChanged(buttonView,isChecked);
            if(isChecked) {
                Log.i(HMUtils.LOGTAG, "Show Specific Edit field");
                layoutSpecificDate.setVisibility(View.VISIBLE);
            }
            else {
                Log.i(HMUtils.LOGTAG, "Hide Specific Edit field");
                layoutSpecificDate.setVisibility(View.GONE);
            }
        }
    };

    // When button "Save" clicked.
    private void filterResult() {

        //get which radio button is selected.
        int filter = this.radioGroupFilter.getCheckedRadioButtonId();

        RadioButton radioButtonFilter = (RadioButton) this.findViewById(filter);

        String message ="Filter: "+ radioButtonFilter.getText();

        Log.i(HMUtils.LOGTAG, message);

        Toast.makeText(this,message,Toast.LENGTH_LONG).show();

        Calendar cal = Calendar.getInstance();

        Date dtToday = cal.getTime();

        if (radioButtonFilter.getText()
                .equals(getResources().getString(R.string.filterNone))) {
            // All reading.
            BPReadingFilter readingFilter = new BPReadingFilter(BPFILTERTYPE.ALL);
            sendResult(readingFilter);
        } else if (radioButtonFilter.getText()
                .equals(getResources().getString(R.string.filterCustom))) {
            // Filter specific period.

            //End date: text field
            String strStartDate = etCustomStartDate.getText().toString();

            //Start date: text field
            String strEndDate = etCustomEndDate.getText().toString();

            //verify end date is >= start date.
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(HMUtils.DATE_FORMAT);

                Date dtStart = sdf.parse(strStartDate);
                Date dtEnd = sdf.parse(strEndDate);

                //check if start is not after end date.
                // or start is equal to TODAY.

                //Start date before end date or Today.
                if (((dtStart.compareTo(dtEnd) < 0) || (dtStart.compareTo(dtToday) == 0))) {
                    // End date must be after start date or Today.
                    if (dtEnd.compareTo(dtStart) > 0 || dtEnd.compareTo(dtToday) == 0) {
                        BPReadingFilter readingFilter = new BPReadingFilter(BPFILTERTYPE.CUSTOM);
                        readingFilter.setStartDate(dtStart);
                        readingFilter.setEndDate(dtEnd);
                        sendResult(readingFilter);
                    } else {
                        Toast.makeText(getApplicationContext(),"Invalid End Date",Toast.LENGTH_LONG);
                        Log.i(HMUtils.LOGTAG, "Invalid End Date");
                    }
                } else {
                    Toast.makeText(getApplicationContext(),"Invalid Start Date",Toast.LENGTH_LONG);
                    Log.i(HMUtils.LOGTAG, "Invalid Start Date");
                }
            }catch(Exception exp) {
                Toast.makeText(getApplicationContext(),"Invalid filter data",Toast.LENGTH_LONG);
                Log.e(HMUtils.LOGTAG,"Invalid filter data. " + exp.getMessage());
            }
        } else if(radioButtonFilter.getText()
                .equals(getResources().getString(R.string.filterSpecific))) {
            // Filter specific date.
            //End date: text field
            String strStartDate = etSpecificDate.getText().toString();

            try
            {
                SimpleDateFormat sdf = new SimpleDateFormat(HMUtils.DATE_FORMAT);

                Date dtSpecificDate = sdf.parse(strStartDate);

                //check both date is not in the future.
                if (dtSpecificDate.compareTo(dtToday) < 0 || dtSpecificDate.compareTo(dtToday) == 0) {
                    BPReadingFilter readingFilter = new BPReadingFilter(BPFILTERTYPE.SPECIFIC);
                    readingFilter.setStartDate(dtSpecificDate);
                    sendResult(readingFilter);
                } else {
                    Toast.makeText(getApplicationContext(),"Invalid Specified Date selected",Toast.LENGTH_LONG);
                    Log.i(HMUtils.LOGTAG, "Invalid Date selected");
                }

            }catch(ParseException exp) {
                Toast.makeText(getApplicationContext(),"Invalid filter data",Toast.LENGTH_LONG);
                Log.e(HMUtils.LOGTAG,"Invalid filter data. " + exp.getMessage());
            }
        } else{
            Toast.makeText(getApplicationContext(),"Invalid filter Type",Toast.LENGTH_LONG);
            Log.i(HMUtils.LOGTAG, "Invalid Filter Type");
        }
    }

    private void sendResult(BPReadingFilter readingFilter) {
        Intent result = new Intent();

        // Pass relevant data back as a result
        result.putExtra("filter", readingFilter);

        // Activity finished ok, return the data
        setResult(RESULT_OK, result); // set result code and bundle data for response
        finish(); // closes the activity, pass data to parent
    }
}

package com.azare.app.healthmonitor;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.azare.app.healthmonitor.db.DAOApptRecord;
import com.azare.app.healthmonitor.model.Appointment;
import com.azare.app.healthmonitor.utils.HMUtils;

public class ListAppointments extends AppCompatActivity {

    public final int REQUEST_NEW_APPT_CODE = 30;
    public static final int REQUEST_EDIT_APPT_CODE = 40;

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    ApptPagerAdapter apptPagerAdapter;
    TabItem tabCompleted;
    TabItem tabUpcoming;

    DAOApptRecord daoApptRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_appointments);

        daoApptRecord = new DAOApptRecord(this);

        tabLayout = findViewById(R.id.tablayout);
        tabUpcoming = findViewById(R.id.tabUpcoming);
        tabCompleted = findViewById(R.id.tabCompleted);
        viewPager = findViewById(R.id.viewPager);


        setupViewPagerAdapter(viewPager);

        //viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setupViewPagerAdapter(ViewPager viewPager)
    {
        apptPagerAdapter = ApptPagerAdapter.initialize(getSupportFragmentManager());

        //order is very important here; they correspond to the tab position from left to right
        apptPagerAdapter.addFragment(ApptCompletedFrag.newInstance(), "Completed Appointments");
        apptPagerAdapter.addFragment(ApptUpcomingFrag.newInstance(), "Upcoming Appointments");

        viewPager.setAdapter(apptPagerAdapter);
        viewPager.setCurrentItem(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_appointment, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addAppointment:
                Intent addApptIntent = new Intent(getApplicationContext(), AddApptActivity.class);
                startActivityForResult(addApptIntent,REQUEST_NEW_APPT_CODE);

                return (true);
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        //result of create new appt
        if (resultCode == RESULT_OK && requestCode == REQUEST_NEW_APPT_CODE) {
            Appointment appointment = (Appointment) data.getSerializableExtra("appt");
            long id = data.getLongExtra("id", -1);

            //Add new Appointment
            if (daoApptRecord.insert(appointment)) {
                String successMsg = getResources().getString(R.string.successmsg);
                Log.i(HMUtils.LOGTAG, successMsg);

                Toast.makeText(getApplicationContext(), successMsg, Toast.LENGTH_SHORT).show();

                ApptUpcomingFrag frag = (ApptUpcomingFrag) apptPagerAdapter.getItem(1);

                frag.updateList();
            }
        }
    }
}

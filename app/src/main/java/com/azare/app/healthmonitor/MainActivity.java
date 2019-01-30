package com.azare.app.healthmonitor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.amitshekhar.DebugDB;
import com.azare.app.healthmonitor.model.HomeItem;
import com.azare.app.healthmonitor.utils.HMUtils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements HomeViewAdapter.ItemListener {

    ActionBar actionBar;
    RecyclerView recyclerView;
    ArrayList<HomeItem> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get ActionBar
        actionBar = getSupportActionBar();

        // Set below attributes to add logo in ActionBar.
        actionBar.setDisplayOptions( ActionBar.DISPLAY_SHOW_HOME
                | ActionBar.DISPLAY_SHOW_TITLE);

        actionBar.setTitle(getTitle());

        Log.i(HMUtils.LOGTAG, DebugDB.getAddressLog());

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        arrayList = new ArrayList<HomeItem>();
        arrayList.add(new HomeItem("Blood Pressure", R.drawable.bpreading, "#09A9FF"));
        arrayList.add(new HomeItem("Weight", R.drawable.weightrecord, "#3E51B1"));
        arrayList.add(new HomeItem("Appointment", R.drawable.appt, "#673BB7"));

        HomeViewAdapter adapter = new HomeViewAdapter(this, arrayList, this);
        recyclerView.setAdapter(adapter);


        /**
         AutoFitGridLayoutManager that auto fits the cells by the column width defined.
         **/

        /*AutoFitGridLayoutManager layoutManager = new AutoFitGridLayoutManager(this, 500);
        recyclerView.setLayoutManager(layoutManager);*/


        /**
         Simple GridLayoutManager that spans two columns
         **/
        GridLayoutManager manager = new GridLayoutManager(this, 2,
                GridLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(manager);


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.dummyBP:
                Intent dummyBPIntent = new Intent(MainActivity.this, DummyBPActivity.class);
                startActivity(dummyBPIntent);

                return (true);

            case R.id.dummyWeight:
                Intent dummyWeight = new Intent(MainActivity.this, DummyWeightActivity.class);
                startActivity(dummyWeight);

                return (true);

            case R.id.dummyAppointment:
                Intent dummyAppt = new Intent(MainActivity.this, DummyApptActivity.class);
                startActivity(dummyAppt);

                return (true);

            case R.id.dummyReminder:
                Intent reminder = new Intent(MainActivity.this, DummyReminder.class);
                startActivity(reminder);

                return (true);

        }
        return (super.onOptionsItemSelected(item));
    }

    @Override
    public void onItemClick(HomeItem item) {
        Toast.makeText(getApplicationContext(), item.text + " is clicked", Toast.LENGTH_SHORT).show();

        if (item.text.equals("Blood Pressure")) {
            Intent lvBPReadingIntent = new Intent(MainActivity.this, ListBPReading.class);
            startActivity(lvBPReadingIntent);
        }

        if (item.text.equals("Weight")) {
            Intent lvWeightsIntent = new Intent(MainActivity.this, ListWeightRecord.class);
            startActivity(lvWeightsIntent);
        }

        if (item.text.equals("Appointment")) {
            Intent lvApptIntent = new Intent(MainActivity.this, ListAppointments.class);
            startActivity(lvApptIntent);
        }
    }
}
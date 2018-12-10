package com.azare.app.healthmonitor.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.azare.app.healthmonitor.db.HealthDBHelper;

import java.sql.Date;
import java.util.ArrayList;

public class BPDataSource {

    // Database fields
    private HealthDBHelper m_HealthDBHelper;

    private String[] allBPColumns = {
            HealthDBHelper.COL_ID,
            HealthDBHelper.COL_SYSTOLIC,
            HealthDBHelper.COL_DIASTOLIC,
            HealthDBHelper.COL_READ_TYPE,
            HealthDBHelper.COL_DATETIME
    };

    public BPDataSource(Context context) {
        m_HealthDBHelper = HealthDBHelper.getInstance(context);
    }

    public boolean addReading() {

        SQLiteDatabase database = m_HealthDBHelper.getWritableDatabase();

        return true;
    }

    public ArrayList<BPReading> listAll() {
        return new ArrayList<BPReading>();
    }

    public ArrayList<BPReading> currentMonth() {

        //TODO: get month from the current date.
        return new ArrayList<BPReading>();
    }

    public ArrayList<BPReading> currentWeek() {

        //TODO: get week number from the current date.
        return new ArrayList<BPReading>();
    }

    public ArrayList<BPReading> listCustom(Date start, Date end) {
        return new ArrayList<BPReading>();
    }
}

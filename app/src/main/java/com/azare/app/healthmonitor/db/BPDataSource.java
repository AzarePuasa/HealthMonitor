package com.azare.app.healthmonitor.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


import com.azare.app.healthmonitor.model.BPReading;

import java.sql.Date;
import java.util.ArrayList;

public class BPDataSource {

    // Database fields
    private HealthDBHelper m_HealthDBHelper;

    private String[] allBPColumns = {
            HealthDBHelper.COL_ID,
            HealthDBHelper.COL_DAY,
            HealthDBHelper.COL_MONTH,
            HealthDBHelper.COL_YEAR,
            HealthDBHelper.COL_READ_TYPE,
            HealthDBHelper.COL_SYSTOLIC,
            HealthDBHelper.COL_DIASTOLIC,
            HealthDBHelper.COL_TIMESTAMP
    };

    public BPDataSource(Context context) {
        m_HealthDBHelper = HealthDBHelper.getInstance(context);
    }

    public boolean addReading() {

        /*TODO: get month from the current date.*/

        SQLiteDatabase database = m_HealthDBHelper.getWritableDatabase();

        return true;
    }

    public ArrayList<BPReading> listAll() {
        return new ArrayList<BPReading>();
    }

    public ArrayList<BPReading> currentMonth(Date dtStart, Date dtEnd) {

        /*TODO: get month from the current date.
        Extract the month. Query the DB for
        readings that is within this month.
         */
        return new ArrayList<BPReading>();
    }

    public ArrayList<BPReading> currentWeek(Date dtStart, Date dtEnd) {

        /*TODO: get week number from the current date.

         */
        return new ArrayList<BPReading>();
    }

    public ArrayList<BPReading> listCustom(Date dtStart, Date dtEnd) {
        return new ArrayList<BPReading>();
    }

    public BPReading getReading(Date dtDate) {
        return new BPReading();
    }
}

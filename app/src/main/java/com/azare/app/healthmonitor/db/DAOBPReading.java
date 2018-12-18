package com.azare.app.healthmonitor.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


import com.azare.app.healthmonitor.model.BPREADTYPE;
import com.azare.app.healthmonitor.model.BPReading;

import java.sql.Date;
import java.util.ArrayList;

public class DAOBPReading {

    private SQLiteDatabase database;

    // Database fields
    private HMDBHelper m_HMDBHelper;

    public DAOBPReading(Context context) {
        m_HMDBHelper = HMDBHelper.getInstance(context);
        database = m_HMDBHelper.getWritableDatabase();
    }

    /*
    Insert a new record into the table.
     */
    public boolean insert(BPReading bpReading) {

        /*TODO: get month from the current date.*/
        // Create a new map of values, where column names are the keys
        ContentValues cv = new ContentValues();
        cv.put(HMDBtables.BPReadingTbl.COL_DAY, bpReading.getDay());
        cv.put(HMDBtables.BPReadingTbl.COL_MONTH, bpReading.getMonth());
        cv.put(HMDBtables.BPReadingTbl.COL_YEAR, bpReading.getYear());
        cv.put(HMDBtables.BPReadingTbl.COL_SYSTOLIC, bpReading.getSystolic());
        cv.put(HMDBtables.BPReadingTbl.COL_DIASTOLIC, bpReading.getDiastolic());
        cv.put(HMDBtables.BPReadingTbl.COL_READ_TYPE, bpReading.getReadType().name());
        cv.put(HMDBtables.BPReadingTbl.COL_TIMESTAMP, bpReading.getTimestamp());

        long insertId = database.insert(HMDBtables.BPReadingTbl.BP_READING_TABLE, null, cv);

        boolean isInserted = insertId == -1;

        return isInserted;
    }

    public int delete(BPREADTYPE type) {
        return database.delete(HMDBtables.BPReadingTbl.BP_READING_TABLE,
                null, null);
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

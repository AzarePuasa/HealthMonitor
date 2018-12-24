package com.azare.app.healthmonitor.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.azare.app.healthmonitor.MainActivity;
import com.azare.app.healthmonitor.model.AfternoonBPReading;
import com.azare.app.healthmonitor.model.BPREADTYPE;
import com.azare.app.healthmonitor.model.BPReading;
import com.azare.app.healthmonitor.model.DailyBPReadings;
import com.azare.app.healthmonitor.model.EveningBPReading;
import com.azare.app.healthmonitor.model.MorningBPReading;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class DAOBPReading {

    private SQLiteDatabase db_writer;
    private SQLiteDatabase db_reader;

    // Database fields
    private HMDBHelper m_HMDBHelper;

    public DAOBPReading(Context context) {
        m_HMDBHelper = HMDBHelper.getInstance(context);
        db_writer = m_HMDBHelper.getWritableDatabase();
        db_reader = m_HMDBHelper.getReadableDatabase();
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

        long insertId = db_writer.insert(HMDBtables.BPReadingTbl.BP_READING_TABLE, null, cv);

        boolean isInserted = insertId == -1;

        return isInserted;
    }

    public int delete(BPREADTYPE type) {
        return db_writer.delete(HMDBtables.BPReadingTbl.BP_READING_TABLE,
                null, null);
    }

    /*
    Get all Blood Pressure reading.
     */
    public DailyBPReadings listAll() {

        DailyBPReadings allDailyBPReadings = new DailyBPReadings();

        Cursor cursor = db_reader.query(HMDBtables.BPReadingTbl.BP_READING_TABLE,
                HMDBtables.BPReadingTbl.ALL_COLUMNS, null,
                null, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            long id = cursor.getLong(0);
            int iDay = cursor.getInt(1);
            int iMonth = cursor.getInt(2);
            int iYear = cursor.getInt(3);
            BPREADTYPE type = BPREADTYPE.valueOf(cursor.getString(4));
            int iSystolic = cursor.getInt(5);
            int iDiastolic = cursor.getInt(6);
            long timestamp = cursor.getLong(7);

            String strDate = iDay
                    + MainActivity.DATE_SEPERATOR + iMonth
                    + MainActivity.DATE_SEPERATOR + iYear;

            try {
                if (type == BPREADTYPE.MORNING) {
                    MorningBPReading morningBPReading = new MorningBPReading(iDay,iMonth,iYear,iSystolic,iDiastolic);
                    morningBPReading.setTimestamp(timestamp);
                    allDailyBPReadings.addMorningReading(strDate, morningBPReading);
                }
                else if (type == BPREADTYPE.AFTERNOON) {
                    AfternoonBPReading afternoonBPReading = new AfternoonBPReading(
                            iDay,iMonth,iYear,iSystolic,iDiastolic);
                    afternoonBPReading.setTimestamp(timestamp);
                    allDailyBPReadings.addAfternoonReading(strDate, afternoonBPReading );
                }
                else if (type == BPREADTYPE.EVENING) {
                    EveningBPReading eveningBPReading = new EveningBPReading(
                            iDay,iMonth,iYear,iSystolic,iDiastolic);
                    eveningBPReading.setTimestamp(timestamp);
                    allDailyBPReadings.addEveningReading(strDate, eveningBPReading );
                }
            } catch (Exception exp) {
                Log.e("Health Monitor", "Fail to retrieve BP reading. " + exp.getMessage());
            }

            cursor.moveToNext();
        }

        cursor.close();

        return allDailyBPReadings;
    }

    /**
     * Get BP Readings of the last 30 Days.
     * @return
     */
    public DailyBPReadings listPrevious30Days() {

        DailyBPReadings dailyBPReadings = new DailyBPReadings();

        /*TODO: get month from the current date.
        Extract the month. Query the DB for
        readings that is within this month.
         */
        return dailyBPReadings;
    }

    /**
     * Get BP Readings of the last 7 Days
     * @return
     */
    public DailyBPReadings listPrevious7Days() {

        DailyBPReadings dailyBPReadings = new DailyBPReadings();

        /*TODO: get previous 7 days of BP Reading.

         */
        return dailyBPReadings;
    }

    /**
     * Get BP readings for the specified period.
     * @param dtStart
     * @param dtEnd
     * @return
     */
    public DailyBPReadings listCustom(Date dtStart, Date dtEnd) {

        DailyBPReadings customDailyBPReadings = new DailyBPReadings();

        //start and end date must be less than current date.

        return customDailyBPReadings;
    }

    /**
     * Get BP Readings for the specified day.
     * @param dtDate
     * @return
     */
    public List<BPReading> getDayReading(Date dtDate) {

        //date must be less than current date.

        //convert date to text.

        //query table where day, month and year is equals to parameter.

        return new ArrayList<BPReading>();
    }

    /**
     * Get BP reading for a specified period.
     * @param dtDate
     * @param type
     * @return
     */
    public BPReading getReading(Date dtDate, BPREADTYPE type) {

        //date must be less than current date.

        return new BPReading();
    }
}

package com.azare.app.healthmonitor.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.azare.app.healthmonitor.model.Appointment;
import com.azare.app.healthmonitor.utils.HMUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DAOApptRecord extends DAOHealthMonitor {

    public DAOApptRecord(Context context) {
        super(context);
    }

    public Appointment getApptById(long appt_id) {

        String condition = HMDBtables.ApptRecordTbl.COL_APPT_ID + "= ?";
        String[] args = new String[]{Long.toString(appt_id)};

        //get all appointment records.
        Cursor cursor = db_reader.query(HMDBtables.ApptRecordTbl.APPT_RECORD_TABLE,
                HMDBtables.ApptRecordTbl.ALL_COLUMNS, condition,
                args, null, null, null);

        if ( cursor.getCount() == 1 ) {
            cursor.moveToFirst();

            long id = cursor.getLong(0);
            int iDay = cursor.getInt(1);
            int iMonth = cursor.getInt(2);
            int iYear = cursor.getInt(3);
            String strHour = cursor.getString(4);
            String strMinutes = cursor.getString(5);
            String strLocation = cursor.getString(6);
            String strPurpose = cursor.getString(7);
            long timestamp = cursor.getLong(8);



            Appointment appointment = new Appointment(iDay, iMonth, iYear, strHour, strMinutes);

            appointment.setId(id);
            appointment.setLocation(strLocation);
            appointment.setPurpose(strPurpose);
            appointment.setTimestamp(timestamp);

            return appointment;
        }

        return null;
    }

    public List<Appointment> listAll() {

        List<Appointment> lAppointments = new ArrayList<Appointment>();

        //get all appointment records.
        Cursor cursor = db_reader.query(HMDBtables.ApptRecordTbl.APPT_RECORD_TABLE,
                HMDBtables.ApptRecordTbl.ALL_COLUMNS, null,
                null, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            long id = cursor.getLong(0);
            int iDay = cursor.getInt(1);
            int iMonth = cursor.getInt(2);
            int iYear = cursor.getInt(3);
            String strHour = cursor.getString(4);
            String strMinutes = cursor.getString(5);
            String strLocation = cursor.getString(6);
            String strPurpose = cursor.getString(7);
            long timestamp = cursor.getLong(8);

            Appointment appointment = new Appointment(iDay, iMonth,iYear, strHour,strMinutes);

            appointment.setId(id);
            appointment.setLocation(strLocation);
            appointment.setPurpose(strPurpose);
            appointment.setTimestamp(timestamp);

            lAppointments.add(appointment);

            cursor.moveToNext();
        }

        cursor.close();

        return lAppointments;
    }

    public List<Appointment> listUpcomingAppt() {

        List<Appointment> lRawAppts = listAll();
        List<Appointment> lApptResult = new ArrayList<Appointment>();

        //get today's Date/time.
        Date dtCurrent = HMUtils.getDateToday();

        for ( Appointment appointment : lRawAppts) {

            //get each records date time.
            //compare each record with today's date
            String strAppt = appointment.getApptDateTimeString();

            Date dtAppt = HMUtils.strToDate(strAppt, new SimpleDateFormat(HMUtils.DATE_TIME_FORMAT_NO_SEC));

            //get upcoming Appointments
            if (dtAppt.after(dtCurrent)) {
                lApptResult.add(appointment);
            }
        }

        return lApptResult;
    }

    public List<Appointment> listCompletedAppt() {

        List<Appointment> lRawAppts = listAll();
        List<Appointment> lApptResult = new ArrayList<Appointment>();

        //get today's Date/time.
        Date dtCurrent = HMUtils.getDateToday();

        for ( Appointment appointment : lRawAppts) {

            //get each records date time.
            //compare each record with today's date
            String strAppt = appointment.getApptDateTimeString();

            Date dtAppt = HMUtils.strToDate(strAppt, new SimpleDateFormat(HMUtils.DATE_TIME_FORMAT_NO_SEC));

            //add records that have date/time less than today.
            if (dtAppt.before(dtCurrent)) {
                lApptResult.add(appointment);
            }
        }

        return lApptResult;
    }

    public boolean insert(Appointment appointment) {
        Log.i(HMUtils.LOGTAG, appointment.toString());

        ContentValues cv = new ContentValues();

        cv.put(HMDBtables.ApptRecordTbl.COL_DAY, appointment.getDay());
        cv.put(HMDBtables.ApptRecordTbl.COL_MONTH, appointment.getMonth());
        cv.put(HMDBtables.ApptRecordTbl.COL_YEAR, appointment.getYear());
        cv.put(HMDBtables.ApptRecordTbl.COL_HOUR, appointment.getHour());
        cv.put(HMDBtables.ApptRecordTbl.COL_MINUTE, appointment.getMinutes());
        cv.put(HMDBtables.ApptRecordTbl.COL_PURPOSE, appointment.getPurpose());
        cv.put(HMDBtables.ApptRecordTbl.COL_LOCATION, appointment.getLocation());
        cv.put(HMDBtables.ApptRecordTbl.COL_TIMESTAMP, appointment.getTimestamp());

        long insertId = db_writer.insert(HMDBtables.ApptRecordTbl.APPT_RECORD_TABLE, null, cv);

        boolean isInserted = insertId > -1? true : false;

        return isInserted;
    }

    public int delete() {
        return db_writer.delete(HMDBtables.ApptRecordTbl.APPT_RECORD_TABLE,
                null, null);
    }

    public boolean update(long id, Appointment appointment) {

        ContentValues cv = new ContentValues();

        cv.put(HMDBtables.ApptRecordTbl.COL_DAY, appointment.getDay());
        cv.put(HMDBtables.ApptRecordTbl.COL_MONTH, appointment.getMonth());
        cv.put(HMDBtables.ApptRecordTbl.COL_YEAR, appointment.getYear());
        cv.put(HMDBtables.ApptRecordTbl.COL_HOUR, appointment.getHour());
        cv.put(HMDBtables.ApptRecordTbl.COL_MINUTE, appointment.getMinutes());
        cv.put(HMDBtables.ApptRecordTbl.COL_PURPOSE, appointment.getPurpose());
        cv.put(HMDBtables.ApptRecordTbl.COL_LOCATION, appointment.getLocation());
        cv.put(HMDBtables.ApptRecordTbl.COL_TIMESTAMP, appointment.getTimestamp());

        String whereClause = HMDBtables.ApptRecordTbl.COL_APPT_ID + "=?";
        String[] args = new String[]{Long.toString(id)};

        int update = db_writer.update(HMDBtables.ApptRecordTbl.APPT_RECORD_TABLE, cv, whereClause, args);

        return update == 1;
    }

    public boolean delete(long id) {
        String whereClause = HMDBtables.ApptRecordTbl.COL_APPT_ID + "=?";
        String[] args = new String[]{Long.toString(id)};

        int delete = db_writer.delete(HMDBtables.ApptRecordTbl.APPT_RECORD_TABLE, whereClause, args);

        return delete == 1;
    }
}

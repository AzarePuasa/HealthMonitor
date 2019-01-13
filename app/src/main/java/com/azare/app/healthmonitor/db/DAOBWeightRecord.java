package com.azare.app.healthmonitor.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.azare.app.healthmonitor.model.WeightRecord;

import java.util.ArrayList;
import java.util.List;

public class DAOBWeightRecord extends DAOHealthMonitor {

    public DAOBWeightRecord(Context context) {
        super(context);
    }

    public boolean insert(WeightRecord weightRecord) {
        ContentValues cv = new ContentValues();

        cv.put(HMDBtables.WeightRecordTbl.COL_DAY, weightRecord.getDay());
        cv.put(HMDBtables.WeightRecordTbl.COL_MONTH, weightRecord.getMonth());
        cv.put(HMDBtables.WeightRecordTbl.COL_YEAR, weightRecord.getYear());
        cv.put(HMDBtables.WeightRecordTbl.COL_WEIGHT, weightRecord.getWeight());
        cv.put(HMDBtables.WeightRecordTbl.COL_TIMESTAMP, weightRecord.getTimestamp());

        long insertId = db_writer.insert(HMDBtables.WeightRecordTbl.WEIGHT_RECORD_TABLE, null, cv);

        boolean isInserted = insertId > -1? true : false;

        return isInserted;
    }

    public int delete() {
        return db_writer.delete(HMDBtables.WeightRecordTbl.WEIGHT_RECORD_TABLE,
                null, null);
    }

    public List<WeightRecord> listAll() {

        List<WeightRecord> weightRecordlist = new ArrayList<WeightRecord>();

        Cursor cursor = db_reader.query(HMDBtables.WeightRecordTbl.WEIGHT_RECORD_TABLE,
                HMDBtables.WeightRecordTbl.ALL_COLUMNS, null,
                null, null, null, HMDBtables.WeightRecordTbl.COL_TIMESTAMP + " ASC");

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            long id = cursor.getLong(0);
            int iDay = cursor.getInt(1);
            int iMonth = cursor.getInt(2);
            int iYear = cursor.getInt(3);
            double iWeight = cursor.getDouble(4);
            long timestamp = cursor.getLong(5);

            weightRecordlist.add(new WeightRecord(iDay,iMonth,iYear,iWeight,timestamp));

            cursor.moveToNext();
        }

        cursor.close();

        return weightRecordlist;
    }

}

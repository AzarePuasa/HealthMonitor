package com.azare.app.healthmonitor.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HealthDBHelper extends SQLiteOpenHelper {

    private static HealthDBHelper sInstance = null;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "HealthMon.db";
    public static final String TABLE_BLOOD_PRESSURE = "bpreadings";

    // Columns names for Blood Pressure
    public static final String COL_ID = "_id";
    public static final String COL_DAY = "day";
    public static final String COL_MONTH = "month";
    public static final String COL_YEAR = "year";
    public static final String COL_READ_TYPE = "readtype";
    public static final String COL_SYSTOLIC = "systolic";
    public static final String COL_DIASTOLIC = "diastolic";
    public static final String COL_TIMESTAMP = "timestamp";

    private HealthDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql_create_bp_table = "CREATE TABLE "
                + TABLE_BLOOD_PRESSURE
                + "("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_DAY + " INTEGER NOT NULL, "
                + COL_MONTH + " INTEGER NOT NULL, "
                + COL_YEAR + " INTEGER NOT NULL, "
                + COL_READ_TYPE + " TEXT NOT NULL, "
                + COL_SYSTOLIC + " INTEGER NOT NULL, "
                + COL_DIASTOLIC + " INTEGER NOT NULL, "
                + COL_TIMESTAMP + " DATETIME DEFAULT (datetime('now','localtime'))"
                + ")";

        db.execSQL(sql_create_bp_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {

            db.execSQL("DROP TABLE IF EXISTS " + TABLE_BLOOD_PRESSURE);
            onCreate(db);
        }
    }

    public static synchronized HealthDBHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new HealthDBHelper(context);
        }
        return sInstance;
    }
}

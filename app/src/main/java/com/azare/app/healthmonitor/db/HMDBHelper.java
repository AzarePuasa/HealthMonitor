package com.azare.app.healthmonitor.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HMDBHelper extends SQLiteOpenHelper {

    private static HMDBHelper sInstance = null;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "HealthMon.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COL_NOT_NULL = " NOT NULL";


    private static final String CREATE_BP_READING_TABLE = "CREATE TABLE "
            + HMDBtables.BPReadingTbl.BP_READING_TABLE
            + "("
            + HMDBtables.BPReadingTbl.COL_BP_ID + INTEGER_TYPE + " PRIMARY KEY AUTOINCREMENT, "
            + HMDBtables.BPReadingTbl.COL_DAY + INTEGER_TYPE + COL_NOT_NULL + ", "
            + HMDBtables.BPReadingTbl.COL_MONTH + INTEGER_TYPE + COL_NOT_NULL+ ", "
            + HMDBtables.BPReadingTbl.COL_YEAR + INTEGER_TYPE + COL_NOT_NULL+ ", "
            + HMDBtables.BPReadingTbl.COL_READ_TYPE + TEXT_TYPE + COL_NOT_NULL+ ", "
            + HMDBtables.BPReadingTbl.COL_SYSTOLIC + INTEGER_TYPE + COL_NOT_NULL+ ", "
            + HMDBtables.BPReadingTbl.COL_DIASTOLIC + INTEGER_TYPE + COL_NOT_NULL+ ", "
            + HMDBtables.BPReadingTbl.COL_TIMESTAMP + INTEGER_TYPE + COL_NOT_NULL + ")";

    private static final String DELETE_TEXTREPORT_TABLE = "DROP TABLE IF EXISTS "
            + HMDBtables.BPReadingTbl.BP_READING_TABLE;

    private HMDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BP_READING_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {

            db.execSQL(DELETE_TEXTREPORT_TABLE);
            onCreate(db);
        }
    }

    public static synchronized HMDBHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new HMDBHelper(context);
        }
        return sInstance;
    }
}

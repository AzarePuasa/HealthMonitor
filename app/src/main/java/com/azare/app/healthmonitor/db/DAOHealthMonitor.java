package com.azare.app.healthmonitor.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DAOHealthMonitor {

    protected SQLiteDatabase db_writer;
    protected SQLiteDatabase db_reader;

    // Database fields
    private HMDBHelper m_HMDBHelper;

    public DAOHealthMonitor(Context context) {
        m_HMDBHelper = HMDBHelper.getInstance(context);
        db_writer = m_HMDBHelper.getWritableDatabase();
        db_reader = m_HMDBHelper.getReadableDatabase();
    }


}

package com.azare.app.healthmonitor.db;

public class HMDBtables {

    public static abstract class BPReadingTbl {
        public static final String BP_READING_TABLE = "tbl_bpreading";

        // Columns names for Blood Pressure
        public static final String COL_BP_ID = "_id";
        public static final String COL_DAY = "day";
        public static final String COL_MONTH = "month";
        public static final String COL_YEAR = "year";
        public static final String COL_READ_TYPE = "readtype";
        public static final String COL_SYSTOLIC = "systolic";
        public static final String COL_DIASTOLIC = "diastolic";
        public static final String COL_TIMESTAMP = "timestamp";

//        public static final String PRIMARY_KEY = "PRIMARY KEY (" + COL_BP_ID + "," + COL_DAY +
//                "," + COL_MONTH + "," + COL_YEAR + "," + COL_READ_TYPE + ")";

        public static final String[] ALL_COLUMNS =
                {COL_BP_ID,COL_DAY,COL_MONTH,COL_YEAR,COL_READ_TYPE,COL_SYSTOLIC,COL_DIASTOLIC,COL_TIMESTAMP};
    }
}
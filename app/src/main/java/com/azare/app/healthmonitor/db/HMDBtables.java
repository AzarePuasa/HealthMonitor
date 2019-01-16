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

    public static abstract class WeightRecordTbl {
        public static final String WEIGHT_RECORD_TABLE = "tbl_weight";

        // Columns names for Weight
        public static final String COL_WEIGHT_ID = "_id";
        public static final String COL_DAY = "day";
        public static final String COL_MONTH = "month";
        public static final String COL_YEAR = "year";
        public static final String COL_WEIGHT = "weight";
        public static final String COL_TIMESTAMP = "timestamp";

        public static final String[] ALL_COLUMNS =
                {COL_WEIGHT_ID,COL_DAY,COL_MONTH,COL_YEAR,COL_WEIGHT,COL_TIMESTAMP};
    }

    public static abstract class ApptRecordTbl {
        public static final String APPT_RECORD_TABLE = "tbl_appointment";

        // Columns names for Appointment
        public static final String COL_APPT_ID = "_id";
        public static final String COL_DAY = "day";
        public static final String COL_MONTH = "month";
        public static final String COL_YEAR = "year";
        public static final String COL_HOUR = "hour";
        public static final String COL_MINUTE = "minute";
        public static final String COL_LOCATION = "location";
        public static final String COL_PURPOSE = "purpose";
        public static final String COL_TIMESTAMP = "timestamp";

        public static final String[] ALL_COLUMNS =
                {COL_APPT_ID,COL_DAY,COL_MONTH,COL_YEAR,COL_HOUR,COL_MONTH,
                        COL_LOCATION,COL_PURPOSE,COL_TIMESTAMP};

    }
}

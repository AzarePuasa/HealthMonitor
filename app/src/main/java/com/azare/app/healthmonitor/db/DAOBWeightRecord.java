package com.azare.app.healthmonitor.db;

import android.content.Context;

import com.azare.app.healthmonitor.model.WeightRecord;

import java.util.ArrayList;
import java.util.List;

public class DAOBWeightRecord extends DAOHealthMonitor {

    public DAOBWeightRecord(Context context) {
        super(context);
    }

    public List<WeightRecord> listAll() {

        return new ArrayList<WeightRecord>();
    }
}

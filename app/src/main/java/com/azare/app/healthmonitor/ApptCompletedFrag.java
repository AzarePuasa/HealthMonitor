package com.azare.app.healthmonitor;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.azare.app.healthmonitor.db.DAOApptRecord;
import com.azare.app.healthmonitor.model.Appointment;
import com.azare.app.healthmonitor.utils.HMUtils;

import java.util.List;


public class ApptCompletedFrag extends Fragment {

    DAOApptRecord apptRecord;

    RecyclerView recyclerView;

    ApptAdapter apptAdapter;

    List<Appointment> lCompletedAppts;


    public ApptCompletedFrag() {
        // Required empty public constructor
        apptRecord = new DAOApptRecord(getContext());

        lCompletedAppts = apptRecord.listCompletedAppt();
    }

    public static ApptCompletedFrag newInstance()
    {
        return new ApptCompletedFrag();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView = (RecyclerView) getView().findViewById(R.id.allApptCompleted);

        Log.i(HMUtils.LOGTAG, "Number of completed items:" + lCompletedAppts.size());

        apptAdapter = new ApptAdapter(lCompletedAppts, false);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setAdapter(apptAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_appt_completed,
                container, false);
        return view;
    }
}

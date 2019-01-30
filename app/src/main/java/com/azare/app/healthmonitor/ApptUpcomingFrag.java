package com.azare.app.healthmonitor;

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

public class ApptUpcomingFrag extends Fragment {

    DAOApptRecord apptRecord;
    RecyclerView recyclerView;
    ApptAdapter apptAdapter;
    List<Appointment> lUpcomingAppts;

    public ApptUpcomingFrag() {
        // Required empty public constructor
    }

    public static ApptUpcomingFrag newInstance()
    {
        return new ApptUpcomingFrag();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e(HMUtils.LOGTAG, "OnCreate Start");

        apptRecord = new DAOApptRecord(getContext());

        //query table for readings.
        lUpcomingAppts = apptRecord.listUpcomingAppt();

        Log.i(HMUtils.LOGTAG, "Number of items:" + lUpcomingAppts.size());

        //update list in adapter.
        apptAdapter = new ApptAdapter(lUpcomingAppts, true);

        Log.e(HMUtils.LOGTAG, "OnCreate End");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.e(HMUtils.LOGTAG,"onActivityCreated Start");

        recyclerView.setAdapter(apptAdapter);

        Log.e(HMUtils.LOGTAG,"onActivityCreated End");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.e(HMUtils.LOGTAG,"onCreateView Start");

        View view = inflater.inflate(R.layout.fragment_appt_upcoming,
                container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.allApptUpcoming);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateList();

        Log.e(HMUtils.LOGTAG,"onCreateView End");
        return view;
    }

    @Override
    public void onResume()
    {
        Log.e(HMUtils.LOGTAG,"onResume Start");

        super.onResume();
        updateList();

        Log.e(HMUtils.LOGTAG,"onResume End");
    }

    public void updateList()
    {
        Log.e(HMUtils.LOGTAG,"updateList Start");
        //query table for readings.
        lUpcomingAppts = apptRecord.listUpcomingAppt();

        apptAdapter = new ApptAdapter(lUpcomingAppts,true);
        recyclerView.setAdapter(apptAdapter);

        Log.e(HMUtils.LOGTAG,"updateList End");
    }
}

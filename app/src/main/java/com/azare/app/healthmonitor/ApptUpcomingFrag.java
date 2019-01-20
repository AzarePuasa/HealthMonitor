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
import android.widget.TextView;

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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        apptRecord = new DAOApptRecord(getContext());

        recyclerView = (RecyclerView) getView().findViewById(R.id.allApptUpcoming);

        lUpcomingAppts = apptRecord.listUpcomingAppt();

        Log.i(HMUtils.LOGTAG, "Number of items:" + lUpcomingAppts.size());

        apptAdapter = new ApptAdapter(lUpcomingAppts);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setAdapter(apptAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_appt_upcoming,
                container, false);
        return view;
    }

}

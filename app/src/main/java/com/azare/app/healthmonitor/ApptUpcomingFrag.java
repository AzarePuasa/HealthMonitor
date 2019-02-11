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

public class ApptUpcomingFrag extends Fragment {

    DAOApptRecord apptRecord;
    RecyclerView recyclerView;
    ApptAdapter apptAdapter;
    List<Appointment> lUpcomingAppts;

    public ApptUpcomingFrag() {
        // Required empty public constructor
        Log.i(HMUtils.LOGTAG, "ApptUpcomingFrag default constructor");
        apptRecord = new DAOApptRecord(getContext());

        //query table for readings.
        lUpcomingAppts = apptRecord.listUpcomingAppt();
    }

    public static ApptUpcomingFrag newInstance()
    {
        Log.i(HMUtils.LOGTAG, "ApptUpcomingFrag newInstance");
        return new ApptUpcomingFrag();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(HMUtils.LOGTAG, "ApptUpcomingFrag OnCreate");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.i(HMUtils.LOGTAG,"ApptUpcomingFrag onActivityCreated");

        Log.i(HMUtils.LOGTAG, "Number of Upcoming items:" + lUpcomingAppts.size());

        //update list in adapter.
        apptAdapter = new ApptAdapter(lUpcomingAppts, true);

        recyclerView.setAdapter(apptAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.i(HMUtils.LOGTAG,"ApptUpcomingFrag onCreateView");

        View view = inflater.inflate(R.layout.fragment_appt_upcoming,
                container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.allApptUpcoming);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    @Override
    public void onResume()
    {
        Log.i(HMUtils.LOGTAG,"ApptUpcomingFrag onResume");

        super.onResume();
        updateList();
    }

    public void updateList()
    {
        Log.i(HMUtils.LOGTAG,"ApptUpcomingFrag updateList");
        //query table for readings.
        lUpcomingAppts = apptRecord.listUpcomingAppt();

        apptAdapter = new ApptAdapter(lUpcomingAppts,true);
        recyclerView.setAdapter(apptAdapter);

        apptAdapter.notifyDataSetChanged();
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.i(HMUtils.LOGTAG,"ApptUpcomingFrag onSaveInstanceState");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(HMUtils.LOGTAG,"ApptUpcomingFrag onPause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(HMUtils.LOGTAG,"ApptUpcomingFrag onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(HMUtils.LOGTAG,"ApptUpcomingFrag onDetach");
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(HMUtils.LOGTAG,"ApptUpcomingFrag onAttach");
    }


}

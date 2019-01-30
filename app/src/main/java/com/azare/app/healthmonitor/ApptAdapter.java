package com.azare.app.healthmonitor;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.azare.app.healthmonitor.db.DAOApptRecord;
import com.azare.app.healthmonitor.model.Appointment;

import java.util.List;

public class ApptAdapter extends RecyclerView.Adapter<ApptAdapter.ApptViewHolder> {

    private Context context;
    private List<Appointment> lAppointments;
    private boolean m_bEditable;

    public ApptAdapter(List<Appointment> lAppointments, boolean bEditable) {
        this.lAppointments = lAppointments;
        this.m_bEditable = bEditable;
    }

    public void clear() {
        this.lAppointments.clear();
    }

    public void setAppointments(List<Appointment> lAppointments) {
        this.lAppointments = lAppointments;
    }

    @NonNull
    @Override
    public ApptAdapter.ApptViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //inflate the layout file
        View appointmentView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.appointment_card, viewGroup, false);

        ApptViewHolder viewHolder = new ApptViewHolder(appointmentView);

        context = viewGroup.getContext();

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ApptAdapter.ApptViewHolder holder, final int position) {
        final Appointment appointment = lAppointments.get(position);

        holder.tvDate.setText(appointment.getDay() +"/" + appointment.getMonth()
                + "/" + appointment.getYear());

        holder.tvTime.setText(appointment.getHour() + ":" + appointment.getMinutes());
        holder.tvLocation.setText(appointment.getLocation());

        if (!m_bEditable) {
            holder.btn_edit.setVisibility(View.GONE);
            holder.btn_delete.setVisibility(View.GONE);
        }

        holder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Button Edit for pos " + position
                        + " Clicked", Toast.LENGTH_SHORT).show();

                Intent intentEdit = new Intent(context,AddApptActivity.class);
                intentEdit.putExtra("apptId", appointment.getId());
                ((ListAppointments)context).startActivityForResult(intentEdit,
                        ListAppointments.REQUEST_EDIT_APPT_CODE);
            }
        });

        holder.btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Button view for pos " + position
                        + " Clicked", Toast.LENGTH_SHORT).show();

                Intent intentView = new Intent(context,ViewApptActivity.class);
                intentView.putExtra("apptId", appointment.getId());
                context.startActivity(intentView);
            }
        });

        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Button delete for pos " + position
                        + " Clicked", Toast.LENGTH_SHORT).show();

                //display confirmation dialog.
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Confirm Delete Appointment");
                builder.setMessage("You are about to delete the record. Do you really want to proceed?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "You've choosen to delete the Appointment",
                                Toast.LENGTH_SHORT).show();
                        DAOApptRecord daoApptRecord = new DAOApptRecord(context);

                        daoApptRecord.delete(appointment.getId());

                        ApptPagerAdapter apptPagerAdapter = ApptPagerAdapter.getInstance();

                        ApptUpcomingFrag frag = (ApptUpcomingFrag) apptPagerAdapter.getItem(1);

                        frag.updateList();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "You've changed your mind to delete the Appointment",
                                Toast.LENGTH_SHORT).show();
                    }
                });

                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return lAppointments.size();
    }

    class ApptViewHolder extends RecyclerView.ViewHolder  {

        TextView tvDate;
        TextView tvTime;
        TextView tvLocation;
        ImageButton btn_edit;
        ImageButton btn_view;
        ImageButton btn_delete;

        public ApptViewHolder(@NonNull View view) {
            super(view);

            tvDate = view.findViewById(R.id.apptDate);
            tvTime = view.findViewById(R.id.apptTime);
            tvLocation = view.findViewById(R.id.apptLoc);

            btn_edit = view.findViewById(R.id.ibEdit);
            btn_view = view.findViewById(R.id.ibView);
            btn_delete = view.findViewById(R.id.ibDelete);
        }
    }
}

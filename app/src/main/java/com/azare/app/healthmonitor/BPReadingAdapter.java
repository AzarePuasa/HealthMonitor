package com.azare.app.healthmonitor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.azare.app.healthmonitor.model.DailyBPReadings;

public class BPReadingAdapter extends RecyclerView.Adapter<BPReadingAdapter.BPReadingViewHolder> {

    private DailyBPReadings dailyBPReadings;
    Context context;

    public BPReadingAdapter(DailyBPReadings dailyBPReadings) {
        this.dailyBPReadings = dailyBPReadings;
    }

    public void clear() {
        dailyBPReadings.clear();
    }

    public void setDailyBPReadings(DailyBPReadings dailyBPReadings) {
        this.dailyBPReadings = dailyBPReadings;
    }

    @Override
    public BPReadingAdapter.BPReadingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        //inflate the layout file
        View BPReadingView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bpreading_card, viewGroup, false);
        BPReadingViewHolder viewHolder = new BPReadingViewHolder(BPReadingView);
        context = viewGroup.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BPReadingViewHolder holder, int position) {

        DailyBPReading dailyBPReading = dailyBPReadings.getDailyReadings().get(position);

        String strDate = dailyBPReading.getDate();

        holder.tvReadingDate.setText(strDate);

        if (dailyBPReading.getMorningBP() != null) {
            String strMorningSys =  String.valueOf(dailyBPReading.getMorningBP().getSystolic());
            String strMorningDia = String.valueOf(dailyBPReading.getMorningBP().getDiastolic());

            Log.i("Health Monitor", "Reading morning: " + strMorningSys);

            holder.tvMorningSystolic.setText(strMorningSys);
            holder.tvMorningDiastolic.setText(strMorningDia);
        }

        if (dailyBPReading.getAfternoonBP() != null) {
            String strAfternoonSys = String.valueOf(dailyBPReading.getAfternoonBP().getSystolic());
            String strAfternoonDia = String.valueOf(dailyBPReading.getAfternoonBP().getDiastolic());

            holder.tvAfternoonSystolic.setText(strAfternoonSys);
            holder.tvAfternoonDiastolic.setText(strAfternoonDia);
        }

        if (dailyBPReading.getEveningBP() != null) {
            String strEveningSys = String.valueOf(dailyBPReading.getEveningBP().getSystolic());
            String strEveningDia = String.valueOf(dailyBPReading.getEveningBP().getDiastolic());


            holder.tvEveningSystolic.setText(strEveningSys);
            holder.tvEveningDiastolic.setText(strEveningDia);
        }
    }

    @Override
    public int getItemCount() {
        return dailyBPReadings.getDailyReadings().size();
    }

    class BPReadingViewHolder extends RecyclerView.ViewHolder {
        TextView tvReadingDate;
        TextView tvMorningSystolic;
        TextView tvMorningDiastolic;
        TextView tvAfternoonSystolic;
        TextView tvAfternoonDiastolic;
        TextView tvEveningSystolic;
        TextView tvEveningDiastolic;
        CardView bpread;


        public BPReadingViewHolder(View view) {
            super(view);

            tvReadingDate = view.findViewById(R.id.bpreadingdate);

            tvMorningSystolic = view.findViewById(R.id.tvMorningSys);
            tvMorningDiastolic = view.findViewById(R.id.tvMorningDia);

            tvAfternoonSystolic = view.findViewById(R.id.tvAfternoonSys);
            tvAfternoonDiastolic = view.findViewById(R.id.tvAfternoonDia);

            tvEveningSystolic = view.findViewById(R.id.tvEveningSys);
            tvEveningDiastolic = view.findViewById(R.id.tvEveningDia);

            bpread = (CardView) view.findViewById(R.id.bpcard);
        }
    }
}

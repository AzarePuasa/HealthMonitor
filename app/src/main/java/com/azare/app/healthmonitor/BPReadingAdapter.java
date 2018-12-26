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

        holder.tvReadingDate.setText(dailyBPReading.getDate());

        Log.i("Health Monitor", "Reading morning: " + dailyBPReading.getMorningBP().getSystolic() );

        holder.tvMorningSystolic.setText(String.valueOf(dailyBPReading.getMorningBP().getSystolic()));
        holder.tvMorningDiastolic.setText(String.valueOf(dailyBPReading.getMorningBP().getDiastolic()));

        holder.tvAfternoonSystolic.setText(String.valueOf(dailyBPReading.getAfternoonBP().getSystolic()));
        holder.tvAfternoonDiastolic.setText(String.valueOf(dailyBPReading.getAfternoonBP().getDiastolic()));

        holder.tvEveningSystolic.setText(String.valueOf(dailyBPReading.getEveningBP().getSystolic()));
        holder.tvEveningDiastolic.setText(String.valueOf(dailyBPReading.getEveningBP().getDiastolic()));

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
        CardView cv;


        public BPReadingViewHolder(View view) {
            super(view);

            tvReadingDate = view.findViewById(R.id.bpreadingdate);

            tvMorningSystolic = view.findViewById(R.id.tvMorningSys);
            tvMorningDiastolic = view.findViewById(R.id.tvMorningDia);

            tvAfternoonSystolic = view.findViewById(R.id.tvAfternoonSys);
            tvAfternoonDiastolic = view.findViewById(R.id.tvAfternoonDia);

            tvEveningSystolic = view.findViewById(R.id.tvEveningSys);
            tvEveningDiastolic = view.findViewById(R.id.tvEveningDia);

            cv = (CardView) view.findViewById(R.id.cv);
        }
    }
}

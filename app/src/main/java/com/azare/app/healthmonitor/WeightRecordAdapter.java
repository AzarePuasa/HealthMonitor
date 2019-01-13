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
import com.azare.app.healthmonitor.model.WeightRecord;
import com.azare.app.healthmonitor.utils.HMUtils;

import java.util.List;

public class WeightRecordAdapter extends RecyclerView.Adapter<WeightRecordAdapter.WeightRecordViewHolder>{

    private Context context;
    private List<WeightRecord> lWeightRecords;

    public WeightRecordAdapter(List<WeightRecord> lWeightRecords) {
        this.lWeightRecords = lWeightRecords;
        Log.i(HMUtils.LOGTAG, "Total Records: " + this.lWeightRecords.size() );
    }

    @NonNull
    @Override
    public WeightRecordViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //inflate the layout file
        View weightRecordView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.weightrecord_card, viewGroup, false);

        WeightRecordViewHolder viewHolder = new WeightRecordViewHolder(weightRecordView);

        context = viewGroup.getContext();

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WeightRecordViewHolder holder, int position) {

        WeightRecord weightRecord = lWeightRecords.get(position);

        String iDay = String.valueOf(weightRecord.getDay());
        String iMonth = String.valueOf(HMUtils.MONTHS[weightRecord.getMonth()-1]);
        String iWeight = String.valueOf(Double.toString(weightRecord.getWeight()));

        holder.DayOfRecord.setText(iDay);

        //set to 3 letter Month text e.g January => Jan
        holder.MonthOfRecord.setText(iMonth);

        holder.WeightRecorded.setText(iWeight);
    }

    @Override
    public int getItemCount() {
        return this.lWeightRecords.size();
    }

    public void clear() {
        lWeightRecords.clear();
    }

    public void setWeightRecords(List<WeightRecord> lWeightRecords) {
        this.lWeightRecords = lWeightRecords;
    }

    class WeightRecordViewHolder extends RecyclerView.ViewHolder {

        TextView DayOfRecord;
        TextView MonthOfRecord;
        TextView WeightRecorded;
        CardView weightcard;

        public WeightRecordViewHolder(@NonNull View view) {
            super(view);

            DayOfRecord = view.findViewById(R.id.DayOfRecord);
            MonthOfRecord = view.findViewById(R.id.MonthOfRecord);
            WeightRecorded = view.findViewById(R.id.WeightRecorded);
            weightcard = view.findViewById(R.id.weightcard);
        }
    }
}

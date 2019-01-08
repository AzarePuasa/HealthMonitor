package com.azare.app.healthmonitor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.azare.app.healthmonitor.model.WeightRecord;

import java.util.List;

class WeightRecordAdapter extends RecyclerView.Adapter<WeightRecordAdapter.WeightRecordViewHolder>{

    private Context context;
    private List<WeightRecord> lWeightRecords;

    public WeightRecordAdapter(List<WeightRecord> lWeightRecords) {
        this.lWeightRecords = lWeightRecords;
    }

    @NonNull
    @Override
    public WeightRecordViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull WeightRecordViewHolder weightRecordViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class WeightRecordViewHolder extends RecyclerView.ViewHolder {

        public WeightRecordViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

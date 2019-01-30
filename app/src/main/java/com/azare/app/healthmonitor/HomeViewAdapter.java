package com.azare.app.healthmonitor;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.azare.app.healthmonitor.model.HomeItem;

import java.util.ArrayList;

public class HomeViewAdapter extends RecyclerView.Adapter<HomeViewAdapter.HomeViewHolder> {

    ArrayList<HomeItem> mValues;
    Context mContext;
    ItemListener mListener;

    public HomeViewAdapter(Context context, ArrayList<HomeItem> values, ItemListener itemListener) {

        mValues = values;
        mContext = context;
        mListener=itemListener;
    }

    @Override
    public HomeViewAdapter.HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.home_view_item, parent, false);

        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder viewHolder, int position) {
        viewHolder.setData(mValues.get(position));
    }


    @Override
    public int getItemCount() {

        return mValues.size();
    }

    public interface ItemListener {
        void onItemClick(HomeItem item);
    }

    class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textView;
        public ImageView imageView;
        public RelativeLayout relativeLayout;
        HomeItem item;

        public HomeViewHolder(View v) {

            super(v);
            v.setOnClickListener(this);
            textView = (TextView) v.findViewById(R.id.textView);
            imageView = (ImageView) v.findViewById(R.id.imageView);
            relativeLayout = (RelativeLayout) v.findViewById(R.id.relativeLayout);

        }

        public void setData(HomeItem item) {
            this.item = item;

            textView.setText(item.text);
            imageView.setImageResource(item.drawable);
            relativeLayout.setBackgroundColor(Color.parseColor(item.color));

        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onItemClick(item);
            }
        }
    }
}

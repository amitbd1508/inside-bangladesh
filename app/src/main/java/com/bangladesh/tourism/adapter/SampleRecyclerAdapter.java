package com.bangladesh.tourism.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.bangladesh.tourism.R;

import java.util.List;

/**
 * Created by Zakir on 22/02/2016.
 */
public class SampleRecyclerAdapter extends RecyclerView.Adapter<SampleRecyclerAdapter.ViewHolder> {

    private List<String> sampleListItem;
    private Context context;

    public SampleRecyclerAdapter(List<String> tipList, Context context) {
        this.sampleListItem = tipList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String item = sampleListItem.get(position);

        holder.sampleTextView.setText(item);

    }

    @Override
    public int getItemCount() {
        return sampleListItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView sampleTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            sampleTextView = (TextView) itemView.findViewById(R.id.sample_text_view_for_item);
        }
    }
}

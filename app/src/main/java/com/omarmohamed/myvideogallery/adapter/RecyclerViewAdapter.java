package com.omarmohamed.myvideogallery.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.omarmohamed.myvideogallery.R;
import com.omarmohamed.myvideogallery.models.VideoModel;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private List<VideoModel> items;

    public void setItems(List<VideoModel> items) {
        this.items = items;
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
        return new ViewHolder(v);
    }

    @Override public void onBindViewHolder(final ViewHolder holder, int position) {
        final VideoModel item = items.get(position);

        holder.textTitle.setText(item.getTitle());
        //Retrieving the basic string and formatting it with the right value
        holder.textCreationTime.setText(String.format(holder.textCreationTime.getText().toString(), item.getCreationTime()));
        holder.textDuration.setText(String.format(holder.textDuration.getText().toString(), item.getDuration()));
        holder.textTimestamp.setText(item.getTimestamp());

        holder.itemView.setTag(item);
    }

    @Override public int getItemCount() {
        return items.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.imageThumbnail)
        ImageView imageThumbnail;
        @InjectView(R.id.textTitle)
        TextView textTitle;
        @InjectView(R.id.textTimestamp)
        TextView textTimestamp;
        @InjectView(R.id.textDuration)
        TextView textDuration;
        @InjectView(R.id.textCreationTime)
        TextView textCreationTime;

        public ViewHolder(View itemView) {
            super(itemView);
            
            ButterKnife.inject(this, itemView);
        }
    }

}

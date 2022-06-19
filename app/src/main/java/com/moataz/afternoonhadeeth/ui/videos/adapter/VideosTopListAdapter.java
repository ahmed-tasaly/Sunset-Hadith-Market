package com.moataz.afternoonhadeeth.ui.videos.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.moataz.afternoonhadeeth.R;
import com.moataz.afternoonhadeeth.data.model.videos.top.TopVideosList;
import com.moataz.afternoonhadeeth.data.model.videos.top.TopVideosResponse;
import com.moataz.afternoonhadeeth.databinding.ItemVideosTopListVideosBinding;
import com.moataz.afternoonhadeeth.ui.home.view.activity.YoutubePlayerActivity;

import java.util.Collections;

public class VideosTopListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private TopVideosResponse items = null;

    @SuppressLint("NotifyDataSetChanged")
    public void setTopVideosList(TopVideosResponse items) {
        this.items = items;
        Collections.shuffle(items.getTopVideosList());
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TopVideosViewHolder(
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.getContext()),
                        R.layout.item_videos_top_list_videos,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TopVideosList topVideosList = items.getTopVideosList().get(position);
        ((TopVideosViewHolder) holder).itemVideosTopListVideosBinding.setListTopVideosModel(topVideosList);
        ((TopVideosViewHolder) holder).setOnClick(topVideosList);
    }

    @Override
    public int getItemCount() {
        if (items == null) return 0;
        return items.getTopVideosList().size();
    }

    static class TopVideosViewHolder extends RecyclerView.ViewHolder {
        ItemVideosTopListVideosBinding itemVideosTopListVideosBinding;

        TopVideosViewHolder(@NonNull ItemVideosTopListVideosBinding itemView) {
            super(itemView.getRoot());
            itemVideosTopListVideosBinding = itemView;
        }

        void setOnClick(TopVideosList topVideosList) {
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), YoutubePlayerActivity.class);
                intent.putExtra("url", topVideosList.getUrl());
                itemView.getContext().startActivity(intent);
            });
        }
    }
}
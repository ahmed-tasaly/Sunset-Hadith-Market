package com.moataz.afternoonhadeeth.ui.videos.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.moataz.afternoonhadeeth.R;
import com.moataz.afternoonhadeeth.data.model.videos.top.Data;
import com.moataz.afternoonhadeeth.databinding.ListDataVideosBlocksBinding;
import com.moataz.afternoonhadeeth.ui.home.view.activity.YoutubePlayerActivity;

import java.util.List;

public class VideosTopBlocksListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Data> items = null;

    @SuppressLint("NotifyDataSetChanged")
    public void setDataList(List<Data> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DisplayListFromBlocksViewHolder(
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.getContext()),
                        R.layout.list_data_videos_blocks,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Data dataList = items.get(position);
        ((DisplayListFromBlocksViewHolder) holder).listDataVideosBlocksBinding.setListBlocksVideosModel(dataList);
        ((DisplayListFromBlocksViewHolder) holder).setOnClick(dataList);
    }

    @Override
    public int getItemCount() {
        if (items == null) return 0;
        return items.size();
    }

    static class DisplayListFromBlocksViewHolder extends RecyclerView.ViewHolder {
        ListDataVideosBlocksBinding listDataVideosBlocksBinding;

        DisplayListFromBlocksViewHolder(@NonNull ListDataVideosBlocksBinding itemView) {
            super(itemView.getRoot());
            listDataVideosBlocksBinding = itemView;
        }

        void setOnClick(Data dataList) {
            listDataVideosBlocksBinding.tets.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), YoutubePlayerActivity.class);
                intent.putExtra("url", dataList.getUrl());
                itemView.getContext().startActivity(intent);
            });
        }
    }
}

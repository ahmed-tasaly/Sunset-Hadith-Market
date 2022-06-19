package com.moataz.afternoonhadeeth.ui.videos.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.moataz.afternoonhadeeth.R;
import com.moataz.afternoonhadeeth.data.model.videos.top.Data;
import com.moataz.afternoonhadeeth.data.model.videos.top.TopVideosBlocks;
import com.moataz.afternoonhadeeth.data.model.videos.top.TopVideosResponse;
import com.moataz.afternoonhadeeth.databinding.ItemVideosTopBlocksBinding;
import com.moataz.afternoonhadeeth.ui.videos.view.activity.DisplayVideoBlocksActivity;
import com.moataz.afternoonhadeeth.utils.helper.Intents;

import java.util.ArrayList;
import java.util.Objects;

public class VideosTopBlocksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private TopVideosResponse items = null;

    @SuppressLint("NotifyDataSetChanged")
    public void setTopVideosBlocks(TopVideosResponse items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BlocksViewHolder(
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.getContext()),
                        R.layout.item_videos_top_blocks,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TopVideosBlocks blocks = items.getTopVideosBlocks().get(position);
        ((BlocksViewHolder) holder).itemVideosTopBlocksBinding.setBlocksTopVideosModel(blocks);
        ((BlocksViewHolder) holder).setOnClick(blocks);
    }

    @Override
    public int getItemCount() {
        if (items == null) return 0;
        return items.getTopVideosBlocks().size();
    }

    static class BlocksViewHolder extends RecyclerView.ViewHolder {
        ItemVideosTopBlocksBinding itemVideosTopBlocksBinding;

        BlocksViewHolder(@NonNull ItemVideosTopBlocksBinding itemView) {
            super(itemView.getRoot());
            itemVideosTopBlocksBinding = itemView;
        }

        void setOnClick(TopVideosBlocks topVideosBlocks) {
            itemVideosTopBlocksBinding.itemTopVideos1.setOnClickListener(v ->
                    Intents.INSTANCE.openNewActivityWithVideos(itemView.getContext(),
                            DisplayVideoBlocksActivity.class,
                            Objects.requireNonNull(Objects.requireNonNull(topVideosBlocks.getItemOne()).getTitle()),
                            (ArrayList<Data>) Objects.requireNonNull(topVideosBlocks.getItemOne().getDataList())));

            itemVideosTopBlocksBinding.itemTopVideos2.setOnClickListener(v ->
                    Intents.INSTANCE.openNewActivityWithVideos(itemView.getContext(),
                            DisplayVideoBlocksActivity.class,
                            Objects.requireNonNull(Objects.requireNonNull(topVideosBlocks.getItemTwo()).getTitle()),
                            (ArrayList<Data>) Objects.requireNonNull(topVideosBlocks.getItemTwo().getDataList())));

            itemVideosTopBlocksBinding.itemTopVideos3.setOnClickListener(v ->
                    Intents.INSTANCE.openNewActivityWithVideos(itemView.getContext(),
                            DisplayVideoBlocksActivity.class,
                            Objects.requireNonNull(Objects.requireNonNull(topVideosBlocks.getItemThree()).getTitle()),
                            (ArrayList<Data>) Objects.requireNonNull(topVideosBlocks.getItemThree().getDataList())));

            itemVideosTopBlocksBinding.itemTopVideos4.setOnClickListener(v ->
                    Intents.INSTANCE.openNewActivityWithVideos(itemView.getContext(),
                            DisplayVideoBlocksActivity.class,
                            Objects.requireNonNull(Objects.requireNonNull(topVideosBlocks.getItemFour()).getTitle()),
                            (ArrayList<Data>) Objects.requireNonNull(topVideosBlocks.getItemFour().getDataList())));
        }
    }
}
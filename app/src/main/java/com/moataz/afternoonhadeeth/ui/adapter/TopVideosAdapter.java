package com.moataz.afternoonhadeeth.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.moataz.afternoonhadeeth.R;
import com.moataz.afternoonhadeeth.data.model.videos.top.Data;
import com.moataz.afternoonhadeeth.data.model.videos.top.TopVideosBlocks;
import com.moataz.afternoonhadeeth.data.model.videos.top.TopVideosList;
import com.moataz.afternoonhadeeth.data.model.videos.top.TopVideosResponse;
import com.moataz.afternoonhadeeth.databinding.ItemVideosTopBlocksBinding;
import com.moataz.afternoonhadeeth.databinding.ItemVideosTopListVideosBinding;
import com.moataz.afternoonhadeeth.ui.view.activity.DataInsideBlocksActivity;
import com.moataz.afternoonhadeeth.ui.view.activity.YoutubePlayerActivity;
import com.moataz.afternoonhadeeth.utils.helper.Intents;

import java.util.ArrayList;
import java.util.Objects;

public class TopVideosAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private TopVideosResponse items = null;

    @SuppressLint("NotifyDataSetChanged")
    public void setTopVideosList(TopVideosResponse items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == R.id.item_top_videos_1) {
            return new BlocksViewHolder(
                    DataBindingUtil.inflate(
                            LayoutInflater.from(parent.getContext()),
                            R.layout.item_videos_top_blocks,
                            parent,
                            false
                    )
            );
        } else if (viewType == R.id.image_top_videos) {
            return new TopVideosViewHolder(
                    DataBindingUtil.inflate(
                            LayoutInflater.from(parent.getContext()),
                            R.layout.item_videos_top_list_videos,
                            parent,
                            false
                    )
            );
        } else throw new IllegalArgumentException("unknown view type");
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == R.id.item_top_videos_1) {
            TopVideosBlocks blocks = items.getTopVideosBlocks().get(position);
            ((BlocksViewHolder) holder).itemVideosTopBlocksBinding.setBlocksTopVideosModel(blocks);
            ((BlocksViewHolder) holder).setOnClick(blocks);

        } else if (getItemViewType(position) == R.id.image_top_videos) {
            TopVideosList topVideosList = items.getTopVideosList().get(position - items.getTopVideosList().size());
            ((TopVideosViewHolder) holder).itemVideosTopListVideosBinding.setListTopVideosModel(topVideosList);
            ((TopVideosViewHolder) holder).setOnClick(topVideosList);
        }
    }

    @Override
    public int getItemCount() {
        if (items == null) return 0;
        return items.getTopVideosBlocks().size() + items.getTopVideosList().size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && position < items.getTopVideosBlocks().size()) {
            return R.id.item_top_videos_1;

        } else if (position == items.getTopVideosBlocks().size() && position < items.getTopVideosBlocks().size()
                + items.getTopVideosList().size()) {
            return R.id.image_top_videos;

        }
        return R.id.image_top_videos;
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
                            DataInsideBlocksActivity.class,
                            Objects.requireNonNull(Objects.requireNonNull(topVideosBlocks.getItemTwo()).getTitle()),
                            (ArrayList<Data>) Objects.requireNonNull(topVideosBlocks.getItemTwo().getDataList())));

            itemVideosTopBlocksBinding.itemTopVideos2.setOnClickListener(v ->
                    Intents.INSTANCE.openNewActivityWithVideos(itemView.getContext(),
                            DataInsideBlocksActivity.class,
                            Objects.requireNonNull(Objects.requireNonNull(topVideosBlocks.getItemTwo()).getTitle()),
                            (ArrayList<Data>) Objects.requireNonNull(topVideosBlocks.getItemTwo().getDataList())));

            itemVideosTopBlocksBinding.itemTopVideos3.setOnClickListener(v ->
                    Intents.INSTANCE.openNewActivityWithVideos(itemView.getContext(),
                            DataInsideBlocksActivity.class,
                            Objects.requireNonNull(Objects.requireNonNull(topVideosBlocks.getItemThree()).getTitle()),
                            (ArrayList<Data>) Objects.requireNonNull(topVideosBlocks.getItemThree().getDataList())));

            itemVideosTopBlocksBinding.itemTopVideos4.setOnClickListener(v ->
                    Intents.INSTANCE.openNewActivityWithVideos(itemView.getContext(),
                            DataInsideBlocksActivity.class,
                            Objects.requireNonNull(Objects.requireNonNull(topVideosBlocks.getItemFour()).getTitle()),
                            (ArrayList<Data>) Objects.requireNonNull(topVideosBlocks.getItemFour().getDataList())));
        }
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
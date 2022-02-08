package com.moataz.afternoonhadeeth.ui.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.moataz.afternoonhadeeth.R;
import com.moataz.afternoonhadeeth.data.model.home.blocks.DataList;

import java.util.List;

public class InfoBlocksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<DataList> items = null;

    @SuppressLint("NotifyDataSetChanged")
    public void setDataList(List<DataList> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InfoBlocksAdapter.DisplayListFromBlocksViewHolder(
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.getContext()),
                        R.layout.list_data_blocks,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        DataList dataList = items.get(position);
        ((DisplayListFromBlocksViewHolder) holder).listDataBlocksBinding.setDataListBlocksModel(dataList);
        ((InfoBlocksAdapter.DisplayListFromBlocksViewHolder) holder).setOnClick(dataList);
    }

    @Override
    public int getItemCount() {
        if (items == null) return 0;
        return items.size();
    }

    static class DisplayListFromBlocksViewHolder extends RecyclerView.ViewHolder {
        ListDataBlocksBinding listDataBlocksBinding;
        DataList dataList;

        DisplayListFromBlocksViewHolder(@NonNull ListDataBlocksBinding itemView) {
            super(itemView.getRoot());
            listDataBlocksBinding = itemView;
        }

        void setOnClick(DataList hadith) {
        }
    }
}

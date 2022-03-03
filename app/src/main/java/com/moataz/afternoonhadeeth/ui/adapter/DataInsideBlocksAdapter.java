package com.moataz.afternoonhadeeth.ui.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.moataz.afternoonhadeeth.R;
import com.moataz.afternoonhadeeth.data.model.home.blocks.DataList;
import com.moataz.afternoonhadeeth.databinding.ListDataBlocksBinding;
import com.moataz.afternoonhadeeth.utils.helper.Intents;

import java.util.List;
import java.util.Objects;

public class DataInsideBlocksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<DataList> items = null;

    @SuppressLint("NotifyDataSetChanged")
    public void setDataList(List<DataList> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DisplayListFromBlocksViewHolder(
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
        ((DisplayListFromBlocksViewHolder) holder).setOnClick(dataList);
    }

    @Override
    public int getItemCount() {
        if (items == null) return 0;
        return items.size();
    }

    static class DisplayListFromBlocksViewHolder extends RecyclerView.ViewHolder {
        ListDataBlocksBinding listDataBlocksBinding;

        DisplayListFromBlocksViewHolder(@NonNull ListDataBlocksBinding itemView) {
            super(itemView.getRoot());
            listDataBlocksBinding = itemView;
        }

        void setOnClick(DataList dataList) {
            listDataBlocksBinding.tets.setOnClickListener(v-> Intents.INSTANCE.openTabUrl(itemView.getContext(), Objects.requireNonNull(dataList.getUrl())));
        }
    }
}

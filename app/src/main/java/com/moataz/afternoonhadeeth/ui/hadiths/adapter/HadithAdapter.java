package com.moataz.afternoonhadeeth.ui.hadiths.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.moataz.afternoonhadeeth.R;
import com.moataz.afternoonhadeeth.data.model.hadith.Hadith;
import com.moataz.afternoonhadeeth.data.model.hadith.HadithMainData;
import com.moataz.afternoonhadeeth.data.model.hadith.HadithResponse;
import com.moataz.afternoonhadeeth.databinding.ListHadithBinding;
import com.moataz.afternoonhadeeth.ui.hadiths.view.activity.DisplayHadithListActivity;
import com.moataz.afternoonhadeeth.utils.helper.Intents;

import java.util.ArrayList;

public class HadithAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private HadithResponse items = null;

    @SuppressLint("NotifyDataSetChanged")
    public void setHadithList(HadithResponse items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HadithViewHolder(
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.getContext()),
                        R.layout.list_hadith,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HadithMainData hadith = items.getHadithMainData().get(position);
        ((HadithViewHolder) holder).listHadithBinding.setHadithMainModel(hadith);
        ((HadithViewHolder) holder).setOnClick(hadith);
    }

    @Override
    public int getItemCount() {
        if (items == null) return 0;
        return items.getHadithMainData().size();
    }

    static class HadithViewHolder extends RecyclerView.ViewHolder {
        ListHadithBinding listHadithBinding;

        HadithViewHolder(@NonNull ListHadithBinding itemView) {
            super(itemView.getRoot());
            listHadithBinding = itemView;
        }

        void setOnClick(HadithMainData hadithData) {
            itemView.setOnClickListener(v ->
                    Intents.INSTANCE.openNewActivityWithHadith(itemView.getContext(),
                            DisplayHadithListActivity.class,
                            hadithData.getTitle(),
                            (ArrayList<Hadith>) hadithData.getDataList()));
        }
    }
}
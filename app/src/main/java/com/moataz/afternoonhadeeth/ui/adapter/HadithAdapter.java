package com.moataz.afternoonhadeeth.ui.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.moataz.afternoonhadeeth.R;
import com.moataz.afternoonhadeeth.data.model.hadith.Hadith;
import com.moataz.afternoonhadeeth.databinding.ListHadithBinding;
import com.moataz.afternoonhadeeth.utils.helper.Intents;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class HadithAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Hadith> items = null;

    @SuppressLint("NotifyDataSetChanged")
    public void setHadithList(List<Hadith> items) {
        this.items = items;
        Collections.shuffle(items);
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
        Hadith hadith = items.get(position);
        ((HadithViewHolder) holder).listHadithBinding.setHadithModel(hadith);
        ((HadithViewHolder) holder).setOnClick(hadith);
    }

    @Override
    public int getItemCount() {
        if (items == null) return 0;
        return items.size();
    }

    static class HadithViewHolder extends RecyclerView.ViewHolder {
        ListHadithBinding listHadithBinding;

        HadithViewHolder(@NonNull ListHadithBinding itemView) {
            super(itemView.getRoot());
            listHadithBinding = itemView;
        }

        void setOnClick(Hadith hadith) {
            listHadithBinding.copyButtonOnClick.setOnClickListener(view -> {
                Intents.INSTANCE.copyText(Objects.requireNonNull(hadith.getHadith()), itemView.getContext(),"");
                Intents.INSTANCE.shareTextSnackbar(itemView.getRootView(), "تم نسخ الحديث", Objects.requireNonNull(hadith.getHadith()), itemView.getContext());
            });

            listHadithBinding.shareButtonOnClick.setOnClickListener(view -> {
                Intents.INSTANCE.copyText(Objects.requireNonNull(hadith.getHadith()), itemView.getContext(),"");
                Intents.INSTANCE.sharedText(itemView.getContext(), Objects.requireNonNull(hadith.getHadith()), "تم الإرسال من تطبيق حديث الغروب: أحاديث النبي ﷺ","");
            });
        }
    }
}
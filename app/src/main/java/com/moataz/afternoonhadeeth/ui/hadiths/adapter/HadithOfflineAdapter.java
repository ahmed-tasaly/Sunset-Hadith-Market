package com.moataz.afternoonhadeeth.ui.hadiths.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.moataz.afternoonhadeeth.R;
import com.moataz.afternoonhadeeth.databinding.ListHadithOfflineBinding;
import com.moataz.afternoonhadeeth.data.model.offline.HadithOffline;
import com.moataz.afternoonhadeeth.utils.helper.Intents;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class HadithOfflineAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<HadithOffline> items = new ArrayList<>();
    private final Context context;

    @SuppressLint("NotifyDataSetChanged")
    public void setHadithList(List<HadithOffline> items) {
        this.items = items;
        Collections.shuffle(items);
        notifyDataSetChanged();
    }

    public HadithOfflineAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HadithOfflineViewHolder(
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.getContext()),
                        R.layout.list_hadith_offline,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HadithOffline hadithOffline = items.get(position);
        ((HadithOfflineViewHolder) holder).listHadithBinding.setHadithModelOffline(hadithOffline);
        ((HadithOfflineViewHolder) holder).setOnClick(hadithOffline);
    }

    @Override
    public int getItemCount() {
        if (items == null) return 0;
        return items.size();
    }

    static class HadithOfflineViewHolder extends RecyclerView.ViewHolder {
        ListHadithOfflineBinding listHadithBinding;

        HadithOfflineViewHolder(@NonNull ListHadithOfflineBinding itemView) {
            super(itemView.getRoot());
            listHadithBinding = itemView;
        }

        void setOnClick(HadithOffline hadithOffline) {
            listHadithBinding.copyButtonOnClick.setOnClickListener(view -> {
                Intents.INSTANCE.copyText(Objects.requireNonNull(hadithOffline.getHadith()), itemView.getContext(),"");
                Intents.INSTANCE.shareTextSnackbar(itemView.getRootView(), "تم نسخ الحديث", Objects.requireNonNull(hadithOffline.getHadith()), itemView.getContext());
            });

            listHadithBinding.shareButtonOnClick.setOnClickListener(view -> {
                Intents.INSTANCE.copyText(Objects.requireNonNull(hadithOffline.getHadith()), itemView.getContext(),"");
                Intents.INSTANCE.sharedText(itemView.getContext(), Objects.requireNonNull(hadithOffline.getHadith()), "تم الإرسال من تطبيق حديث الغروب: أحاديث النبي ﷺ","");
            });
        }
    }
}
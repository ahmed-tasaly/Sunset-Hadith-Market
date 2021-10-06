package com.moataz.afternoonhadeeth.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.moataz.afternoonhadeeth.R;
import com.moataz.afternoonhadeeth.data.model.DailyImage;
import com.moataz.afternoonhadeeth.data.model.DailyPost;
import com.moataz.afternoonhadeeth.data.model.FirstImage;
import com.moataz.afternoonhadeeth.data.model.HomeResponse;
import com.moataz.afternoonhadeeth.data.model.KanzHasanat;
import com.moataz.afternoonhadeeth.data.model.Live;
import com.moataz.afternoonhadeeth.data.model.SaheehBukhari;
import com.moataz.afternoonhadeeth.data.model.SaheehMuslim;
import com.moataz.afternoonhadeeth.data.model.TahzeebMuslim;
import com.moataz.afternoonhadeeth.ui.view.activity.YoutubePlayerActivity;

import java.util.Objects;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private HomeResponse items = null;

    @SuppressLint("NotifyDataSetChanged")
    public void setHomeList(HomeResponse items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == R.id.first_image) {
            return new FirstImageViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.item_home_firstimage,
                            parent,
                            false
                    )
            );
        } else if (viewType == R.id.live_image) {
            return new LiveViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.item_home_live,
                            parent,
                            false
                    )
            );
        } else if (viewType == R.id.daily_post) {
            return new DailyPostViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.item_home_dailypost,
                            parent,
                            false
                    )
            );
        } else if (viewType == R.id.text_kanz_hasanat) {
            return new KanzHasanatViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.item_home_kanzhasanat,
                            parent,
                            false
                    )
            );
        } else if (viewType == R.id.text_tahzeeb_muslim) {
            return new TahzeebMuslimViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.item_home_tahzeebmuslim,
                            parent,
                            false
                    )
            );
        } else if (viewType == R.id.daily_image) {
            return new DailyImageViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.item_home_dailyimage,
                            parent,
                            false
                    )
            );
        } else if (viewType == R.id.text_saheeh_bukhari) {
            return new SaheehBukhariViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.item_home_saheehbukhari,
                            parent,
                            false
                    )
            );
        } else if (viewType == R.id.text_saheeh_muslim) {
            return new SaheehMuslimViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.item_home_saheehmuslim,
                            parent,
                            false
                    )
            );
        } else throw new IllegalArgumentException("unknown view type");
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (getItemViewType(position) == R.id.first_image) {
            FirstImage firstImage = items.getFirstImage().get(position);
            ((FirstImageViewHolder) holder).setFirstImageData(firstImage);

        } else if (getItemViewType(position) == R.id.live_image) {
            Live live = items.getLive().get(position - items.getFirstImage().size());
            ((LiveViewHolder) holder).setLiveImageData(live);
            ((LiveViewHolder) holder).setOnClick(live);

        } else if (getItemViewType(position) == R.id.daily_post) {
            DailyPost dailyPost = items.getDailyPost().get(position - (items.getFirstImage().size() + items.getLive().size()));
            ((DailyPostViewHolder) holder).setDailyPostData(dailyPost);

        } else if (getItemViewType(position) == R.id.text_kanz_hasanat) {
            KanzHasanat kanzHasanat = items.getKanzHasanat().get(position - (items.getFirstImage().size() + items.getLive().size() + items.getDailyPost().size()));
            ((KanzHasanatViewHolder) holder).setKanzHasanatData(kanzHasanat);

        } else if (getItemViewType(position) == R.id.text_tahzeeb_muslim) {
            TahzeebMuslim tahzeebMuslim = items.getTahzeebMuslim().get(position - (items.getFirstImage().size() + items.getLive().size() + items.getDailyPost().size() + items.getKanzHasanat().size()));
            ((TahzeebMuslimViewHolder) holder).setTahzeebMuslimData(tahzeebMuslim);

        } else if (getItemViewType(position) == R.id.daily_image) {
            DailyImage dailyImage = items.getDailyImage().get(position - (items.getFirstImage().size() + items.getLive().size() + items.getDailyPost().size() + items.getKanzHasanat().size() + items.getTahzeebMuslim().size()));
            ((DailyImageViewHolder) holder).setDailyImageData(dailyImage);

        } else if (getItemViewType(position) == R.id.text_saheeh_bukhari) {
            SaheehBukhari saheehBukhari = items.getSaheehBukhari().get(position - (items.getFirstImage().size() + items.getLive().size() + items.getDailyPost().size() + items.getKanzHasanat().size() + items.getTahzeebMuslim().size() + items.getDailyImage().size()));
            ((SaheehBukhariViewHolder) holder).setSaheehBukhariData(saheehBukhari);

        } else if (getItemViewType(position) == R.id.text_saheeh_muslim) {
            SaheehMuslim saheehMuslim = items.getSaheehMuslim().get(position - (items.getFirstImage().size() + items.getLive().size() + items.getDailyPost().size() + items.getKanzHasanat().size() + items.getTahzeebMuslim().size() + items.getDailyImage().size() + items.getSaheehBukhari().size()));
            ((SaheehMuslimViewHolder) holder).setSaheehMuslimData(saheehMuslim);

        }
    }

    @Override
    public int getItemCount() {
        if (items == null) return 0;
        return items.getFirstImage().size() + items.getLive().size() + items.getDailyPost().size() + items.getKanzHasanat().size() + items.getTahzeebMuslim().size() + items.getDailyImage().size() + items.getSaheehBukhari().size() + items.getSaheehMuslim().size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && position < items.getFirstImage().size()) {
            return R.id.first_image;

        } else if (position == items.getFirstImage().size() && position < items.getFirstImage().size()
                + items.getLive().size()) {
            return R.id.live_image;

        } else if (position == items.getFirstImage().size() + items.getLive().size() && position < items.getFirstImage().size()
                + items.getLive().size() + items.getDailyPost().size()) {
            return R.id.daily_post;

        } else if (position == items.getFirstImage().size() + items.getLive().size() + items.getDailyPost().size() && position < items.getFirstImage().size()
                + items.getLive().size() + items.getDailyPost().size() + items.getKanzHasanat().size()) {
            return R.id.text_kanz_hasanat;

        } else if (position == items.getFirstImage().size() + items.getLive().size() + items.getDailyPost().size() + items.getKanzHasanat().size() & position < items.getFirstImage().size()
                + items.getLive().size() + items.getDailyPost().size() + items.getKanzHasanat().size() + items.getTahzeebMuslim().size()) {
            return R.id.text_tahzeeb_muslim;

        } else if (position == items.getFirstImage().size() + items.getLive().size() + items.getDailyPost().size() + items.getKanzHasanat().size() + items.getTahzeebMuslim().size() & position < items.getFirstImage().size()
                + items.getLive().size() + items.getDailyPost().size() + items.getKanzHasanat().size() + items.getTahzeebMuslim().size() + items.getDailyImage().size()) {
            return R.id.daily_image;

        } else if (position == items.getFirstImage().size() + items.getLive().size() + items.getDailyPost().size() + items.getKanzHasanat().size() + items.getTahzeebMuslim().size() + items.getDailyImage().size() & position < items.getFirstImage().size()
                + items.getLive().size() + items.getDailyPost().size() + items.getKanzHasanat().size() + items.getTahzeebMuslim().size() + items.getDailyImage().size() + items.getSaheehBukhari().size()) {
            return R.id.text_saheeh_bukhari;

        } else if (position == items.getFirstImage().size() + items.getLive().size() + items.getDailyPost().size() + items.getKanzHasanat().size() + items.getTahzeebMuslim().size() + items.getDailyImage().size() + items.getSaheehBukhari().size() & position < items.getFirstImage().size()
                + items.getLive().size() + items.getDailyPost().size() + items.getKanzHasanat().size() + items.getTahzeebMuslim().size() + items.getDailyImage().size() + items.getSaheehBukhari().size() + items.getSaheehMuslim().size()) {
            return R.id.text_saheeh_muslim;

        } return R.id.first_image;
    }

    static class FirstImageViewHolder extends RecyclerView.ViewHolder {
        private final ImageView firstImage;

        FirstImageViewHolder(@NonNull View itemView) {
            super(itemView);
            firstImage = itemView.findViewById(R.id.first_image);
        }

        void setFirstImageData(FirstImage firstImage) {
            Glide.with(itemView.getContext())
                    .load(firstImage.getImageUrl())
                    .into(this.firstImage);
        }
    }

    static class LiveViewHolder extends RecyclerView.ViewHolder {
        private final ImageView liveImage;

        LiveViewHolder(@NonNull View itemView) {
            super(itemView);
            liveImage = itemView.findViewById(R.id.live_image);
        }

        void setLiveImageData(Live live) {
            Glide.with(itemView.getContext())
                    .load(live.getImage())
                    .into(liveImage);
        }

        void setOnClick(Live live) {
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), YoutubePlayerActivity.class);
                intent.putExtra("url", live.getUrl());
                itemView.getContext().startActivity(intent);
            });
        }
    }

    static class DailyPostViewHolder extends RecyclerView.ViewHolder {
        private final ImageView dailyPost;

        DailyPostViewHolder(@NonNull View itemView) {
            super(itemView);
            dailyPost = itemView.findViewById(R.id.daily_post);
        }

        void setDailyPostData(DailyPost dailyPost) {
            Glide.with(itemView.getContext())
                    .load(dailyPost.getImageUrl())
                    .into(this.dailyPost);
        }
    }

    static class KanzHasanatViewHolder extends RecyclerView.ViewHolder {
        private final TextView kanzHasanat;

        KanzHasanatViewHolder(@NonNull View itemView) {
            super(itemView);
            kanzHasanat = itemView.findViewById(R.id.text_kanz_hasanat);
        }

        void setKanzHasanatData(KanzHasanat kanzHasanat) {
            this.kanzHasanat.setText(kanzHasanat.getText());
        }
    }

    static class TahzeebMuslimViewHolder extends RecyclerView.ViewHolder {
        private final TextView tahzeebMuslim;

        TahzeebMuslimViewHolder(@NonNull View itemView) {
            super(itemView);
            tahzeebMuslim = itemView.findViewById(R.id.text_tahzeeb_muslim);
        }

        void setTahzeebMuslimData(TahzeebMuslim tahzeebMuslim) {
            this.tahzeebMuslim.setText(tahzeebMuslim.getText());
        }
    }

    static class DailyImageViewHolder extends RecyclerView.ViewHolder {
        private final ImageView dailyImage;

        DailyImageViewHolder(@NonNull View itemView) {
            super(itemView);
            dailyImage = itemView.findViewById(R.id.daily_image);
        }

        void setDailyImageData(DailyImage dailyImage) {
            Glide.with(itemView.getContext())
                    .load(dailyImage.getImageUrl())
                    .into(this.dailyImage);
        }
    }

    static class SaheehBukhariViewHolder extends RecyclerView.ViewHolder {
        private final TextView saheehBukhari;

        SaheehBukhariViewHolder(@NonNull View itemView) {
            super(itemView);
            saheehBukhari = itemView.findViewById(R.id.text_saheeh_bukhari);
        }

        void setSaheehBukhariData(SaheehBukhari saheehBukhari) {
            this.saheehBukhari.setText(saheehBukhari.getText());
        }
    }

    static class SaheehMuslimViewHolder extends RecyclerView.ViewHolder {
        private final TextView saheehMuslim;

        SaheehMuslimViewHolder(@NonNull View itemView) {
            super(itemView);
            saheehMuslim = itemView.findViewById(R.id.text_saheeh_muslim);
        }

        void setSaheehMuslimData(SaheehMuslim saheehMuslim) {
            this.saheehMuslim.setText(saheehMuslim.getText());
        }
    }
}

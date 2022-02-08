package com.moataz.afternoonhadeeth.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.moataz.afternoonhadeeth.R;
import com.moataz.afternoonhadeeth.data.model.home.Counter;
import com.moataz.afternoonhadeeth.data.model.home.DailyImage;
import com.moataz.afternoonhadeeth.data.model.home.FirstItem;
import com.moataz.afternoonhadeeth.data.model.home.HomeResponse;
import com.moataz.afternoonhadeeth.data.model.home.KanzHasanat;
import com.moataz.afternoonhadeeth.data.model.home.Live;
import com.moataz.afternoonhadeeth.data.model.home.SaheehBukhari;
import com.moataz.afternoonhadeeth.data.model.home.SaheehMuslim;
import com.moataz.afternoonhadeeth.data.model.home.TahzeebMuslim;
import com.moataz.afternoonhadeeth.data.model.home.blocks.Blocks;
import com.moataz.afternoonhadeeth.data.model.home.blocks.DataList;
import com.moataz.afternoonhadeeth.databinding.ItemHomeBlocksBinding;
import com.moataz.afternoonhadeeth.databinding.ItemHomeCounterBinding;
import com.moataz.afternoonhadeeth.databinding.ItemHomeDailyimageBinding;
import com.moataz.afternoonhadeeth.databinding.ItemHomeFirstitemBinding;
import com.moataz.afternoonhadeeth.databinding.ItemHomeKanzhasanatBinding;
import com.moataz.afternoonhadeeth.databinding.ItemHomeLiveBinding;
import com.moataz.afternoonhadeeth.databinding.ItemHomeSaheehbukhariBinding;
import com.moataz.afternoonhadeeth.databinding.ItemHomeSaheehmuslimBinding;
import com.moataz.afternoonhadeeth.databinding.ItemHomeTahzeebmuslimBinding;
import com.moataz.afternoonhadeeth.ui.view.activity.DisplayImageActivity;
import com.moataz.afternoonhadeeth.ui.view.activity.InfoBlocksActivity;
import com.moataz.afternoonhadeeth.ui.view.activity.PDFActivity;
import com.moataz.afternoonhadeeth.ui.view.activity.YoutubePlayerActivity;
import com.moataz.afternoonhadeeth.utils.helper.Actions;
import com.moataz.afternoonhadeeth.utils.helper.Intents;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private HomeResponse items = null;
    private static final Intents textAction = Intents.INSTANCE;

    @SuppressLint("NotifyDataSetChanged")
    public void setHomeList(HomeResponse items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == R.id.hadith_text) {
            return new FirstItemViewHolder(
                    DataBindingUtil.inflate(
                            LayoutInflater.from(parent.getContext()),
                            R.layout.item_home_firstitem,
                            parent,
                            false
                    )
            );
        } else if (viewType == R.id.item_1) {
            return new BlocksViewHolder(
                    DataBindingUtil.inflate(
                            LayoutInflater.from(parent.getContext()),
                            R.layout.item_home_blocks,
                            parent,
                            false
                    )
            );
        } else if (viewType == R.id.hadith_text_counter) {
            return new CounterViewHolder(
                    DataBindingUtil.inflate(
                            LayoutInflater.from(parent.getContext()),
                            R.layout.item_home_counter,
                            parent,
                            false
                    )
            );
        } else if (viewType == R.id.hadith_text_kanzhasanat) {
            return new KanzHasanatViewHolder(
                    DataBindingUtil.inflate(
                            LayoutInflater.from(parent.getContext()),
                            R.layout.item_home_kanzhasanat,
                            parent,
                            false
                    )
            );
        } else if (viewType == R.id.text_tahzeeb_muslim) {
            return new TahzeebMuslimViewHolder(
                    DataBindingUtil.inflate(
                            LayoutInflater.from(parent.getContext()),
                            R.layout.item_home_tahzeebmuslim,
                            parent,
                            false
                    )
            );
        } else if (viewType == R.id.daily_image) {
            return new DailyImageViewHolder(
                    DataBindingUtil.inflate(
                            LayoutInflater.from(parent.getContext()),
                            R.layout.item_home_dailyimage,
                            parent,
                            false
                    )
            );
        } else if (viewType == R.id.text_saheeh_bukhari) {
            return new SaheehBukhariViewHolder(
                    DataBindingUtil.inflate(
                            LayoutInflater.from(parent.getContext()),
                            R.layout.item_home_saheehbukhari,
                            parent,
                            false
                    )
            );
        } else if (viewType == R.id.live_image) {
            return new LiveViewHolder(
                    DataBindingUtil.inflate(
                            LayoutInflater.from(parent.getContext()),
                            R.layout.item_home_live,
                            parent,
                            false
                    )
            );
        } else if (viewType == R.id.text_saheeh_muslim) {
            return new SaheehMuslimViewHolder(
                    DataBindingUtil.inflate(
                            LayoutInflater.from(parent.getContext()),
                            R.layout.item_home_saheehmuslim,
                            parent,
                            false
                    )
            );
        } else throw new IllegalArgumentException("unknown view type");
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == R.id.hadith_text) {
            FirstItem firstItem = items.getFirstItem().get(position);
            ((FirstItemViewHolder) holder).itemHomeFirstitemBinding.setFirstItemModel(firstItem);
            ((FirstItemViewHolder) holder).setOnClick(firstItem);

        } else if (getItemViewType(position) == R.id.item_1) {
            Blocks blocks = items.getBlocks().get(position - items.getBlocks().size());
            ((BlocksViewHolder) holder).itemHomeBlocksBinding.setBlocksModel(blocks);
            ((BlocksViewHolder) holder).setOnClick(blocks);

        } else if (getItemViewType(position) == R.id.hadith_text_counter) {
            Counter counter = items.getCounter().get(position - (items.getFirstItem().size() + items.getBlocks().size()));
            ((CounterViewHolder) holder).itemHomeCounterBinding.setCounterModel(counter);
            ((CounterViewHolder) holder).setOnClick();

        } else if (getItemViewType(position) == R.id.hadith_text_kanzhasanat) {
            KanzHasanat kanzHasanat = items.getKanzHasanat().get(position - (items.getFirstItem().size() + items.getBlocks().size() + items.getCounter().size()));
            ((KanzHasanatViewHolder) holder).bind(items.getKanzHasanat());
            ((KanzHasanatViewHolder) holder).setOnClick(kanzHasanat);

        } else if (getItemViewType(position) == R.id.text_tahzeeb_muslim) {
            TahzeebMuslim tahzeebMuslim = items.getTahzeebMuslim().get(position - (items.getFirstItem().size() + items.getBlocks().size() + items.getCounter().size() + items.getKanzHasanat().size()));
            ((TahzeebMuslimViewHolder) holder).itemHomeTahzeebmuslimBinding.setTahzeebMuslimModel(tahzeebMuslim);
            ((TahzeebMuslimViewHolder) holder).setOnClick(tahzeebMuslim);

        } else if (getItemViewType(position) == R.id.daily_image) {
            DailyImage dailyImage = items.getDailyImage().get(position - (items.getFirstItem().size() + items.getBlocks().size() + items.getCounter().size() + items.getKanzHasanat().size() + items.getTahzeebMuslim().size()));
            ((DailyImageViewHolder) holder).itemHomeDailyimageBinding.setDailyImageModel(dailyImage);
            ((DailyImageViewHolder) holder).setOnClick(dailyImage);

        } else if (getItemViewType(position) == R.id.text_saheeh_bukhari) {
            SaheehBukhari saheehBukhari = items.getSaheehBukhari().get(position - (items.getFirstItem().size() + items.getBlocks().size() + items.getCounter().size() + items.getKanzHasanat().size() + items.getTahzeebMuslim().size() + items.getDailyImage().size()));
            ((SaheehBukhariViewHolder) holder).itemHomeSaheehbukhariBinding.setSaheehBukhariModel(saheehBukhari);
            ((SaheehBukhariViewHolder) holder).setOnClick(saheehBukhari);

        } else if (getItemViewType(position) == R.id.live_image) {
            Live live = items.getLive().get(position - (items.getFirstItem().size() + items.getBlocks().size() + items.getCounter().size() + items.getKanzHasanat().size() + items.getTahzeebMuslim().size() + items.getDailyImage().size() + items.getSaheehBukhari().size()));
            ((LiveViewHolder) holder).itemHomeLiveBinding.setLiveModel(live);
            ((LiveViewHolder) holder).setOnClick(live);

        } else if (getItemViewType(position) == R.id.text_saheeh_muslim) {
            SaheehMuslim saheehMuslim = items.getSaheehMuslim().get(position - (items.getFirstItem().size() + items.getBlocks().size() + items.getCounter().size() + items.getKanzHasanat().size() + items.getTahzeebMuslim().size() + items.getDailyImage().size() + items.getSaheehBukhari().size() + items.getLive().size()));
            ((SaheehMuslimViewHolder) holder).itemHomeSaheehmuslimBinding.setSaheehMuslimModel(saheehMuslim);
            ((SaheehMuslimViewHolder) holder).setOnClick(saheehMuslim);
        }
    }

    @Override
    public int getItemCount() {
        if (items == null) return 0;
        return items.getFirstItem().size() + items.getBlocks().size() + items.getCounter().size() + items.getKanzHasanat().size() + items.getTahzeebMuslim().size() + items.getDailyImage().size() + items.getSaheehBukhari().size() + items.getLive().size() + items.getSaheehMuslim().size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && position < items.getFirstItem().size()) {
            return R.id.hadith_text;

        } else if (position == items.getFirstItem().size() && position < items.getFirstItem().size()
                + items.getBlocks().size()) {
            return R.id.item_1;

        } else if (position == items.getFirstItem().size() + items.getBlocks().size() && position < items.getFirstItem().size() + items.getBlocks().size()
                + items.getCounter().size()) {
            return R.id.hadith_text_counter;

        } else if (position == items.getFirstItem().size() + items.getBlocks().size() + items.getCounter().size() && position < items.getFirstItem().size() + items.getBlocks().size()
                + items.getCounter().size() + items.getKanzHasanat().size()) {
            return R.id.hadith_text_kanzhasanat;

        } else if (position == items.getFirstItem().size() + items.getBlocks().size() + items.getCounter().size() + items.getKanzHasanat().size() && position < items.getFirstItem().size() + items.getBlocks().size()
                + items.getCounter().size() + items.getKanzHasanat().size() + items.getTahzeebMuslim().size()) {
            return R.id.text_tahzeeb_muslim;

        } else if (position == items.getFirstItem().size() + items.getBlocks().size() + items.getCounter().size() + items.getKanzHasanat().size() + items.getTahzeebMuslim().size() & position < items.getFirstItem().size() + items.getBlocks().size()
                + items.getCounter().size() + items.getKanzHasanat().size() + items.getTahzeebMuslim().size() + items.getDailyImage().size()) {
            return R.id.daily_image;

        } else if (position == items.getFirstItem().size() + items.getBlocks().size() + items.getCounter().size() + items.getKanzHasanat().size() + items.getTahzeebMuslim().size() + items.getDailyImage().size() & position < items.getFirstItem().size() + items.getBlocks().size()
                + items.getCounter().size() + items.getKanzHasanat().size() + items.getTahzeebMuslim().size() + items.getDailyImage().size() + items.getSaheehBukhari().size()) {
            return R.id.text_saheeh_bukhari;

        } else if (position == items.getFirstItem().size() + items.getBlocks().size() + items.getCounter().size() + items.getKanzHasanat().size() + items.getTahzeebMuslim().size() + items.getDailyImage().size() + items.getSaheehBukhari().size() & position < items.getFirstItem().size() + items.getBlocks().size()
                + items.getCounter().size() + items.getKanzHasanat().size() + items.getTahzeebMuslim().size() + items.getDailyImage().size() + items.getSaheehBukhari().size() + items.getLive().size()) {
            return R.id.live_image;

        } else if (position == items.getFirstItem().size() + items.getBlocks().size() + items.getCounter().size() + items.getKanzHasanat().size() + items.getTahzeebMuslim().size() + items.getDailyImage().size() + items.getSaheehBukhari().size() + items.getLive().size() & position < items.getFirstItem().size() + items.getBlocks().size()
                + items.getCounter().size() + items.getKanzHasanat().size() + items.getTahzeebMuslim().size() + items.getDailyImage().size() + items.getSaheehBukhari().size() + +items.getLive().size() + items.getSaheehMuslim().size()) {
            return R.id.text_saheeh_muslim;

        }
        return R.id.hadith_text;
    }

    static class FirstItemViewHolder extends RecyclerView.ViewHolder {
        ItemHomeFirstitemBinding itemHomeFirstitemBinding;

        FirstItemViewHolder(@NonNull ItemHomeFirstitemBinding itemView) {
            super(itemView.getRoot());
            itemHomeFirstitemBinding = itemView;
        }

        void setOnClick(FirstItem firstItem) {
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), YoutubePlayerActivity.class);
                intent.putExtra("url", firstItem.getUrl());
                itemView.getContext().startActivity(intent);
            });
        }
    }

    static class BlocksViewHolder extends RecyclerView.ViewHolder {
        ItemHomeBlocksBinding itemHomeBlocksBinding;

        BlocksViewHolder(@NonNull ItemHomeBlocksBinding itemView) {
            super(itemView.getRoot());
            itemHomeBlocksBinding = itemView;
        }

        void setOnClick(Blocks blocks) {
            itemHomeBlocksBinding.item1.setOnClickListener(v ->
                    Intents.INSTANCE.openNewActivity(itemView.getContext(), PDFActivity.class));

            itemHomeBlocksBinding.item2.setOnClickListener(v ->
                    Intents.INSTANCE.openNewActivityWithInfo(itemView.getContext(),
                            InfoBlocksActivity.class,
                            Objects.requireNonNull(Objects.requireNonNull(blocks.getItemTwo()).getTitle()),
                            (ArrayList<DataList>) Objects.requireNonNull(blocks.getItemTwo().getDataList())));

            itemHomeBlocksBinding.item3.setOnClickListener(v ->
                    Intents.INSTANCE.openNewActivityWithInfo(itemView.getContext(),
                            InfoBlocksActivity.class,
                            Objects.requireNonNull(Objects.requireNonNull(blocks.getItemThree()).getTitle()),
                            (ArrayList<DataList>) Objects.requireNonNull(blocks.getItemThree().getDataList())));

            itemHomeBlocksBinding.item4.setOnClickListener(v ->
                    Intents.INSTANCE.openNewActivityWithInfo(itemView.getContext(),
                            InfoBlocksActivity.class,
                            Objects.requireNonNull(Objects.requireNonNull(blocks.getItemFour()).getTitle()),
                            (ArrayList<DataList>) Objects.requireNonNull(blocks.getItemFour().getDataList())));
        }
    }

    static class CounterViewHolder extends RecyclerView.ViewHolder {
        ItemHomeCounterBinding itemHomeCounterBinding;

        CounterViewHolder(@NonNull ItemHomeCounterBinding itemView) {
            super(itemView.getRoot());
            itemHomeCounterBinding = itemView;
            Actions.INSTANCE.displayCounter(itemHomeCounterBinding.buttonCounter);
        }

        void setOnClick() {
            itemHomeCounterBinding.buttonCounter.setOnClickListener(v -> {
                Actions.INSTANCE.counter(itemHomeCounterBinding.buttonCounter);
                Actions.INSTANCE.vibrateOnce(itemView.getContext());
            });

            itemView.setOnClickListener(v -> {
                Actions.INSTANCE.counter(itemHomeCounterBinding.buttonCounter);
                Actions.INSTANCE.vibrateOnce(itemView.getContext());
            });

            itemHomeCounterBinding.resetButtonOnClick.setOnClickListener(v -> {
                Actions.INSTANCE.reset(itemHomeCounterBinding.buttonCounter);
                Actions.INSTANCE.vibrateOnce(itemView.getContext());
            });
        }
    }

    static class KanzHasanatViewHolder extends RecyclerView.ViewHolder {
        ItemHomeKanzhasanatBinding itemHomeKanzhasanatBinding;

        KanzHasanatViewHolder(@NonNull ItemHomeKanzhasanatBinding itemView) {
            super(itemView.getRoot());
            itemHomeKanzhasanatBinding = itemView;
        }

        void setOnClick(KanzHasanat kanzHasanat) {
            itemHomeKanzhasanatBinding.copyButtonOnClick.setOnClickListener(v -> {
                textAction.copyText(Objects.requireNonNull(kanzHasanat.getHadith()), itemView.getContext());
                textAction.shareTextSnackbar(itemView.getRootView(), "تم نسخ الحديث", Objects.requireNonNull(kanzHasanat.getHadith()), itemView.getContext());
            });

            itemHomeKanzhasanatBinding.shareButtonOnClick.setOnClickListener(v -> {
                Intents.INSTANCE.copyText(Objects.requireNonNull(kanzHasanat.getHadith()), itemView.getContext());
                Intents.INSTANCE.sharedText(itemView.getContext(), Objects.requireNonNull(kanzHasanat.getHadith()), "تم الإرسال من تطبيق حديث الغروب: لسيرة النبي ﷺ");
            });
        }

        public void bind(List<KanzHasanat> kanzHasanat) {
            itemHomeKanzhasanatBinding.hadithTextKanzhasanat.setText(kanzHasanat.get(getRandomNumber(kanzHasanat.size())).getHadith());
        }

        private int getRandomNumber(int size) {
            return new Random().nextInt(size - 1);
        }
    }

    static class TahzeebMuslimViewHolder extends RecyclerView.ViewHolder {
        ItemHomeTahzeebmuslimBinding itemHomeTahzeebmuslimBinding;

        TahzeebMuslimViewHolder(@NonNull ItemHomeTahzeebmuslimBinding itemView) {
            super(itemView.getRoot());
            itemHomeTahzeebmuslimBinding = itemView;
        }

        void setOnClick(TahzeebMuslim tahzeebMuslim) {
            itemHomeTahzeebmuslimBinding.copyButtonOnClick.setOnClickListener(v -> {
                textAction.copyText(Objects.requireNonNull(tahzeebMuslim.getHadith()), itemView.getContext());
                textAction.shareTextSnackbar(itemView.getRootView(), "تم نسخ الحديث", Objects.requireNonNull(tahzeebMuslim.getHadith()), itemView.getContext());
            });

            itemHomeTahzeebmuslimBinding.shareButtonOnClick.setOnClickListener(v -> {
                Intents.INSTANCE.copyText(Objects.requireNonNull(tahzeebMuslim.getHadith()), itemView.getContext());
                Intents.INSTANCE.sharedText(itemView.getContext(), Objects.requireNonNull(tahzeebMuslim.getHadith()), "تم الإرسال من تطبيق حديث الغروب: أحاديث النبي ﷺ");
            });
        }
    }

    static class DailyImageViewHolder extends RecyclerView.ViewHolder {
        ItemHomeDailyimageBinding itemHomeDailyimageBinding;

        DailyImageViewHolder(@NonNull ItemHomeDailyimageBinding itemView) {
            super(itemView.getRoot());
            itemHomeDailyimageBinding = itemView;
        }

        void setOnClick(DailyImage images) {
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), DisplayImageActivity.class);
                intent.putExtra("imageUrl", images.getImage());
                itemView.getContext().startActivity(intent);
            });
        }
    }

    static class SaheehBukhariViewHolder extends RecyclerView.ViewHolder {
        ItemHomeSaheehbukhariBinding itemHomeSaheehbukhariBinding;

        SaheehBukhariViewHolder(@NonNull ItemHomeSaheehbukhariBinding itemView) {
            super(itemView.getRoot());
            itemHomeSaheehbukhariBinding = itemView;
        }

        void setOnClick(SaheehBukhari saheehBukhari) {
            itemHomeSaheehbukhariBinding.copyButtonOnClick.setOnClickListener(v -> {
                textAction.copyText(Objects.requireNonNull(saheehBukhari.getHadith()), itemView.getContext());
                textAction.shareTextSnackbar(itemView.getRootView(), "تم نسخ الحديث", Objects.requireNonNull(saheehBukhari.getHadith()), itemView.getContext());
            });

            itemHomeSaheehbukhariBinding.shareButtonOnClick.setOnClickListener(v -> {
                Intents.INSTANCE.copyText(Objects.requireNonNull(saheehBukhari.getHadith()), itemView.getContext());
                Intents.INSTANCE.sharedText(itemView.getContext(), Objects.requireNonNull(saheehBukhari.getHadith()), "تم الإرسال من تطبيق حديث الغروب: أحاديث النبي ﷺ");
            });
        }
    }

    static class LiveViewHolder extends RecyclerView.ViewHolder {
        ItemHomeLiveBinding itemHomeLiveBinding;

        LiveViewHolder(@NonNull ItemHomeLiveBinding itemView) {
            super(itemView.getRoot());
            itemHomeLiveBinding = itemView;
        }

        void setOnClick(Live live) {
            itemHomeLiveBinding.copyButtonOnClick.setOnClickListener(v -> {
                textAction.copyText(Objects.requireNonNull(live.getText()), itemView.getContext());
                textAction.shareTextSnackbar(itemView.getRootView(), "تم نسخ الحديث", Objects.requireNonNull(live.getText()), itemView.getContext());
            });

            itemHomeLiveBinding.shareButtonOnClick.setOnClickListener(v -> {
                Intents.INSTANCE.copyText(Objects.requireNonNull(live.getText()), itemView.getContext());
                Intents.INSTANCE.sharedText(itemView.getContext(), Objects.requireNonNull(live.getText()), "تم الإرسال من تطبيق حديث الغروب: أحاديث النبي ﷺ");
            });
        }
    }

    static class SaheehMuslimViewHolder extends RecyclerView.ViewHolder {
        ItemHomeSaheehmuslimBinding itemHomeSaheehmuslimBinding;

        SaheehMuslimViewHolder(@NonNull ItemHomeSaheehmuslimBinding itemView) {
            super(itemView.getRoot());
            itemHomeSaheehmuslimBinding = itemView;
        }

        void setOnClick(SaheehMuslim saheehMuslim) {
            itemHomeSaheehmuslimBinding.copyButtonOnClick.setOnClickListener(v -> {
                textAction.copyText(Objects.requireNonNull(saheehMuslim.getHadith()), itemView.getContext());
                textAction.shareTextSnackbar(itemView.getRootView(), "تم نسخ الحديث", Objects.requireNonNull(saheehMuslim.getHadith()), itemView.getContext());
            });

            itemHomeSaheehmuslimBinding.shareButtonOnClick.setOnClickListener(v -> {
                Intents.INSTANCE.copyText(Objects.requireNonNull(saheehMuslim.getHadith()), itemView.getContext());
                Intents.INSTANCE.sharedText(itemView.getContext(), Objects.requireNonNull(saheehMuslim.getHadith()), "تم الإرسال من تطبيق حديث الغروب: أحاديث النبي ﷺ");
            });
        }
    }
}

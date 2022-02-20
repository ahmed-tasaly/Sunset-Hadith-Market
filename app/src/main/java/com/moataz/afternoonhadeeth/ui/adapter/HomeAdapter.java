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
import com.moataz.afternoonhadeeth.data.model.home.HadithChanges;
import com.moataz.afternoonhadeeth.data.model.home.HomeResponse;
import com.moataz.afternoonhadeeth.data.model.home.TextChanges;
import com.moataz.afternoonhadeeth.data.model.home.Videos;
import com.moataz.afternoonhadeeth.data.model.home.blocks.Blocks;
import com.moataz.afternoonhadeeth.data.model.home.blocks.DataList;
import com.moataz.afternoonhadeeth.databinding.ItemHomeBlocksBinding;
import com.moataz.afternoonhadeeth.databinding.ItemHomeCounterBinding;
import com.moataz.afternoonhadeeth.databinding.ItemHomeFirstHadithChangesBinding;
import com.moataz.afternoonhadeeth.databinding.ItemHomeFirstTextChangesBinding;
import com.moataz.afternoonhadeeth.databinding.ItemHomeFourthHadithChangesBinding;
import com.moataz.afternoonhadeeth.databinding.ItemHomeSecondHadithChangesBinding;
import com.moataz.afternoonhadeeth.databinding.ItemHomeSecondTextChangesBinding;
import com.moataz.afternoonhadeeth.databinding.ItemHomeThirdHadithChangesBinding;
import com.moataz.afternoonhadeeth.databinding.ItemHomeVideosBinding;
import com.moataz.afternoonhadeeth.ui.view.activity.InfoBlocksActivity;
import com.moataz.afternoonhadeeth.ui.view.activity.PDFActivity;
import com.moataz.afternoonhadeeth.ui.view.activity.YoutubePlayerActivity;
import com.moataz.afternoonhadeeth.utils.helper.CounterActions;
import com.moataz.afternoonhadeeth.utils.helper.Intents;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int KANZ_HASANAT_DISPLAYED_COUNT = 1;
    private static final int FIRST_ITEM_DISPLAYED_COUNT = 1;
    private static final int COUNTER_DISPLAYED_COUNT = 1;
    private static final int TAHZEEB_MUSLIM_DISPLAYED_COUNT = 1;
    private static final int DAILY_IMAGE_DISPLAYED_COUNT = 1;
    private static final int SAHEEH_BUKHARI_DISPLAYED_COUNT = 1;
    private static final int SAHEEH_MUSLIM_DISPLAYED_COUNT = 1;
    private static final int LIVE_DISPLAYED_COUNT = 1;
    private HomeResponse items = null;
    private static final CounterActions counter = new CounterActions();
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
            return new VideosViewHolder(
                    DataBindingUtil.inflate(
                            LayoutInflater.from(parent.getContext()),
                            R.layout.item_home_videos,
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
            return new FirstHadithChangesViewHolder(
                    DataBindingUtil.inflate(
                            LayoutInflater.from(parent.getContext()),
                            R.layout.item_home_first_hadith_changes,
                            parent,
                            false
                    )
            );
        } else if (viewType == R.id.text_tahzeeb_muslim) {
            return new FirstTextChangesViewHolder(
                    DataBindingUtil.inflate(
                            LayoutInflater.from(parent.getContext()),
                            R.layout.item_home_first_text_changes,
                            parent,
                            false
                    )
            );
        } else if (viewType == R.id.daily_image) {
            return new SecondHadithChangesViewHolder(
                    DataBindingUtil.inflate(
                            LayoutInflater.from(parent.getContext()),
                            R.layout.item_home_second_hadith_changes,
                            parent,
                            false
                    )
            );
        } else if (viewType == R.id.text_saheeh_bukhari) {
            return new ThirdHadithChangesViewHolder(
                    DataBindingUtil.inflate(
                            LayoutInflater.from(parent.getContext()),
                            R.layout.item_home_third_hadith_changes,
                            parent,
                            false
                    )
            );
        } else if (viewType == R.id.live_image) {
            return new SecondTextChangesViewHolder(
                    DataBindingUtil.inflate(
                            LayoutInflater.from(parent.getContext()),
                            R.layout.item_home_second_text_changes,
                            parent,
                            false
                    )
            );
        } else if (viewType == R.id.text_saheeh_muslim) {
            return new FourthHadithChangesViewHolder(
                    DataBindingUtil.inflate(
                            LayoutInflater.from(parent.getContext()),
                            R.layout.item_home_fourth_hadith_changes,
                            parent,
                            false
                    )
            );
        } else throw new IllegalArgumentException("unknown view type");
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == R.id.hadith_text) {
            Videos videos = items.getVideos().get(getRandomNumber(items.getVideos().size()));
            ((VideosViewHolder) holder).itemHomeVideosBinding.setVideosModel(videos);
            ((VideosViewHolder) holder).setOnClick(videos);

        } else if (getItemViewType(position) == R.id.item_1) {
            Blocks blocks = items.getBlocks().get(position - items.getBlocks().size());
            ((BlocksViewHolder) holder).itemHomeBlocksBinding.setBlocksModel(blocks);
            ((BlocksViewHolder) holder).setOnClick(blocks);

        } else if (getItemViewType(position) == R.id.hadith_text_kanzhasanat) {
            HadithChanges firstHadithChanges = items.getFirstHadithChanges().get(getRandomNumber(items.getFirstHadithChanges().size()));
            ((FirstHadithChangesViewHolder) holder).changeText(items.getFirstHadithChanges());
            ((FirstHadithChangesViewHolder) holder).setOnClick();

        } else if (getItemViewType(position) == R.id.hadith_text_counter) {
            Counter counter = items.getCounter().get(getRandomNumber(items.getCounter().size()));
            ((CounterViewHolder) holder).itemHomeCounterBinding.setCounterModel(counter);
            ((CounterViewHolder) holder).setOnClick();

        } else if (getItemViewType(position) == R.id.text_tahzeeb_muslim) {
            TextChanges firstTextChanges = items.getFirstTextChanges().get(getRandomNumber(items.getFirstTextChanges().size()));
            ((FirstTextChangesViewHolder) holder).itemHomeFirstTextChangesBinding.setFirstTextChangesModel(firstTextChanges);
            ((FirstTextChangesViewHolder) holder).changeText(items.getFirstTextChanges());
            ((FirstTextChangesViewHolder) holder).setOnClick();

        } else if (getItemViewType(position) == R.id.daily_image) {
            HadithChanges secondHadithChanges = items.getSecondHadithChanges().get(getRandomNumber(items.getSecondHadithChanges().size()));
            ((SecondHadithChangesViewHolder) holder).itemHomeSecondHadithChangesBinding.setSecondHadithChangesModel(secondHadithChanges);
            ((SecondHadithChangesViewHolder) holder).changeText(items.getSecondHadithChanges());
            ((SecondHadithChangesViewHolder) holder).setOnClick();

        } else if (getItemViewType(position) == R.id.text_saheeh_bukhari) {
            HadithChanges thirdHadithChanges = items.getThirdHadithChanges().get(getRandomNumber(items.getThirdHadithChanges().size()));
            ((ThirdHadithChangesViewHolder) holder).itemHomeThirdHadithChangesBinding.setThirdHadithChangesModel(thirdHadithChanges);
            ((ThirdHadithChangesViewHolder) holder).changeText(items.getThirdHadithChanges());
            ((ThirdHadithChangesViewHolder) holder).setOnClick();

        } else if (getItemViewType(position) == R.id.live_image) {
            TextChanges secondTextChanges = items.getSecondTextChanges().get(getRandomNumber(items.getSecondTextChanges().size()));
            ((SecondTextChangesViewHolder) holder).itemHomeSecondTextChangesBinding.setSecondTextChangesModel(secondTextChanges);
            ((SecondTextChangesViewHolder) holder).changeText(items.getSecondTextChanges());
            ((SecondTextChangesViewHolder) holder).setOnClick();

        } else if (getItemViewType(position) == R.id.text_saheeh_muslim) {
            HadithChanges saheehMuslim = items.getFourthHadithChanges().get(getRandomNumber(items.getFourthHadithChanges().size()));
            ((FourthHadithChangesViewHolder) holder).itemHomeFourthHadithChangesBinding.setFourthHadithChangesModel(saheehMuslim);
            ((FourthHadithChangesViewHolder) holder).changeText(items.getFourthHadithChanges());
            ((FourthHadithChangesViewHolder) holder).setOnClick();
        }
    }

    public static int getRandomNumber(int size) {
        return new Random().nextInt(size);
    }

    @Override
    public int getItemCount() {
        if (items == null) return 0;
        return FIRST_ITEM_DISPLAYED_COUNT
                + items.getBlocks().size()
                + KANZ_HASANAT_DISPLAYED_COUNT
                + COUNTER_DISPLAYED_COUNT
                + DAILY_IMAGE_DISPLAYED_COUNT
                + TAHZEEB_MUSLIM_DISPLAYED_COUNT
                + SAHEEH_BUKHARI_DISPLAYED_COUNT
                + LIVE_DISPLAYED_COUNT
                + SAHEEH_MUSLIM_DISPLAYED_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return R.id.hadith_text;
        } else if (position == FIRST_ITEM_DISPLAYED_COUNT
                && position < FIRST_ITEM_DISPLAYED_COUNT + items.getBlocks().size()) {
            return R.id.item_1;

        } else if (position == FIRST_ITEM_DISPLAYED_COUNT + items.getBlocks().size()
                && position < FIRST_ITEM_DISPLAYED_COUNT + items.getBlocks().size() + KANZ_HASANAT_DISPLAYED_COUNT) {
            return R.id.hadith_text_kanzhasanat;

        } else if (position == FIRST_ITEM_DISPLAYED_COUNT + items.getBlocks().size() + COUNTER_DISPLAYED_COUNT
                && position < FIRST_ITEM_DISPLAYED_COUNT + items.getBlocks().size() + KANZ_HASANAT_DISPLAYED_COUNT + COUNTER_DISPLAYED_COUNT) {
            return R.id.hadith_text_counter;

        } else if (position == FIRST_ITEM_DISPLAYED_COUNT + items.getBlocks().size() + KANZ_HASANAT_DISPLAYED_COUNT + COUNTER_DISPLAYED_COUNT
                && position < FIRST_ITEM_DISPLAYED_COUNT + items.getBlocks().size() + KANZ_HASANAT_DISPLAYED_COUNT + COUNTER_DISPLAYED_COUNT + DAILY_IMAGE_DISPLAYED_COUNT) {
            return R.id.daily_image;

        } else if (position == FIRST_ITEM_DISPLAYED_COUNT + items.getBlocks().size() + KANZ_HASANAT_DISPLAYED_COUNT + COUNTER_DISPLAYED_COUNT + DAILY_IMAGE_DISPLAYED_COUNT
                && position < FIRST_ITEM_DISPLAYED_COUNT + items.getBlocks().size() + KANZ_HASANAT_DISPLAYED_COUNT + COUNTER_DISPLAYED_COUNT + DAILY_IMAGE_DISPLAYED_COUNT + TAHZEEB_MUSLIM_DISPLAYED_COUNT) {
            return R.id.text_tahzeeb_muslim;

        } else if (position == FIRST_ITEM_DISPLAYED_COUNT + items.getBlocks().size() + KANZ_HASANAT_DISPLAYED_COUNT + COUNTER_DISPLAYED_COUNT + DAILY_IMAGE_DISPLAYED_COUNT + TAHZEEB_MUSLIM_DISPLAYED_COUNT
                && position < FIRST_ITEM_DISPLAYED_COUNT + items.getBlocks().size() + KANZ_HASANAT_DISPLAYED_COUNT + COUNTER_DISPLAYED_COUNT + DAILY_IMAGE_DISPLAYED_COUNT + TAHZEEB_MUSLIM_DISPLAYED_COUNT + SAHEEH_BUKHARI_DISPLAYED_COUNT) {
            return R.id.text_saheeh_bukhari;

        } else if (position == FIRST_ITEM_DISPLAYED_COUNT + items.getBlocks().size() + KANZ_HASANAT_DISPLAYED_COUNT + COUNTER_DISPLAYED_COUNT + DAILY_IMAGE_DISPLAYED_COUNT + TAHZEEB_MUSLIM_DISPLAYED_COUNT + SAHEEH_BUKHARI_DISPLAYED_COUNT
                && position < FIRST_ITEM_DISPLAYED_COUNT + items.getBlocks().size() + KANZ_HASANAT_DISPLAYED_COUNT + COUNTER_DISPLAYED_COUNT + DAILY_IMAGE_DISPLAYED_COUNT + TAHZEEB_MUSLIM_DISPLAYED_COUNT + SAHEEH_BUKHARI_DISPLAYED_COUNT + LIVE_DISPLAYED_COUNT) {
            return R.id.live_image;

        } else if (position == FIRST_ITEM_DISPLAYED_COUNT + items.getBlocks().size() + KANZ_HASANAT_DISPLAYED_COUNT + COUNTER_DISPLAYED_COUNT + DAILY_IMAGE_DISPLAYED_COUNT + TAHZEEB_MUSLIM_DISPLAYED_COUNT + SAHEEH_BUKHARI_DISPLAYED_COUNT + LIVE_DISPLAYED_COUNT
                && position < FIRST_ITEM_DISPLAYED_COUNT + items.getBlocks().size() + KANZ_HASANAT_DISPLAYED_COUNT + COUNTER_DISPLAYED_COUNT + DAILY_IMAGE_DISPLAYED_COUNT + TAHZEEB_MUSLIM_DISPLAYED_COUNT + SAHEEH_BUKHARI_DISPLAYED_COUNT + +LIVE_DISPLAYED_COUNT + SAHEEH_MUSLIM_DISPLAYED_COUNT) {
            return R.id.text_saheeh_muslim;

        }
        return R.id.hadith_text;
    }

    static class VideosViewHolder extends RecyclerView.ViewHolder {
        ItemHomeVideosBinding itemHomeVideosBinding;

        VideosViewHolder(@NonNull ItemHomeVideosBinding itemView) {
            super(itemView.getRoot());
            itemHomeVideosBinding = itemView;
        }

        void setOnClick(Videos videos) {
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), YoutubePlayerActivity.class);
                intent.putExtra("url", videos.getUrl());
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

    static class FirstHadithChangesViewHolder extends RecyclerView.ViewHolder {
        ItemHomeFirstHadithChangesBinding itemHomeFirstHadithChangesBinding;

        FirstHadithChangesViewHolder(@NonNull ItemHomeFirstHadithChangesBinding itemView) {
            super(itemView.getRoot());
            itemHomeFirstHadithChangesBinding = itemView;
        }

        void setOnClick() {
            itemHomeFirstHadithChangesBinding.copyButtonOnClick.setOnClickListener(v -> {
                textAction.copyText(itemHomeFirstHadithChangesBinding.hadithTextKanzhasanat.getText().toString(), itemView.getContext());
                textAction.shareTextSnackbar(itemView.getRootView(), "تم نسخ الحديث", itemHomeFirstHadithChangesBinding.hadithTextKanzhasanat.getText().toString(), itemView.getContext());
            });

            itemHomeFirstHadithChangesBinding.shareButtonOnClick.setOnClickListener(v -> {
                Intents.INSTANCE.copyText(itemHomeFirstHadithChangesBinding.hadithTextKanzhasanat.getText().toString(), itemView.getContext());
                Intents.INSTANCE.sharedText(itemView.getContext(), itemHomeFirstHadithChangesBinding.hadithTextKanzhasanat.getText().toString(), "تم الإرسال من تطبيق حديث الغروب: لسيرة النبي ﷺ");
            });
        }

        void changeText(@NonNull List<HadithChanges> firstHadithChanges) {
            itemHomeFirstHadithChangesBinding.hadithTextKanzhasanat.setText(firstHadithChanges.get(getRandomNumber(firstHadithChanges.size())).getHadith());

            itemHomeFirstHadithChangesBinding.changeButtonOnClick.setOnClickListener(v -> {
                itemHomeFirstHadithChangesBinding.hadithTextKanzhasanat.setText(firstHadithChanges.get(getRandomNumber(firstHadithChanges.size())).getHadith());
                counter.vibrateOnce(itemView.getContext());
            });
            itemHomeFirstHadithChangesBinding.hadithTextKanzhasanat.setOnClickListener(v -> {
                itemHomeFirstHadithChangesBinding.hadithTextKanzhasanat.setText(firstHadithChanges.get(getRandomNumber(firstHadithChanges.size())).getHadith());
                counter.vibrateOnce(itemView.getContext());
            });
        }
    }

    static class CounterViewHolder extends RecyclerView.ViewHolder {
        ItemHomeCounterBinding itemHomeCounterBinding;

        CounterViewHolder(@NonNull ItemHomeCounterBinding itemView) {
            super(itemView.getRoot());
            itemHomeCounterBinding = itemView;
            counter.displayCounter(itemHomeCounterBinding.buttonCounter);
        }

        void setOnClick() {
            itemHomeCounterBinding.buttonCounter.setOnClickListener(v -> {
                counter.addCounter(itemHomeCounterBinding.buttonCounter);
                counter.vibrateOnce(itemView.getContext());
            });

            itemView.setOnClickListener(v -> {
                counter.addCounter(itemHomeCounterBinding.buttonCounter);
                counter.vibrateOnce(itemView.getContext());
            });

            itemHomeCounterBinding.resetButtonOnClick.setOnClickListener(v -> {
                counter.resetCounter(itemHomeCounterBinding.buttonCounter);
                counter.vibrateOnce(itemView.getContext());
            });
        }
    }

    static class SecondHadithChangesViewHolder extends RecyclerView.ViewHolder {
        ItemHomeSecondHadithChangesBinding itemHomeSecondHadithChangesBinding;

        SecondHadithChangesViewHolder(@NonNull ItemHomeSecondHadithChangesBinding itemView) {
            super(itemView.getRoot());
            itemHomeSecondHadithChangesBinding = itemView;
        }

        void setOnClick() {
            itemHomeSecondHadithChangesBinding.copyButtonOnClick.setOnClickListener(v -> {
                textAction.copyText(itemHomeSecondHadithChangesBinding.dailyImage.getText().toString(), itemView.getContext());
                textAction.shareTextSnackbar(itemView.getRootView(), "تم نسخ الحديث", itemHomeSecondHadithChangesBinding.dailyImage.getText().toString(), itemView.getContext());
            });

            itemHomeSecondHadithChangesBinding.shareButtonOnClick.setOnClickListener(v -> {
                Intents.INSTANCE.copyText(itemHomeSecondHadithChangesBinding.dailyImage.getText().toString(), itemView.getContext());
                Intents.INSTANCE.sharedText(itemView.getContext(), itemHomeSecondHadithChangesBinding.dailyImage.getText().toString(), "تم الإرسال من تطبيق حديث الغروب: لسيرة النبي ﷺ");
            });
        }

        void changeText(@NonNull List<HadithChanges> secondHadithChanges) {
            itemHomeSecondHadithChangesBinding.dailyImage.setText(secondHadithChanges.get(getRandomNumber(secondHadithChanges.size())).getHadith());

            itemHomeSecondHadithChangesBinding.changeButtonOnClick.setOnClickListener(v -> {
                itemHomeSecondHadithChangesBinding.dailyImage.setText(secondHadithChanges.get(getRandomNumber(secondHadithChanges.size())).getHadith());
                counter.vibrateOnce(itemView.getContext());
            });
            itemHomeSecondHadithChangesBinding.dailyImage.setOnClickListener(v -> {
                itemHomeSecondHadithChangesBinding.dailyImage.setText(secondHadithChanges.get(getRandomNumber(secondHadithChanges.size())).getHadith());
                counter.vibrateOnce(itemView.getContext());
            });
        }
    }

    static class FirstTextChangesViewHolder extends RecyclerView.ViewHolder {
        ItemHomeFirstTextChangesBinding itemHomeFirstTextChangesBinding;

        FirstTextChangesViewHolder(@NonNull ItemHomeFirstTextChangesBinding itemView) {
            super(itemView.getRoot());
            itemHomeFirstTextChangesBinding = itemView;
        }

        void changeText(@NonNull List<TextChanges> firstTextChanges) {
            itemHomeFirstTextChangesBinding.textTahzeebMuslim.setText(firstTextChanges.get(getRandomNumber(firstTextChanges.size())).getText());

            itemHomeFirstTextChangesBinding.changeText.setOnClickListener(v -> {
                itemHomeFirstTextChangesBinding.textTahzeebMuslim.setText(firstTextChanges.get(getRandomNumber(firstTextChanges.size())).getText());
                counter.vibrateOnce(itemView.getContext());
            });
            itemHomeFirstTextChangesBinding.textTahzeebMuslim.setOnClickListener(v -> {
                itemHomeFirstTextChangesBinding.textTahzeebMuslim.setText(firstTextChanges.get(getRandomNumber(firstTextChanges.size())).getText());
                counter.vibrateOnce(itemView.getContext());
            });
        }

        void setOnClick() {
            itemHomeFirstTextChangesBinding.shareButtonOnClick.setOnClickListener(v -> {
                Intents.INSTANCE.copyText(itemHomeFirstTextChangesBinding.textTahzeebMuslim.getText().toString(), itemView.getContext());
                Intents.INSTANCE.sharedText(itemView.getContext(), itemHomeFirstTextChangesBinding.textTahzeebMuslim.getText().toString(), "تم الإرسال من تطبيق حديث الغروب: سيرة النبي ﷺ");
            });
        }
    }

    static class ThirdHadithChangesViewHolder extends RecyclerView.ViewHolder {
        ItemHomeThirdHadithChangesBinding itemHomeThirdHadithChangesBinding;

        ThirdHadithChangesViewHolder(@NonNull ItemHomeThirdHadithChangesBinding itemView) {
            super(itemView.getRoot());
            itemHomeThirdHadithChangesBinding = itemView;
        }

        void setOnClick() {
            itemHomeThirdHadithChangesBinding.copyButtonOnClick.setOnClickListener(v -> {
                textAction.copyText(itemHomeThirdHadithChangesBinding.hadithText.getText().toString(), itemView.getContext());
                textAction.shareTextSnackbar(itemView.getRootView(), "تم نسخ الحديث", itemHomeThirdHadithChangesBinding.hadithText.getText().toString(), itemView.getContext());
            });

            itemHomeThirdHadithChangesBinding.shareButtonOnClick.setOnClickListener(v -> {
                Intents.INSTANCE.copyText(itemHomeThirdHadithChangesBinding.hadithText.getText().toString(), itemView.getContext());
                Intents.INSTANCE.sharedText(itemView.getContext(), itemHomeThirdHadithChangesBinding.hadithText.getText().toString(), "تم الإرسال من تطبيق حديث الغروب: لسيرة النبي ﷺ");
            });
        }

        void changeText(@NonNull List<HadithChanges> thirdHadithChanges) {
            itemHomeThirdHadithChangesBinding.hadithText.setText(thirdHadithChanges.get(getRandomNumber(thirdHadithChanges.size())).getHadith());

            itemHomeThirdHadithChangesBinding.changeButtonOnClick.setOnClickListener(v -> {
                itemHomeThirdHadithChangesBinding.hadithText.setText(thirdHadithChanges.get(getRandomNumber(thirdHadithChanges.size())).getHadith());
                counter.vibrateOnce(itemView.getContext());
            });
            itemHomeThirdHadithChangesBinding.hadithText.setOnClickListener(v -> {
                itemHomeThirdHadithChangesBinding.hadithText.setText(thirdHadithChanges.get(getRandomNumber(thirdHadithChanges.size())).getHadith());
                counter.vibrateOnce(itemView.getContext());
            });
        }
    }

    static class SecondTextChangesViewHolder extends RecyclerView.ViewHolder {
        ItemHomeSecondTextChangesBinding itemHomeSecondTextChangesBinding;

        SecondTextChangesViewHolder(@NonNull ItemHomeSecondTextChangesBinding itemView) {
            super(itemView.getRoot());
            itemHomeSecondTextChangesBinding = itemView;
        }

        public void changeText(@NonNull List<TextChanges> secondTextChanges) {
            itemHomeSecondTextChangesBinding.liveImage.setText(secondTextChanges.get(getRandomNumber(secondTextChanges.size())).getText());

            itemHomeSecondTextChangesBinding.changeText.setOnClickListener(v -> {
                itemHomeSecondTextChangesBinding.liveImage.setText(secondTextChanges.get(getRandomNumber(secondTextChanges.size())).getText());
                counter.vibrateOnce(itemView.getContext());
            });
            itemHomeSecondTextChangesBinding.liveImage.setOnClickListener(v -> {
                itemHomeSecondTextChangesBinding.liveImage.setText(secondTextChanges.get(getRandomNumber(secondTextChanges.size())).getText());
                counter.vibrateOnce(itemView.getContext());
            });
        }

        void setOnClick() {
            itemHomeSecondTextChangesBinding.shareButtonOnClick.setOnClickListener(v -> {
                Intents.INSTANCE.copyText(itemHomeSecondTextChangesBinding.liveImage.getText().toString(), itemView.getContext());
                Intents.INSTANCE.sharedText(itemView.getContext(), itemHomeSecondTextChangesBinding.liveImage.getText().toString(), "تم الإرسال من تطبيق حديث الغروب: سيرة النبي ﷺ");
            });
        }
    }

    static class FourthHadithChangesViewHolder extends RecyclerView.ViewHolder {
        ItemHomeFourthHadithChangesBinding itemHomeFourthHadithChangesBinding;

        FourthHadithChangesViewHolder(@NonNull ItemHomeFourthHadithChangesBinding itemView) {
            super(itemView.getRoot());
            itemHomeFourthHadithChangesBinding = itemView;
        }

        void setOnClick() {
            itemHomeFourthHadithChangesBinding.copyButtonOnClick.setOnClickListener(v -> {
                textAction.copyText(itemHomeFourthHadithChangesBinding.hadithText.getText().toString(), itemView.getContext());
                textAction.shareTextSnackbar(itemView.getRootView(), "تم نسخ الحديث", itemHomeFourthHadithChangesBinding.hadithText.getText().toString(), itemView.getContext());
            });

            itemHomeFourthHadithChangesBinding.shareButtonOnClick.setOnClickListener(v -> {
                Intents.INSTANCE.copyText(itemHomeFourthHadithChangesBinding.hadithText.getText().toString(), itemView.getContext());
                Intents.INSTANCE.sharedText(itemView.getContext(), itemHomeFourthHadithChangesBinding.hadithText.getText().toString(), "تم الإرسال من تطبيق حديث الغروب: لسيرة النبي ﷺ");
            });
        }

        public void changeText(@NonNull List<HadithChanges> saheehMuslim) {
            itemHomeFourthHadithChangesBinding.hadithText.setText(saheehMuslim.get(getRandomNumber(saheehMuslim.size())).getHadith());

            itemHomeFourthHadithChangesBinding.changeButtonOnClick.setOnClickListener(v -> {
                itemHomeFourthHadithChangesBinding.hadithText.setText(saheehMuslim.get(getRandomNumber(saheehMuslim.size())).getHadith());
                counter.vibrateOnce(itemView.getContext());
            });
            itemHomeFourthHadithChangesBinding.hadithText.setOnClickListener(v -> {
                itemHomeFourthHadithChangesBinding.hadithText.setText(saheehMuslim.get(getRandomNumber(saheehMuslim.size())).getHadith());
                counter.vibrateOnce(itemView.getContext());
            });
        }
    }
}

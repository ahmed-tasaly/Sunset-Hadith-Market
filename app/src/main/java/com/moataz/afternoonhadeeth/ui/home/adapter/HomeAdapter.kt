package com.moataz.afternoonhadeeth.ui.home.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.moataz.afternoonhadeeth.R
import com.moataz.afternoonhadeeth.data.model.home.HadithChanges
import com.moataz.afternoonhadeeth.data.model.home.HomeResponse
import com.moataz.afternoonhadeeth.data.model.home.TextChanges
import com.moataz.afternoonhadeeth.data.model.home.Videos
import com.moataz.afternoonhadeeth.data.model.home.blocks.Blocks
import com.moataz.afternoonhadeeth.data.model.home.blocks.DataList
import com.moataz.afternoonhadeeth.databinding.*
import com.moataz.afternoonhadeeth.ui.home.view.activity.DisplayHomeBlocksActivity
import com.moataz.afternoonhadeeth.ui.home.view.activity.PDFActivity
import com.moataz.afternoonhadeeth.ui.home.view.activity.YoutubePlayerActivity
import com.moataz.afternoonhadeeth.utils.helper.CounterActions
import com.moataz.afternoonhadeeth.utils.helper.Intents
import com.moataz.afternoonhadeeth.utils.helper.Intents.copyText
import com.moataz.afternoonhadeeth.utils.helper.Intents.openNewActivity
import com.moataz.afternoonhadeeth.utils.helper.Intents.openNewActivityWithInfo
import com.moataz.afternoonhadeeth.utils.helper.Intents.sharedText
import java.util.*

class HomeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: HomeResponse? = null
    private val KANZ_HASANAT_DISPLAYED_COUNT = 1
    private val FIRST_ITEM_DISPLAYED_COUNT = 1
    private val COUNTER_DISPLAYED_COUNT = 1
    private val TAHZEEB_MUSLIM_DISPLAYED_COUNT = 1
    private val DAILY_IMAGE_DISPLAYED_COUNT = 1
    private val SAHEEH_BUKHARI_DISPLAYED_COUNT = 1
    private val SAHEEH_MUSLIM_DISPLAYED_COUNT = 1
    private val LIVE_DISPLAYED_COUNT = 1
    private val counter = CounterActions()
    private val textAction = Intents

    @SuppressLint("NotifyDataSetChanged")
    fun setHomeList(items: HomeResponse?) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == R.id.hadith_text) {
            VideosViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_home_videos,
                    parent,
                    false
                )
            )
        } else if (viewType == R.id.item_1) {
            BlocksViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_home_blocks,
                    parent,
                    false
                )
            )
        } else if (viewType == R.id.hadith_text_counter) {
            CounterViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_home_counter,
                    parent,
                    false
                )
            )
        } else if (viewType == R.id.hadith_text_kanzhasanat) {
            FirstHadithChangesViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_home_first_hadith_changes,
                    parent,
                    false
                )
            )
        } else if (viewType == R.id.text_tahzeeb_muslim) {
            FirstTextChangesViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_home_first_text_changes,
                    parent,
                    false
                )
            )
        } else if (viewType == R.id.daily_image) {
            SecondHadithChangesViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_home_second_hadith_changes,
                    parent,
                    false
                )
            )
        } else if (viewType == R.id.text_saheeh_bukhari) {
            ThirdHadithChangesViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_home_third_hadith_changes,
                    parent,
                    false
                )
            )
        } else if (viewType == R.id.live_image) {
            SecondTextChangesViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_home_second_text_changes,
                    parent,
                    false
                )
            )
        } else if (viewType == R.id.text_saheeh_muslim) {
            FourthHadithChangesViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_home_fourth_hadith_changes,
                    parent,
                    false
                )
            )
        } else throw IllegalArgumentException("unknown view type")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == R.id.hadith_text) {
            val videos = items!!.videos[getRandomNumber(items!!.videos.size)]
            (holder as VideosViewHolder).itemHomeVideosBinding.videosModel = videos
            holder.setOnClick(videos)

        } else if (getItemViewType(position) == R.id.item_1) {
            val blocks = items!!.blocks[position - items!!.blocks.size]
            (holder as BlocksViewHolder).itemHomeBlocksBinding.blocksModel = blocks
            holder.setOnClick(blocks)

        } else if (getItemViewType(position) == R.id.hadith_text_kanzhasanat) {
            val (hadith) = items!!.firstHadithChanges[getRandomNumber(
                items!!.firstHadithChanges.size
            )]
            (holder as FirstHadithChangesViewHolder).changeText(items!!.firstHadithChanges)
            holder.setOnClick()

        } else if (getItemViewType(position) == R.id.hadith_text_counter) {
            val counter = items!!.counter[getRandomNumber(
                items!!.counter.size
            )]
            (holder as CounterViewHolder).itemHomeCounterBinding.counterModel = counter
            holder.setOnClick()

        } else if (getItemViewType(position) == R.id.text_tahzeeb_muslim) {
            val firstTextChanges = items!!.firstTextChanges[getRandomNumber(
                items!!.firstTextChanges.size
            )]
            (holder as FirstTextChangesViewHolder).itemHomeFirstTextChangesBinding.firstTextChangesModel =
                firstTextChanges
            holder.changeText(items!!.firstTextChanges)
            holder.setOnClick()

        } else if (getItemViewType(position) == R.id.daily_image) {
            val secondHadithChanges = items!!.secondHadithChanges[getRandomNumber(
                items!!.secondHadithChanges.size
            )]
            (holder as SecondHadithChangesViewHolder).itemHomeSecondHadithChangesBinding.secondHadithChangesModel =
                secondHadithChanges
            holder.changeText(items!!.secondHadithChanges)
            holder.setOnClick()

        } else if (getItemViewType(position) == R.id.text_saheeh_bukhari) {
            val thirdHadithChanges = items!!.thirdHadithChanges[getRandomNumber(
                items!!.thirdHadithChanges.size
            )]
            (holder as ThirdHadithChangesViewHolder).itemHomeThirdHadithChangesBinding.thirdHadithChangesModel =
                thirdHadithChanges
            holder.changeText(items!!.thirdHadithChanges)
            holder.setOnClick()

        } else if (getItemViewType(position) == R.id.live_image) {
            val secondTextChanges = items!!.secondTextChanges[getRandomNumber(
                items!!.secondTextChanges.size
            )]
            (holder as SecondTextChangesViewHolder).itemHomeSecondTextChangesBinding.secondTextChangesModel =
                secondTextChanges
            holder.changeText(items!!.secondTextChanges)
            holder.setOnClick()

        } else if (getItemViewType(position) == R.id.text_saheeh_muslim) {
            val saheehMuslim = items!!.fourthHadithChanges[getRandomNumber(
                items!!.fourthHadithChanges.size
            )]
            (holder as FourthHadithChangesViewHolder).itemHomeFourthHadithChangesBinding.fourthHadithChangesModel =
                saheehMuslim
            holder.changeText(items!!.fourthHadithChanges)
            holder.setOnClick()
        }
    }

    override fun getItemCount(): Int {
        return if (items == null) 0 else FIRST_ITEM_DISPLAYED_COUNT + items!!.blocks.size + KANZ_HASANAT_DISPLAYED_COUNT +DAILY_IMAGE_DISPLAYED_COUNT + TAHZEEB_MUSLIM_DISPLAYED_COUNT + SAHEEH_BUKHARI_DISPLAYED_COUNT + LIVE_DISPLAYED_COUNT + SAHEEH_MUSLIM_DISPLAYED_COUNT + COUNTER_DISPLAYED_COUNT
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return R.id.hadith_text
        } else if (position == FIRST_ITEM_DISPLAYED_COUNT
            && position < FIRST_ITEM_DISPLAYED_COUNT + items!!.blocks.size
        ) {
            return R.id.item_1
        } else if (position == FIRST_ITEM_DISPLAYED_COUNT + items!!.blocks.size
            && position < FIRST_ITEM_DISPLAYED_COUNT + items!!.blocks.size + KANZ_HASANAT_DISPLAYED_COUNT
        ) {
            return R.id.hadith_text_kanzhasanat
        } else if (position == FIRST_ITEM_DISPLAYED_COUNT + items!!.blocks.size + COUNTER_DISPLAYED_COUNT
            && position < FIRST_ITEM_DISPLAYED_COUNT + items!!.blocks.size + KANZ_HASANAT_DISPLAYED_COUNT + COUNTER_DISPLAYED_COUNT
        ) {
            return R.id.hadith_text_counter
        } else if (position == FIRST_ITEM_DISPLAYED_COUNT + items!!.blocks.size + KANZ_HASANAT_DISPLAYED_COUNT + COUNTER_DISPLAYED_COUNT
            && position < FIRST_ITEM_DISPLAYED_COUNT + items!!.blocks.size + KANZ_HASANAT_DISPLAYED_COUNT + COUNTER_DISPLAYED_COUNT + DAILY_IMAGE_DISPLAYED_COUNT
        ) {
            return R.id.daily_image
        } else if (position == FIRST_ITEM_DISPLAYED_COUNT + items!!.blocks.size + KANZ_HASANAT_DISPLAYED_COUNT + COUNTER_DISPLAYED_COUNT + DAILY_IMAGE_DISPLAYED_COUNT
            && position < FIRST_ITEM_DISPLAYED_COUNT + items!!.blocks.size + KANZ_HASANAT_DISPLAYED_COUNT + COUNTER_DISPLAYED_COUNT + DAILY_IMAGE_DISPLAYED_COUNT + TAHZEEB_MUSLIM_DISPLAYED_COUNT
        ) {
            return R.id.text_tahzeeb_muslim
        } else if (position == FIRST_ITEM_DISPLAYED_COUNT + items!!.blocks.size + KANZ_HASANAT_DISPLAYED_COUNT + COUNTER_DISPLAYED_COUNT + DAILY_IMAGE_DISPLAYED_COUNT + TAHZEEB_MUSLIM_DISPLAYED_COUNT
            && position < FIRST_ITEM_DISPLAYED_COUNT + items!!.blocks.size + KANZ_HASANAT_DISPLAYED_COUNT + COUNTER_DISPLAYED_COUNT + DAILY_IMAGE_DISPLAYED_COUNT + TAHZEEB_MUSLIM_DISPLAYED_COUNT + SAHEEH_BUKHARI_DISPLAYED_COUNT
        ) {
            return R.id.text_saheeh_bukhari
        } else if (position == FIRST_ITEM_DISPLAYED_COUNT + items!!.blocks.size + KANZ_HASANAT_DISPLAYED_COUNT + COUNTER_DISPLAYED_COUNT + DAILY_IMAGE_DISPLAYED_COUNT + TAHZEEB_MUSLIM_DISPLAYED_COUNT + SAHEEH_BUKHARI_DISPLAYED_COUNT
            && position < FIRST_ITEM_DISPLAYED_COUNT + items!!.blocks.size + KANZ_HASANAT_DISPLAYED_COUNT + COUNTER_DISPLAYED_COUNT + DAILY_IMAGE_DISPLAYED_COUNT + TAHZEEB_MUSLIM_DISPLAYED_COUNT + SAHEEH_BUKHARI_DISPLAYED_COUNT + LIVE_DISPLAYED_COUNT
        ) {
            return R.id.live_image
        } else if (position == FIRST_ITEM_DISPLAYED_COUNT + items!!.blocks.size + KANZ_HASANAT_DISPLAYED_COUNT + COUNTER_DISPLAYED_COUNT + DAILY_IMAGE_DISPLAYED_COUNT + TAHZEEB_MUSLIM_DISPLAYED_COUNT + SAHEEH_BUKHARI_DISPLAYED_COUNT + LIVE_DISPLAYED_COUNT
            && position < FIRST_ITEM_DISPLAYED_COUNT + items!!.blocks.size + KANZ_HASANAT_DISPLAYED_COUNT + COUNTER_DISPLAYED_COUNT + DAILY_IMAGE_DISPLAYED_COUNT + TAHZEEB_MUSLIM_DISPLAYED_COUNT + SAHEEH_BUKHARI_DISPLAYED_COUNT + +LIVE_DISPLAYED_COUNT + SAHEEH_MUSLIM_DISPLAYED_COUNT
        ) {
            return R.id.text_saheeh_muslim
        }
        return R.id.hadith_text
    }

    fun getRandomNumber(size: Int): Int {
        return Random().nextInt(size)
    }

    inner class VideosViewHolder(var itemHomeVideosBinding: ItemHomeVideosBinding) :
        RecyclerView.ViewHolder(
            itemHomeVideosBinding.root
        ) {
        fun setOnClick(videos: Videos) {
            itemView.setOnClickListener { v: View? ->
                val intent = Intent(itemView.context, YoutubePlayerActivity::class.java)
                intent.putExtra("url", videos.url)
                itemView.context.startActivity(intent)
            }
        }
    }

    inner class BlocksViewHolder(var itemHomeBlocksBinding: ItemHomeBlocksBinding) :
        RecyclerView.ViewHolder(
            itemHomeBlocksBinding.root
        ) {
        fun setOnClick(blocks: Blocks) {
            itemHomeBlocksBinding.item1.setOnClickListener {
                openNewActivity(
                    itemView.context,
                    PDFActivity::class.java
                )
            }
            itemHomeBlocksBinding.item2.setOnClickListener {
                openNewActivityWithInfo(
                    itemView.context,
                    DisplayHomeBlocksActivity::class.java,
                    blocks.itemTwo!!.title!!,
                    blocks.itemTwo.dataList!! as ArrayList<DataList>
                )
            }
            itemHomeBlocksBinding.item3.setOnClickListener { v: View? ->
                openNewActivityWithInfo(
                    itemView.context,
                    DisplayHomeBlocksActivity::class.java,
                    blocks.itemThree!!.title!!,
                    blocks.itemThree.dataList!! as ArrayList<DataList>
                )
            }
            itemHomeBlocksBinding.item4.setOnClickListener { v: View? ->
                openNewActivityWithInfo(
                    itemView.context,
                    DisplayHomeBlocksActivity::class.java,
                    blocks.itemFour!!.title!!,
                    blocks.itemFour.dataList!! as ArrayList<DataList>
                )
            }
        }
    }

    inner class FirstHadithChangesViewHolder(var itemHomeFirstHadithChangesBinding: ItemHomeFirstHadithChangesBinding) :
        RecyclerView.ViewHolder(
            itemHomeFirstHadithChangesBinding.root
        ) {
        fun setOnClick() {
            itemHomeFirstHadithChangesBinding.copyButtonOnClick.setOnClickListener { v: View? ->
                textAction.copyText(
                    itemHomeFirstHadithChangesBinding.hadithTextKanzhasanat.text.toString(),
                    itemView.context,
                    ""
                )
                textAction.shareTextSnackbar(
                    itemView.rootView,
                    "تم نسخ الحديث",
                    itemHomeFirstHadithChangesBinding.hadithTextKanzhasanat.text.toString(),
                    itemView.context
                )
            }
            itemHomeFirstHadithChangesBinding.shareButtonOnClick.setOnClickListener { v: View? ->
                copyText(
                    itemHomeFirstHadithChangesBinding.hadithTextKanzhasanat.text.toString(),
                    itemView.context,
                    ""
                )
                sharedText(
                    itemView.context,
                    itemHomeFirstHadithChangesBinding.hadithTextKanzhasanat.text.toString(),
                    "تم الإرسال من تطبيق حديث الغروب: سيرة النبي ﷺ",
                    ""
                )
            }
        }

        fun changeText(firstHadithChanges: List<HadithChanges>) {
            itemHomeFirstHadithChangesBinding.hadithTextKanzhasanat.text =
                firstHadithChanges[getRandomNumber(firstHadithChanges.size)].hadith
            itemHomeFirstHadithChangesBinding.changeButtonOnClick.setOnClickListener { v: View? ->
                itemHomeFirstHadithChangesBinding.hadithTextKanzhasanat.text =
                    firstHadithChanges[getRandomNumber(firstHadithChanges.size)].hadith
                counter.vibrateOnce(itemView.context)
            }
            itemHomeFirstHadithChangesBinding.hadithTextKanzhasanat.setOnClickListener { v: View? ->
                itemHomeFirstHadithChangesBinding.hadithTextKanzhasanat.text =
                    firstHadithChanges[getRandomNumber(firstHadithChanges.size)].hadith
                counter.vibrateOnce(itemView.context)
            }
        }
    }

    inner class CounterViewHolder(var itemHomeCounterBinding: ItemHomeCounterBinding) :
        RecyclerView.ViewHolder(
            itemHomeCounterBinding.root
        ) {
        fun setOnClick() {
            itemHomeCounterBinding.buttonCounter.setOnClickListener { v: View? ->
                counter.addCounter(itemHomeCounterBinding.buttonCounter)
                counter.vibrateOnce(itemView.context)
            }
            itemView.setOnClickListener { v: View? ->
                counter.addCounter(itemHomeCounterBinding.buttonCounter)
                counter.vibrateOnce(itemView.context)
            }
            itemHomeCounterBinding.resetButtonOnClick.setOnClickListener { v: View? ->
                counter.resetCounter(itemHomeCounterBinding.buttonCounter)
                counter.vibrateOnce(itemView.context)
            }
        }

        init {
            counter.displayCounter(itemHomeCounterBinding.buttonCounter)
        }
    }

    inner class SecondHadithChangesViewHolder(var itemHomeSecondHadithChangesBinding: ItemHomeSecondHadithChangesBinding) :
        RecyclerView.ViewHolder(
            itemHomeSecondHadithChangesBinding.root
        ) {
        fun setOnClick() {
            itemHomeSecondHadithChangesBinding.copyButtonOnClick.setOnClickListener { v: View? ->
                textAction.copyText(
                    itemHomeSecondHadithChangesBinding.dailyImage.text.toString(),
                    itemView.context,
                    ""
                )
                textAction.shareTextSnackbar(
                    itemView.rootView,
                    "تم نسخ الحديث",
                    itemHomeSecondHadithChangesBinding.dailyImage.text.toString(),
                    itemView.context
                )
            }
            itemHomeSecondHadithChangesBinding.shareButtonOnClick.setOnClickListener { v: View? ->
                copyText(
                    itemHomeSecondHadithChangesBinding.dailyImage.text.toString(),
                    itemView.context,
                    ""
                )
                sharedText(
                    itemView.context,
                    itemHomeSecondHadithChangesBinding.dailyImage.text.toString(),
                    "تم الإرسال من تطبيق حديث الغروب: سيرة النبي ﷺ",
                    ""
                )
            }
        }

        fun changeText(secondHadithChanges: List<HadithChanges>) {
            itemHomeSecondHadithChangesBinding.dailyImage.text =
                secondHadithChanges[getRandomNumber(secondHadithChanges.size)].hadith
            itemHomeSecondHadithChangesBinding.changeButtonOnClick.setOnClickListener { v: View? ->
                itemHomeSecondHadithChangesBinding.dailyImage.text =
                    secondHadithChanges[getRandomNumber(secondHadithChanges.size)].hadith
                counter.vibrateOnce(itemView.context)
            }
            itemHomeSecondHadithChangesBinding.dailyImage.setOnClickListener { v: View? ->
                itemHomeSecondHadithChangesBinding.dailyImage.text =
                    secondHadithChanges[getRandomNumber(secondHadithChanges.size)].hadith
                counter.vibrateOnce(itemView.context)
            }
        }
    }

    inner class FirstTextChangesViewHolder(var itemHomeFirstTextChangesBinding: ItemHomeFirstTextChangesBinding) :
        RecyclerView.ViewHolder(
            itemHomeFirstTextChangesBinding.root
        ) {
        fun changeText(firstTextChanges: List<TextChanges>) {
            itemHomeFirstTextChangesBinding.textTahzeebMuslim.text =
                firstTextChanges[getRandomNumber(firstTextChanges.size)].text
            itemHomeFirstTextChangesBinding.changeText.setOnClickListener { v: View? ->
                itemHomeFirstTextChangesBinding.textTahzeebMuslim.text =
                    firstTextChanges[getRandomNumber(firstTextChanges.size)].text
                counter.vibrateOnce(itemView.context)
            }
            itemHomeFirstTextChangesBinding.textTahzeebMuslim.setOnClickListener { v: View? ->
                itemHomeFirstTextChangesBinding.textTahzeebMuslim.text =
                    firstTextChanges[getRandomNumber(firstTextChanges.size)].text
                counter.vibrateOnce(itemView.context)
            }
        }

        fun setOnClick() {
            itemHomeFirstTextChangesBinding.shareButtonOnClick.setOnClickListener { v: View? ->
                copyText(
                    itemHomeFirstTextChangesBinding.textTahzeebMuslim.text.toString(),
                    itemView.context,
                    "من أسماء النبي: "
                )
                sharedText(
                    itemView.context,
                    itemHomeFirstTextChangesBinding.textTahzeebMuslim.text.toString(),
                    "تم الإرسال من تطبيق حديث الغروب: سيرة النبي ﷺ",
                    "من أسماء النبي: "
                )
            }
        }
    }

    inner class ThirdHadithChangesViewHolder(var itemHomeThirdHadithChangesBinding: ItemHomeThirdHadithChangesBinding) :
        RecyclerView.ViewHolder(
            itemHomeThirdHadithChangesBinding.root
        ) {
        fun setOnClick() {
            itemHomeThirdHadithChangesBinding.copyButtonOnClick.setOnClickListener { v: View? ->
                textAction.copyText(
                    itemHomeThirdHadithChangesBinding.hadithText.text.toString(),
                    itemView.context,
                    ""
                )
                textAction.shareTextSnackbar(
                    itemView.rootView,
                    "تم نسخ الحديث",
                    itemHomeThirdHadithChangesBinding.hadithText.text.toString(),
                    itemView.context
                )
            }
            itemHomeThirdHadithChangesBinding.shareButtonOnClick.setOnClickListener { v: View? ->
                copyText(
                    itemHomeThirdHadithChangesBinding.hadithText.text.toString(),
                    itemView.context,
                    ""
                )
                sharedText(
                    itemView.context,
                    itemHomeThirdHadithChangesBinding.hadithText.text.toString(),
                    "تم الإرسال من تطبيق حديث الغروب: سيرة النبي ﷺ",
                    ""
                )
            }
        }

        fun changeText(thirdHadithChanges: List<HadithChanges>) {
            itemHomeThirdHadithChangesBinding.hadithText.text =
                thirdHadithChanges[getRandomNumber(thirdHadithChanges.size)].hadith
            itemHomeThirdHadithChangesBinding.changeButtonOnClick.setOnClickListener { v: View? ->
                itemHomeThirdHadithChangesBinding.hadithText.text =
                    thirdHadithChanges[getRandomNumber(thirdHadithChanges.size)].hadith
                counter.vibrateOnce(itemView.context)
            }
            itemHomeThirdHadithChangesBinding.hadithText.setOnClickListener { v: View? ->
                itemHomeThirdHadithChangesBinding.hadithText.text =
                    thirdHadithChanges[getRandomNumber(thirdHadithChanges.size)].hadith
                counter.vibrateOnce(itemView.context)
            }
        }
    }

    inner class SecondTextChangesViewHolder(var itemHomeSecondTextChangesBinding: ItemHomeSecondTextChangesBinding) :
        RecyclerView.ViewHolder(
            itemHomeSecondTextChangesBinding.root
        ) {
        fun changeText(secondTextChanges: List<TextChanges>) {
            itemHomeSecondTextChangesBinding.liveImage.text =
                secondTextChanges[getRandomNumber(secondTextChanges.size)].text
            itemHomeSecondTextChangesBinding.changeText.setOnClickListener { v: View? ->
                itemHomeSecondTextChangesBinding.liveImage.text =
                    secondTextChanges[getRandomNumber(secondTextChanges.size)].text
                counter.vibrateOnce(itemView.context)
            }
            itemHomeSecondTextChangesBinding.liveImage.setOnClickListener { v: View? ->
                itemHomeSecondTextChangesBinding.liveImage.text =
                    secondTextChanges[getRandomNumber(secondTextChanges.size)].text
                counter.vibrateOnce(itemView.context)
            }
        }

        fun setOnClick() {
            itemHomeSecondTextChangesBinding.shareButtonOnClick.setOnClickListener { v: View? ->
                copyText(
                    itemHomeSecondTextChangesBinding.liveImage.text.toString(),
                    itemView.context,
                    "من وصايا النبي: "
                )
                sharedText(
                    itemView.context,
                    itemHomeSecondTextChangesBinding.liveImage.text.toString(),
                    "تم الإرسال من تطبيق حديث الغروب: سيرة النبي ﷺ",
                    "من وصايا النبي: "
                )
            }
        }
    }

    inner class FourthHadithChangesViewHolder(var itemHomeFourthHadithChangesBinding: ItemHomeFourthHadithChangesBinding) :
        RecyclerView.ViewHolder(
            itemHomeFourthHadithChangesBinding.root
        ) {
        fun setOnClick() {
            itemHomeFourthHadithChangesBinding.copyButtonOnClick.setOnClickListener { v: View? ->
                textAction.copyText(
                    itemHomeFourthHadithChangesBinding.hadithText.text.toString(),
                    itemView.context,
                    ""
                )
                textAction.shareTextSnackbar(
                    itemView.rootView,
                    "تم نسخ الحديث",
                    itemHomeFourthHadithChangesBinding.hadithText.text.toString(),
                    itemView.context
                )
            }
            itemHomeFourthHadithChangesBinding.shareButtonOnClick.setOnClickListener { v: View? ->
                copyText(
                    itemHomeFourthHadithChangesBinding.hadithText.text.toString(),
                    itemView.context,
                    ""
                )
                sharedText(
                    itemView.context,
                    itemHomeFourthHadithChangesBinding.hadithText.text.toString(),
                    "تم الإرسال من تطبيق حديث الغروب: سيرة النبي ﷺ",
                    ""
                )
            }
        }

        fun changeText(saheehMuslim: List<HadithChanges>) {
            itemHomeFourthHadithChangesBinding.hadithText.text =
                saheehMuslim[getRandomNumber(saheehMuslim.size)].hadith
            itemHomeFourthHadithChangesBinding.changeButtonOnClick.setOnClickListener { v: View? ->
                itemHomeFourthHadithChangesBinding.hadithText.text =
                    saheehMuslim[getRandomNumber(saheehMuslim.size)].hadith
                counter.vibrateOnce(itemView.context)
            }
            itemHomeFourthHadithChangesBinding.hadithText.setOnClickListener { v: View? ->
                itemHomeFourthHadithChangesBinding.hadithText.text =
                    saheehMuslim[getRandomNumber(saheehMuslim.size)].hadith
                counter.vibrateOnce(itemView.context)
            }
        }
    }
}
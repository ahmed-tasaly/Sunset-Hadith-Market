package com.moataz.afternoonhadeeth.ui.offline.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.moataz.afternoonhadeeth.R
import com.moataz.afternoonhadeeth.data.model.offline.HadithOffline
import com.moataz.afternoonhadeeth.databinding.ListHadithOfflineBinding
import com.moataz.afternoonhadeeth.utils.helper.Intents.copyText
import com.moataz.afternoonhadeeth.utils.helper.Intents.shareTextSnackbar
import com.moataz.afternoonhadeeth.utils.helper.Intents.sharedText
import java.util.*

class HadithOfflineAdapter(private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: List<HadithOffline?>? = ArrayList()

    @SuppressLint("NotifyDataSetChanged")
    fun setHadithList(items: List<HadithOffline?>?) {
        this.items = items
        Collections.shuffle(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HadithOfflineViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_hadith_offline,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val hadithOffline = items!![position]
        (holder as HadithOfflineViewHolder).listHadithBinding.hadithModelOffline = hadithOffline
        holder.setOnClick(hadithOffline)
    }

    override fun getItemCount(): Int {
        return if (items == null) 0 else items!!.size
    }

    internal class HadithOfflineViewHolder(var listHadithBinding: ListHadithOfflineBinding) :
        RecyclerView.ViewHolder(
            listHadithBinding.root
        ) {
        fun setOnClick(hadithOffline: HadithOffline?) {
            listHadithBinding.copyButtonOnClick.setOnClickListener { view: View? ->
                copyText(
                    Objects.requireNonNull(
                        hadithOffline!!.hadith
                    ), itemView.context, ""
                )
                shareTextSnackbar(
                    itemView.rootView, "تم نسخ الحديث", Objects.requireNonNull(
                        hadithOffline.hadith
                    ), itemView.context
                )
            }
            listHadithBinding.shareButtonOnClick.setOnClickListener { view: View? ->
                copyText(
                    Objects.requireNonNull(
                        hadithOffline!!.hadith
                    ), itemView.context, ""
                )
                sharedText(
                    itemView.context, Objects.requireNonNull(
                        hadithOffline.hadith
                    ), "تم الإرسال من تطبيق حديث الغروب: أحاديث النبي ﷺ", ""
                )
            }
        }
    }
}
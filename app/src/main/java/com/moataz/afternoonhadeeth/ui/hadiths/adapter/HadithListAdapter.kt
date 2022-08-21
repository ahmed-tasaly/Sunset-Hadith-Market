package com.moataz.afternoonhadeeth.ui.hadiths.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.moataz.afternoonhadeeth.R
import com.moataz.afternoonhadeeth.data.model.hadith.Hadith
import com.moataz.afternoonhadeeth.databinding.ListHadithDataBinding
import com.moataz.afternoonhadeeth.utils.helper.Intents.copyText
import com.moataz.afternoonhadeeth.utils.helper.Intents.shareTextSnackbar
import com.moataz.afternoonhadeeth.utils.helper.Intents.sharedText
import java.util.*

class HadithListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: List<Hadith?>? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setHadithList(items: List<Hadith?>?) {
        this.items = items
        notifyDataSetChanged()
        items!!.shuffled()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HadithViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_hadith_data,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val hadith = items!![position]
        (holder as HadithViewHolder).listHadithBinding.hadithModel = hadith
        holder.setOnClick(hadith)
    }

    override fun getItemCount(): Int {
        return if (items == null) 0 else items!!.size
    }

    internal class HadithViewHolder(var listHadithBinding: ListHadithDataBinding) :
        RecyclerView.ViewHolder(
            listHadithBinding.root
        ) {
        fun setOnClick(hadith: Hadith?) {
            listHadithBinding.copyButtonOnClick.setOnClickListener {
                Objects.requireNonNull(
                    hadith!!.hadith
                ).let { hadith ->
                    copyText(
                        hadith.toString(), itemView.context, ""
                    )
                }
                Objects.requireNonNull(
                    hadith.hadith
                ).let { hadith ->
                    shareTextSnackbar(
                        itemView.rootView, "تم نسخ الحديث", hadith.toString(), itemView.context
                    )
                }
            }
            listHadithBinding.shareButtonOnClick.setOnClickListener {
                Objects.requireNonNull(
                    hadith!!.hadith
                ).let { hadith ->
                    copyText(
                        hadith.toString(), itemView.context, ""
                    )
                }
                Objects.requireNonNull(
                    hadith.hadith
                ).let { hadith ->
                    sharedText(
                        itemView.context,
                        hadith.toString(),
                        "تم الإرسال من تطبيق حديث الغروب: سيرة النبي ﷺ",
                        ""
                    )
                }
            }
        }
    }
}
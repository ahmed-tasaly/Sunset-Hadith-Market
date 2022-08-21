package com.moataz.afternoonhadeeth.ui.hadiths.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.moataz.afternoonhadeeth.R
import com.moataz.afternoonhadeeth.data.model.hadith.Hadith
import com.moataz.afternoonhadeeth.data.model.hadith.HadithMainData
import com.moataz.afternoonhadeeth.data.model.hadith.HadithResponse
import com.moataz.afternoonhadeeth.databinding.ListHadithBinding
import com.moataz.afternoonhadeeth.ui.hadiths.view.activity.DisplayHadithListActivity
import com.moataz.afternoonhadeeth.utils.helper.Intents.openNewActivityWithHadith

class HadithAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: HadithResponse? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setHadithList(items: HadithResponse?) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HadithViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_hadith,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val hadith = items!!.hadithMainData[position]
        (holder as HadithViewHolder).listHadithBinding.hadithMainModel = hadith
        holder.setOnClick(hadith)
    }

    override fun getItemCount(): Int {
        return if (items == null) 0 else items!!.hadithMainData.size
    }

    internal class HadithViewHolder(var listHadithBinding: ListHadithBinding) :
        RecyclerView.ViewHolder(
            listHadithBinding.root
        ) {
        fun setOnClick(hadithData: HadithMainData) {
            itemView.setOnClickListener {
                openNewActivityWithHadith(
                    itemView.context,
                    DisplayHadithListActivity::class.java,
                    hadithData.title,
                    hadithData.dataList as ArrayList<Hadith>
                )
            }
        }
    }
}
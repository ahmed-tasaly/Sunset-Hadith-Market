package com.moataz.afternoonhadeeth.ui.videos.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.moataz.afternoonhadeeth.R
import com.moataz.afternoonhadeeth.data.model.videos.top.Data
import com.moataz.afternoonhadeeth.databinding.ListDataVideosBlocksBinding
import com.moataz.afternoonhadeeth.ui.home.view.activity.YoutubePlayerActivity

class VideosTopBlocksListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: List<Data>? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setDataList(items: List<Data>?) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DisplayListFromBlocksViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_data_videos_blocks,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataList = items!![position]
        (holder as DisplayListFromBlocksViewHolder).listDataVideosBlocksBinding.listBlocksVideosModel =
            dataList
        holder.setOnClick(dataList)
    }

    override fun getItemCount(): Int {
        return if (items == null) 0 else items!!.size
    }

    internal class DisplayListFromBlocksViewHolder(var listDataVideosBlocksBinding: ListDataVideosBlocksBinding) :
        RecyclerView.ViewHolder(
            listDataVideosBlocksBinding.root
        ) {
        fun setOnClick(dataList: Data) {
            listDataVideosBlocksBinding.tets.setOnClickListener { v: View? ->
                val intent = Intent(itemView.context, YoutubePlayerActivity::class.java)
                intent.putExtra("url", dataList.url)
                itemView.context.startActivity(intent)
            }
        }
    }
}
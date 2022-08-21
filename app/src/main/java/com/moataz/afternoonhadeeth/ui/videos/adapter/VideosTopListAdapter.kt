package com.moataz.afternoonhadeeth.ui.videos.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.moataz.afternoonhadeeth.R
import com.moataz.afternoonhadeeth.data.model.videos.top.TopVideosList
import com.moataz.afternoonhadeeth.data.model.videos.top.TopVideosResponse
import com.moataz.afternoonhadeeth.databinding.ItemVideosTopListVideosBinding
import com.moataz.afternoonhadeeth.ui.home.view.activity.YoutubePlayerActivity
import java.util.*

class VideosTopListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: TopVideosResponse? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setTopVideosList(items: TopVideosResponse) {
        this.items = items
        items.topVideosList.shuffled()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TopVideosViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_videos_top_list_videos,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val topVideosList = items!!.topVideosList[position]
        (holder as TopVideosViewHolder).itemVideosTopListVideosBinding.listTopVideosModel =
            topVideosList
        holder.setOnClick(topVideosList)
    }

    override fun getItemCount(): Int {
        return if (items == null) 0 else items!!.topVideosList.size
    }

    internal class TopVideosViewHolder(var itemVideosTopListVideosBinding: ItemVideosTopListVideosBinding) :
        RecyclerView.ViewHolder(
            itemVideosTopListVideosBinding.root
        ) {
        fun setOnClick(topVideosList: TopVideosList) {
            itemView.setOnClickListener { v: View? ->
                val intent = Intent(itemView.context, YoutubePlayerActivity::class.java)
                intent.putExtra("url", topVideosList.url)
                itemView.context.startActivity(intent)
            }
        }
    }
}
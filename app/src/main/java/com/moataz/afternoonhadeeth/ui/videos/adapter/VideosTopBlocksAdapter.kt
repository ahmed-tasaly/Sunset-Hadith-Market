package com.moataz.afternoonhadeeth.ui.videos.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.moataz.afternoonhadeeth.R
import com.moataz.afternoonhadeeth.data.model.videos.top.Data
import com.moataz.afternoonhadeeth.data.model.videos.top.TopVideosBlocks
import com.moataz.afternoonhadeeth.data.model.videos.top.TopVideosResponse
import com.moataz.afternoonhadeeth.databinding.ItemVideosTopBlocksBinding
import com.moataz.afternoonhadeeth.ui.videos.view.activity.DisplayVideoBlocksActivity
import com.moataz.afternoonhadeeth.utils.helper.Intents.openNewActivityWithVideos
import java.util.*

class VideosTopBlocksAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: TopVideosResponse? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setTopVideosBlocks(items: TopVideosResponse?) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BlocksViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_videos_top_blocks,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val blocks = items!!.topVideosBlocks[position]
        (holder as BlocksViewHolder).itemVideosTopBlocksBinding.blocksTopVideosModel = blocks
        holder.setOnClick(blocks)
    }

    override fun getItemCount(): Int {
        return if (items == null) 0 else items!!.topVideosBlocks.size
    }

    internal class BlocksViewHolder(var itemVideosTopBlocksBinding: ItemVideosTopBlocksBinding) :
        RecyclerView.ViewHolder(
            itemVideosTopBlocksBinding.root
        ) {
        fun setOnClick(topVideosBlocks: TopVideosBlocks) {
            itemVideosTopBlocksBinding.itemTopVideos1.setOnClickListener { v: View? ->
                openNewActivityWithVideos(
                    itemView.context,
                    DisplayVideoBlocksActivity::class.java,
                    topVideosBlocks.itemOne!!.title!!,
                    Objects.requireNonNull<List<Data?>>(
                        topVideosBlocks.itemOne.dataList
                    ) as ArrayList<Data>
                )
            }
            itemVideosTopBlocksBinding.itemTopVideos2.setOnClickListener { v: View? ->
                openNewActivityWithVideos(
                    itemView.context,
                    DisplayVideoBlocksActivity::class.java,
                    topVideosBlocks.itemTwo!!.title!!,
                    Objects.requireNonNull<List<Data?>>(
                        topVideosBlocks.itemTwo.dataList
                    ) as ArrayList<Data>
                )
            }
            itemVideosTopBlocksBinding.itemTopVideos3.setOnClickListener { v: View? ->
                openNewActivityWithVideos(
                    itemView.context,
                    DisplayVideoBlocksActivity::class.java,
                    topVideosBlocks.itemThree!!.title!!,
                    Objects.requireNonNull<List<Data?>>(
                        topVideosBlocks.itemThree.dataList
                    ) as ArrayList<Data>
                )
            }
            itemVideosTopBlocksBinding.itemTopVideos4.setOnClickListener { v: View? ->
                openNewActivityWithVideos(
                    itemView.context,
                    DisplayVideoBlocksActivity::class.java,
                    topVideosBlocks.itemFour!!.title!!,
                    Objects.requireNonNull<List<Data?>>(
                        topVideosBlocks.itemFour.dataList
                    ) as ArrayList<Data>
                )
            }
        }
    }
}
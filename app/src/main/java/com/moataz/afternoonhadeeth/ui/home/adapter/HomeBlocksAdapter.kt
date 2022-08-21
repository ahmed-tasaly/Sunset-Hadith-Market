package com.moataz.afternoonhadeeth.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.moataz.afternoonhadeeth.R
import com.moataz.afternoonhadeeth.data.model.home.blocks.DataList
import com.moataz.afternoonhadeeth.databinding.ListDataBlocksBinding
import com.moataz.afternoonhadeeth.utils.helper.Intents.openTabUrl

class HomeBlocksAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: List<DataList>? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setDataList(items: List<DataList>?) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DisplayListFromBlocksViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_data_blocks,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataList = items!![position]
        (holder as DisplayListFromBlocksViewHolder).listDataBlocksBinding.dataListBlocksModel =
            dataList
        holder.setOnClick(dataList)
    }

    override fun getItemCount(): Int {
        return if (items == null) 0 else items!!.size
    }

    internal class DisplayListFromBlocksViewHolder(var listDataBlocksBinding: ListDataBlocksBinding) :
        RecyclerView.ViewHolder(
            listDataBlocksBinding.root
        ) {
        fun setOnClick(dataList: DataList) {
            listDataBlocksBinding.tets.setOnClickListener {
                openTabUrl(
                    itemView.context,
                    dataList.url!!
                )
            }
        }
    }
}
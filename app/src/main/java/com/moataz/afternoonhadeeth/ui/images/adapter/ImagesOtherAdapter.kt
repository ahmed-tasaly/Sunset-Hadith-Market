package com.moataz.afternoonhadeeth.ui.images.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.moataz.afternoonhadeeth.R
import com.moataz.afternoonhadeeth.data.model.image.Images
import com.moataz.afternoonhadeeth.databinding.ListImagesMasjidBinding
import com.moataz.afternoonhadeeth.ui.images.view.activity.DisplayImageActivity
import java.util.*

class ImagesOtherAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: List<Images?>? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setImagesList(items: List<Images?>?) {
        this.items = items
        Collections.shuffle(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ImagesViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_images_masjid,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val images = items!![position]
        (holder as ImagesViewHolder).listImagesBinding.imagesModel = images
        holder.setOnClick(images)
    }

    override fun getItemCount(): Int {
        return if (items == null) 0 else items!!.size
    }

    internal class ImagesViewHolder(var listImagesBinding: ListImagesMasjidBinding) :
        RecyclerView.ViewHolder(
            listImagesBinding.root
        ) {
        fun setOnClick(images: Images?) {
            itemView.setOnClickListener { v: View? ->
                val intent = Intent(itemView.context, DisplayImageActivity::class.java)
                intent.putExtra("imageUrl", images!!.imageUrl)
                itemView.context.startActivity(intent)
            }
        }
    }
}
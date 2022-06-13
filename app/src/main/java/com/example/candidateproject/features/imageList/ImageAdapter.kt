package com.example.candidateproject.features.imageList

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.candidateproject.R
import com.example.candidateproject.base.RecyclerInterface
import com.example.candidateproject.databinding.ItemImageBinding
import com.example.candidateproject.services.responds.ImageListRespond
import kotlin.properties.Delegates

class ImageAdapter: RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    var mContext: Context? = null

    var list by Delegates.observable(mutableListOf<ImageListRespond>()) { _, _, _ ->
        notifyDataSetChanged()
    }
    var listener: RecyclerInterface<ImageListRespond>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val itemBinding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bindData(mContext, list[position], listener)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ImageViewHolder(private val itemBinding: ItemImageBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bindData(mContext: Context?, data: ImageListRespond, listener: RecyclerInterface<ImageListRespond>?) {
            itemBinding.apply {
                mContext?.let {
                    Glide.with(it)
                        .load(data.download_url)
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .error(R.drawable.ic_launcher_foreground)
                        .fitCenter()
                        .into(iv)
                }

                authorTv.text = data.author

                cv.setOnClickListener { listener?.onItemClick(data) }
            }
        }
    }
}
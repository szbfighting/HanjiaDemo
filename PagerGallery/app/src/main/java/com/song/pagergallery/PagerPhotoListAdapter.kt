package com.song.pagergallery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.pager_photo_veiw.view.*

class PagerPhotoListAdapter: ListAdapter<PhotoItem, PagerPhotoViewHolder>(DIFFCALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerPhotoViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.pager_photo_veiw,parent,false).apply {
            val holder = PagerPhotoViewHolder(this)
            return holder
        }

    }

    override fun onBindViewHolder(holder: PagerPhotoViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(getItem(position).previewUrl)
            .placeholder(R.drawable.photo_placeholder)
            .into(holder.itemView.pagerPhoto)
    }

    object DIFFCALLBACK:DiffUtil.ItemCallback<PhotoItem>(){
        override fun areItemsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
                return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
                return oldItem.photoId == newItem.photoId
        }

    }

}

class PagerPhotoViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

}
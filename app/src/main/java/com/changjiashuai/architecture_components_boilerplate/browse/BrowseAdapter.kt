package com.changjiashuai.architecture_components_boilerplate.browse

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.changjiashuai.architecture_components_boilerplate.R
import com.changjiashuai.architecture_components_boilerplate.model.BufferooViewModel
import javax.inject.Inject

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 2017/12/12 14:40.
 */
class BrowseAdapter @Inject constructor() : RecyclerView.Adapter<BrowseAdapter.ViewHolder>() {

    var bufferoos: List<BufferooViewModel> = arrayListOf()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bufferoo = bufferoos[position]
        holder.tvName.text = bufferoo.name
        holder.tvTitle.text = bufferoo.title

        Glide.with(holder.itemView.context)
                .load(bufferoo.avatar)
                .apply(RequestOptions.circleCropTransform())
                .into(holder.ivAvatar)
    }

    override fun getItemCount(): Int {
        return bufferoos.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_bufferoo, parent, false)
        return ViewHolder(itemView)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var ivAvatar: ImageView
        var tvName: TextView
        var tvTitle: TextView

        init {
            ivAvatar = view.findViewById(R.id.image_avatar)
            tvName = view.findViewById(R.id.text_name)
            tvTitle = view.findViewById(R.id.text_title)
        }
    }
}
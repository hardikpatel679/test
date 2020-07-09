package com.hdapp.test.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.hdapp.test2.R
import com.hdapp.test2.data.db.Row
import java.util.*


class RowAdapter(var context: Context, var tempList: List<Row>) :
    RecyclerView.Adapter<RowAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem: View = layoutInflater.inflate(R.layout.item_row, parent, false)
        return ViewHolder(listItem)
    }

    override fun getItemCount(): Int {
        return tempList.size
    }

    override fun onBindViewHolder(holder: RowAdapter.ViewHolder, position: Int) {
        val temp: Row = tempList.get(position)
        holder.tvdescription.setText(temp.description?.trim())
        Glide.with(context).load(temp.imageHref).placeholder(R.drawable.ic_noimage)
            .into(holder.ivImage)


    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvdescription: TextView
        var ivImage: ImageView

        init {
            tvdescription = itemView.findViewById(R.id.tvdescription)
            ivImage = itemView.findViewById(R.id.ivImage)
        }
    }
}
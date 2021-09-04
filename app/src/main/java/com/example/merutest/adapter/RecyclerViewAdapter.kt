package com.example.merutest.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.merutest.MainActivity
import com.example.merutest.R
import com.example.merutest.database.LikeDatabase
import com.example.merutest.interfaces.Like
import com.example.merutest.interfaces.Save
import com.example.merutest.model.ReceipeData
import kotlinx.android.synthetic.main.recyclerview_row.view.*

class RecyclerViewAdapter(private val activity: MainActivity): RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    var items = ArrayList<ReceipeData>()
    private val like: Like = activity as Like
    private val save: Save = activity as Save

    fun setListData(data: ArrayList<ReceipeData>) {
        this.items = data
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_row, parent, false)

        return MyViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
        holder.ivLikeData.setOnClickListener {
            like.addToLike(items[position])
        }

        holder.ivSaveData.setOnClickListener {
            save.addToSave(items[position])
        }
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val tvTitle = view.tvTitle
        val tvPublisher = view.tvPublisher
        val img = view.img
        val ivLikeData = view.ivLikeData
        val ivSaveData = view.ivSaveData

        fun bind(data: ReceipeData) {
            tvTitle.text = data.title
            tvPublisher.text = data.publisher

            Glide.with(img)
                .load(data.image_url)
                .into(img)
        }
    }
}

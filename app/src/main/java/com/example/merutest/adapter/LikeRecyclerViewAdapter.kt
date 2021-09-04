package com.example.merutest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.merutest.MainActivity
import com.example.merutest.R
import com.example.merutest.entity.EntityLikeReceipe
import com.example.merutest.interfaces.DisLike
import com.example.merutest.interfaces.Like
import com.example.merutest.model.ReceipeData
import com.example.merutest.view.LikeReceipeActivity
import kotlinx.android.synthetic.main.liked_recyclerview_row.view.*
import kotlinx.android.synthetic.main.recyclerview_row.view.*

class LikeRecyclerViewAdapter(private val activity: LikeReceipeActivity): RecyclerView.Adapter<LikeRecyclerViewAdapter.MyViewHolder>() {

    var items = ArrayList<EntityLikeReceipe>()
    private val disLike: DisLike = activity as DisLike

    fun setListData(data: ArrayList<EntityLikeReceipe>) {
        this.items = data
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.liked_recyclerview_row, parent, false)

        return MyViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
        holder.ivDislike.setOnClickListener {
            disLike.removeFromLike(items[position])
        }
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val tvLikeTitle = view.tvLikeTitle
        val tvLikePublisher = view.tvLikePublisher
        val imgLike = view.imgLike
        val ivDislike = view.ivDislike

        fun bind(data: EntityLikeReceipe) {
            tvLikeTitle.text = data.title
            tvLikePublisher.text = data.publisher

            Glide.with(imgLike)
                .load(data.image_url)
                .into(imgLike)
        }
    }
}

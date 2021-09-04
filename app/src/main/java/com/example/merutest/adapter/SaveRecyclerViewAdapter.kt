package com.example.merutest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.merutest.R
import com.example.merutest.entity.EntitySaveRecipes
import com.example.merutest.interfaces.DeleteRecipe
import com.example.merutest.view.SaveRecipesActivity
import kotlinx.android.synthetic.main.saved_recyclerview_row.view.*

class SaveRecyclerViewAdapter(private val activity: SaveRecipesActivity): RecyclerView.Adapter<SaveRecyclerViewAdapter.MyViewHolder>() {

    var items = ArrayList<EntitySaveRecipes>()
    private val delete: DeleteRecipe = activity as DeleteRecipe

    fun setListData(data: ArrayList<EntitySaveRecipes>) {
        this.items = data
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.saved_recyclerview_row, parent, false)

        return MyViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
        holder.ivDelete.setOnClickListener {
            delete.deleteFromSave(items[position])
        }
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val tvSaveTitle = view.tvSaveTitle
        val tvSavePublisher = view.tvSavePublisher
        val imgSave = view.imgSave
        val ivDelete = view.ivDelete

        fun bind(data: EntitySaveRecipes) {
            tvSaveTitle.text = data.title
            tvSavePublisher.text = data.publisher

            Glide.with(imgSave)
                .load(data.image_url)
                .into(imgSave)
        }
    }
}

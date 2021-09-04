package com.example.merutest.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.room.Room
import com.example.merutest.R
import com.example.merutest.adapter.LikeRecyclerViewAdapter
import com.example.merutest.database.LikeDatabase
import com.example.merutest.entity.EntityLikeReceipe
import com.example.merutest.interfaces.DisLike
import com.example.merutest.model.ReceipeData
import kotlinx.android.synthetic.main.activity_like_receipe.*
import kotlinx.android.synthetic.main.fragment_potato.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LikeReceipeActivity : AppCompatActivity(), DisLike
{
    lateinit var likeRecyclerViewAdapter: LikeRecyclerViewAdapter
    lateinit var likeDatabase: LikeDatabase

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_like_receipe)

        likeDatabase = Room.databaseBuilder(applicationContext,
            LikeDatabase::class.java,
            "likeDB").build()

        initRecyclerView()
        createData()
    }

    private fun initRecyclerView()
    {
        rvLikeReceipe.apply {
            layoutManager = LinearLayoutManager(context)
            likeRecyclerViewAdapter = LikeRecyclerViewAdapter(this@LikeReceipeActivity)
            adapter = likeRecyclerViewAdapter

            val decoration = DividerItemDecoration(context,
                StaggeredGridLayoutManager.VERTICAL
            )
            addItemDecoration(decoration)
        }
    }

    private fun createData() {
        likeDatabase.likeDao().getLikeRecipe().observe(this, Observer {
            if (it != null)
            {
                if (ArrayList(it).size > 0) {
                    rvLikeReceipe?.isVisible = true
                    rlNoLikedRecipes?.isVisible = false
                } else {
                    rvLikeReceipe?.isVisible = false
                    rlNoLikedRecipes?.isVisible = true
                }
                likeRecyclerViewAdapter.setListData(ArrayList(it))
                likeRecyclerViewAdapter.notifyDataSetChanged()
            }
        })
    }

    override fun removeFromLike(entityLikeReceipe: EntityLikeReceipe)
    {
        GlobalScope.launch {
            likeDatabase.likeDao().dislikeReceipe(entityLikeReceipe)
        }
    }
}
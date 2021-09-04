package com.example.merutest.view

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.room.Delete
import androidx.room.Room
import com.example.merutest.MainActivity
import com.example.merutest.R
import com.example.merutest.adapter.LikeRecyclerViewAdapter
import com.example.merutest.adapter.SaveRecyclerViewAdapter
import com.example.merutest.database.LikeDatabase
import com.example.merutest.database.SaveDatabase
import com.example.merutest.entity.EntitySaveRecipes
import com.example.merutest.interfaces.DeleteRecipe
import kotlinx.android.synthetic.main.activity_like_receipe.*
import kotlinx.android.synthetic.main.activity_save_recipes.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SaveRecipesActivity : AppCompatActivity(), DeleteRecipe {

    lateinit var saveRecyclerViewAdapter: SaveRecyclerViewAdapter
    lateinit var saveDatabase: SaveDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_recipes)

        saveDatabase = Room.databaseBuilder(applicationContext,
            SaveDatabase::class.java,
            "saveDB").build()

        initRecyclerView()
        createData()
    }

    private fun initRecyclerView()
    {
        rvSavedReceipe.apply {
            layoutManager = LinearLayoutManager(context)
            saveRecyclerViewAdapter = SaveRecyclerViewAdapter(this@SaveRecipesActivity)
            adapter = saveRecyclerViewAdapter

            val decoration = DividerItemDecoration(context,
                StaggeredGridLayoutManager.VERTICAL
            )
            addItemDecoration(decoration)
        }
    }

    private fun createData() {
        saveDatabase.saveDao().getSaveRecipe().observe(this, Observer {
            if (it != null)
            {
                if (ArrayList(it).size > 0) {
                    rvSavedReceipe?.isVisible = true
                    rlNoSavedRecipes?.isVisible = false
                } else {
                    rvSavedReceipe?.isVisible = false
                    rlNoSavedRecipes?.isVisible = true
                }
                saveRecyclerViewAdapter.setListData(ArrayList(it))
                saveRecyclerViewAdapter.notifyDataSetChanged()
            }
        })
    }

    override fun deleteFromSave(entitySaveRecipes: EntitySaveRecipes)
    {
        GlobalScope.launch {
            saveDatabase.saveDao().deleteReceipe(entitySaveRecipes)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val ConnectionManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = ConnectionManager.activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnected == true)
        {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        else
        {
            finish()
        }
    }
}
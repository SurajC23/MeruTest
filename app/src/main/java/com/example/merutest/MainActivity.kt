package com.example.merutest

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.room.Room
import androidx.viewpager2.widget.ViewPager2
import com.example.merutest.adapter.ViewPagerAdapter
import com.example.merutest.database.LikeDatabase
import com.example.merutest.database.SaveDatabase
import com.example.merutest.entity.EntityLikeReceipe
import com.example.merutest.entity.EntitySaveRecipes
import com.example.merutest.interfaces.Like
import com.example.merutest.interfaces.Save
import com.example.merutest.model.ReceipeData
import com.example.merutest.view.LikeReceipeActivity
import com.example.merutest.view.SaveRecipesActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), Like, Save
{
    lateinit var likeDatabase: LikeDatabase
    lateinit var saveDatabase: SaveDatabase
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ConnectionManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = ConnectionManager.activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnected == true) {
        } else {
            val intent = Intent(this, SaveRecipesActivity::class.java)
            startActivity(intent)
            finish()
        }

        likeDatabase = Room.databaseBuilder(applicationContext,
        LikeDatabase::class.java,
        "likeDB").build()

        saveDatabase = Room.databaseBuilder(applicationContext,
            SaveDatabase::class.java,
            "saveDB").build()

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        val ivLikeData = findViewById<ImageView>(R.id.ivLikeData)
        val ivSaveData = findViewById<ImageView>(R.id.ivSaveData)

        val adapter= ViewPagerAdapter(supportFragmentManager,lifecycle)
        viewPager.adapter=adapter

        TabLayoutMediator(tabLayout,viewPager){tab,position->
            when(position){
                0->{
                    tab.text=getString(R.string.potato)
                }
                1->{
                    tab.text=getString(R.string.chicken)
                }
                2->{
                    tab.text=getString(R.string.fish)
                }
            }
        }.attach()

        ivLikeData.setOnClickListener {
            val intent = Intent(this, LikeReceipeActivity::class.java)
            startActivity(intent)
        }

        ivSaveData.setOnClickListener {
            val intent = Intent(this, SaveRecipesActivity::class.java)
            startActivity(intent)
        }
    }

    override fun addToLike(receipeData: ReceipeData)
    {
        GlobalScope.launch {
            likeDatabase.likeDao().likeReceipe(EntityLikeReceipe(0, receipeData.recipe_id,
                receipeData.image_url, receipeData.title, receipeData.publisher))
        }
    }

    override fun addToSave(receipeData: ReceipeData)
    {
        GlobalScope.launch {
            saveDatabase.saveDao().saveReceipe(
                EntitySaveRecipes(0, receipeData.recipe_id,
                receipeData.image_url, receipeData.title, receipeData.publisher)
            )
        }
    }
}
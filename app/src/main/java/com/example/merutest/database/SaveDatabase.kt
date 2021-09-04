package com.example.merutest.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.merutest.dao.LikeReceipeDoa
import com.example.merutest.dao.SaveReceipeDoa
import com.example.merutest.entity.EntityLikeReceipe
import com.example.merutest.entity.EntitySaveRecipes

@Database(entities = [EntitySaveRecipes::class], version = 2)
abstract class SaveDatabase: RoomDatabase()
{
    abstract fun saveDao(): SaveReceipeDoa
}
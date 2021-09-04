package com.example.merutest.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.merutest.dao.LikeReceipeDoa
import com.example.merutest.entity.EntityLikeReceipe

@Database(entities = [EntityLikeReceipe::class], version = 1)
abstract class LikeDatabase: RoomDatabase()
{
    abstract fun likeDao(): LikeReceipeDoa
}